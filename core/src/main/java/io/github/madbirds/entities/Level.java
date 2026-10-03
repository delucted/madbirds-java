package io.github.madbirds.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import io.github.madbirds.entities.creature.bird.Bird;
import io.github.madbirds.state.Physics;
import io.github.madbirds.tools.Callable;

import java.util.ArrayList;

/**
 * Levels are laid out in world units (metres) with the ground surface at {@link #GROUND_Y}.
 * Use {@link #above(float, float)} for positions so layouts stay correct whichever way gravity points.
 */
public class Level implements Callable {
    public static final float GROUND_Y = 0f;
    private static final float GROUND_THICKNESS = 2f;

    /** +1 or -1: the world-y direction that points away from the ground (opposite gravity). */
    protected static final float UP = -Math.signum(Physics.getWorld().getGravity().y);

    private LevelType type;
    protected ArrayList<Coin> coins = new ArrayList<>();
    protected ArrayList<Block> blocks = new ArrayList<>();
    protected ArrayList<Bird> birds = new ArrayList<>();

    /** Where the loaded bird sits in the slingshot pouch, and where launches start from. */
    protected Vector2 slingshot;
    protected Body ground;

    @Override
    public void step() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }

    /** A world position {@code x} across and {@code height} above the ground surface. */
    protected static Vector2 above(float x, float height) {
        return new Vector2(x, GROUND_Y + UP * height);
    }

    /** Creates a static ground slab whose top surface is at {@link #GROUND_Y}, spanning {@code left} to {@code right}. */
    protected void createGround(float left, float right) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(above((left + right) / 2f, -GROUND_THICKNESS / 2f));
        ground = Physics.getWorld().createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((right - left) / 2f, GROUND_THICKNESS / 2f);
        ground.createFixture(shape, 0f).setFriction(0.9f);
        shape.dispose();
    }

    public Vector2 getSlingshot() {
        return slingshot;
    }
}
