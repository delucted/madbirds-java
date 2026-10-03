package io.github.madbirds.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import io.github.madbirds.state.Physics;
import io.github.madbirds.tools.Callable;
import io.github.madbirds.tools.InterpolatedBody;

public class Coin extends InterpolatedBody {
    public static final float RADIUS = 0.6f;
    public static final float DENSITY = 0.1f;

    private final CoinType type;

    public Coin(CoinType type, Vector2 position) {
        super(createBody(type, position));
        this.type = type;
    }

    private static Body createBody(CoinType type, Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.bullet = true;
        bodyDef.position.set(position);
        Body body = Physics.getWorld().createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(RADIUS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = DENSITY;
        fixtureDef.friction = 0.8f;
        fixtureDef.restitution = 0f;

        body.createFixture(fixtureDef);
        return body;
    }

    public CoinType getType() {
        return type;
    }
}
