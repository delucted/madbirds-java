package io.github.madbirds.tools;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class InterpolatedBody {
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

    void update() {
        interpolatedRotation = MathUtils.lerpAngle(previousRotation, currentRotation, progress);
    }

    void step() {

    }
}
