package io.github.madbirds.entities.creature.bird;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import io.github.madbirds.entities.BirdType;
import io.github.madbirds.state.Physics;
import io.github.madbirds.tools.Callable;
import io.github.madbirds.tools.InterpolatedBody;

public class Bird extends InterpolatedBody implements Callable {
    private final BirdRendering rendering = new BirdRendering();
    private final BirdType type;

    public Bird(BirdType type) {
        super(createBody(type));
        this.type = type;
    }

    private static Body createBody(BirdType type) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.bullet = true;
        Body body = Physics.getWorld().createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(type.getRadius());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = type.getDensity();
        fixtureDef.friction = 0.9f;
        fixtureDef.restitution = 0.06f;

        body.createFixture(fixtureDef);
        return body;
    }

    public BirdType getType() {
        return type;
    }
}
