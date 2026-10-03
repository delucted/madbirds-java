package io.github.madbirds.tools;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Disposable;
import io.github.madbirds.state.Physics;
import io.github.madbirds.state.Time;

public class InterpolatedBody implements Callable, Disposable {
    private final Body body;
    private Vector2 previousPosition, currentPosition, interpolatedPosition;
    private float previousRotation, currentRotation, interpolatedRotation;

    public InterpolatedBody(Body body) {
        this.body = body;

        Vector2 position = body.getPosition();
        previousPosition = position.cpy();
        currentPosition = position.cpy();
        interpolatedPosition = position.cpy();

        float rotation = body.getAngle();
        previousRotation = rotation;
        currentRotation = rotation;
        interpolatedRotation = rotation;
    }

    @Override
    public void dispose() {
        Physics.getWorld().destroyBody(body);
    }

    @Override
    public void step() {
        previousPosition = currentPosition;
        currentPosition = body.getPosition().cpy();

        previousRotation = currentRotation;
        currentRotation = body.getAngle();
    }

    @Override
    public void update() {
        float alpha = Time.getAlpha();
        interpolatedPosition = previousPosition.cpy().lerp(currentPosition, alpha);
        interpolatedRotation = MathUtils.lerpAngle(previousRotation, currentRotation, alpha);
    }

    @Override
    public void render() {}

    public Body getBody() {
        return body;
    }

    public Vector2 getPreviousPosition() {
        return previousPosition;
    }

    public Vector2 getCurrentPosition() {
        return currentPosition;
    }

    public Vector2 getInterpolatedPosition() {
        return interpolatedPosition;
    }

    public float getPreviousRotation() {
        return previousRotation;
    }

    public float getCurrentRotation() {
        return currentRotation;
    }

    public float getInterpolatedRotation() {
        return interpolatedRotation;
    }
}
