package io.github.madbirds.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Predicts the flight path of a launched bird by replaying Box2D's own integrator
 * step by step, so the drawn curve matches what the physics world will actually do.
 *
 * Box2D (b2Island::Solve) advances a free-flying body each step as:
 *   v += h * gravityScale * g
 *   v *= 1 / (1 + h * linearDamping)
 *   if |h * v| > MAX_TRANSLATION: v scaled so |h * v| == MAX_TRANSLATION
 *   x += h * v
 * This is semi-implicit Euler, which differs slightly from the analytic parabola,
 * so we mirror it exactly instead of using the closed-form formula.
 */
public class TrajectoryEngine {
    /** Box2D's b2_maxTranslation: the most a body can move in a single step. */
    private static final float MAX_TRANSLATION = 2f;

    private final float stepDelta;
    private final Vector2[] points;
    private int pointCount;

    private boolean hit;
    private final Vector2 hitPoint = new Vector2();

    private final Vector2 position = new Vector2();
    private final Vector2 velocity = new Vector2();
    private final Vector2 gravity = new Vector2();
    private final Vector2 next = new Vector2();
    private final Vector2 direction = new Vector2();
    private final Vector2 offset = new Vector2();
    private final Vector2 rayStart = new Vector2();
    private final Vector2 rayEnd = new Vector2();

    private final ClosestHitCallback rayCallback = new ClosestHitCallback();

    /**
     * @param stepDelta must be the same fixed step the world is stepped with (Game.STEP_DELTA)
     * @param maxSteps  how many steps ahead to predict (e.g. 3 seconds = 3 / stepDelta)
     */
    public TrajectoryEngine(float stepDelta, int maxSteps) {
        this.stepDelta = stepDelta;
        this.points = new Vector2[maxSteps + 1];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Vector2();
        }
    }

    /**
     * Predicts the path of {@code body} if it were given {@code launchVelocity} right before
     * the next world step. Uses the body's own gravity scale and damping and the world's gravity.
     * Stops at the first fixture the bird would hit (ignoring the bird itself and sensors).
     *
     * @param radius bird radius, used to catch obstacles the centre line would just graze; 0 to disable
     * @return the number of predicted points
     */
    public int simulate(World world, Body body, Vector2 launchVelocity, float radius) {
        return simulate(body.getPosition(), launchVelocity, world.getGravity(),
            body.getGravityScale(), body.getLinearDamping(), world, body, radius);
    }

    /**
     * Predicts a path from raw parameters. {@code world} may be null to skip collision checks.
     */
    public int simulate(Vector2 start, Vector2 launchVelocity, Vector2 worldGravity, float gravityScale,
                        float linearDamping, World world, Body ignore, float radius) {
        position.set(start);
        velocity.set(launchVelocity);
        gravity.set(worldGravity).scl(gravityScale);
        float damping = 1f / (1f + stepDelta * linearDamping);
        float maxTranslation2 = MAX_TRANSLATION * MAX_TRANSLATION;

        hit = false;
        pointCount = 0;
        points[pointCount++].set(position);

        while (pointCount < points.length) {
            velocity.mulAdd(gravity, stepDelta).scl(damping);

            float tx = velocity.x * stepDelta;
            float ty = velocity.y * stepDelta;
            float translation2 = tx * tx + ty * ty;
            if (translation2 > maxTranslation2) {
                velocity.scl(MAX_TRANSLATION / (float) Math.sqrt(translation2));
            }

            next.set(position).mulAdd(velocity, stepDelta);

            if (world != null && !position.epsilonEquals(next)) {
                float length = direction.set(next).sub(position).len();
                direction.scl(1f / length);
                float travel = castSegment(world, ignore, position, radius, length);
                if (travel < length) {
                    hit = true;
                    hitPoint.set(position).mulAdd(direction, Math.max(0f, travel));
                    points[pointCount++].set(hitPoint);
                    break;
                }
            }

            position.set(next);
            points[pointCount++].set(position);
        }

        return pointCount;
    }

    /**
     * Approximates sweeping the bird's circle along {@code direction}: a centre ray that reaches
     * one radius ahead (for the leading edge) plus two rays offset sideways by the radius
     * (for grazing hits). Returns how far the centre can travel before the bird touches
     * something, or infinity if nothing was hit.
     */
    private float castSegment(World world, Body ignore, Vector2 from, float radius, float length) {
        float reach = length + radius;
        float travel = castRay(world, ignore, from, rayEnd.set(from).mulAdd(direction, reach)) * reach - radius;

        if (radius > 0f) {
            offset.set(direction).rotate90(1).scl(radius);
            for (int side = -1; side <= 1; side += 2) {
                rayStart.set(from).mulAdd(offset, side);
                rayEnd.set(rayStart).mulAdd(direction, length);
                travel = Math.min(travel, castRay(world, ignore, rayStart, rayEnd) * length);
            }
        }

        return travel;
    }

    /** Returns the fraction along the ray of the closest solid hit, or infinity if nothing was hit. */
    private float castRay(World world, Body ignore, Vector2 from, Vector2 to) {
        rayCallback.reset(ignore);
        world.rayCast(rayCallback, from, to);
        return rayCallback.hit ? rayCallback.closest : Float.POSITIVE_INFINITY;
    }

    /**
     * Draws the predicted path as fading dots. Set the renderer's projection matrix
     * to the world camera before calling.
     *
     * @param dotSpacing draw one dot every this many steps
     */
    public void render(ShapeRenderer shapes, Color color, float dotRadius, int dotSpacing) {
        if (pointCount < 2) return;

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapes.begin(ShapeRenderer.ShapeType.Filled);

        for (int i = dotSpacing; i < pointCount; i += dotSpacing) {
            float t = (float) i / pointCount;
            shapes.setColor(color.r, color.g, color.b, color.a * (1f - t));
            shapes.circle(points[i].x, points[i].y, dotRadius * (1f - 0.5f * t), 12);
        }

        shapes.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Turns a slingshot pull into a launch velocity: the bird flies opposite to the pull,
     * faster the further it is pulled, up to {@code maxPull}.
     */
    public static Vector2 launchVelocity(Vector2 anchor, Vector2 pulledTo, float power, float maxPull, Vector2 out) {
        return out.set(anchor).sub(pulledTo).limit(maxPull).scl(power);
    }

    public Vector2 getPoint(int index) {
        return points[index];
    }

    public int getPointCount() {
        return pointCount;
    }

    /** Whether the last simulation ended on a collision. */
    public boolean hasHit() {
        return hit;
    }

    public Vector2 getHitPoint() {
        return hitPoint;
    }

    private static class ClosestHitCallback implements RayCastCallback {
        private Body ignore;
        float closest;
        boolean hit;

        void reset(Body ignore) {
            this.ignore = ignore;
            this.closest = 1f;
            this.hit = false;
        }

        @Override
        public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
            if (fixture.isSensor() || fixture.getBody() == ignore) {
                return -1f; // skip this fixture, keep the ray going
            }
            if (fraction < closest) {
                closest = fraction;
                hit = true;
            }
            return fraction; // clip the ray so only closer hits are reported
        }
    }
}
