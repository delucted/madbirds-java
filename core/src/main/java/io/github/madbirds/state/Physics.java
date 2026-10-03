package io.github.madbirds.state;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Physics {
    private static final World world = new World(new Vector2(0f, 16f), false);

    public static void step() {
        world.step(Time.STEP_DELTA, 3, 8);
    }

    public static World getWorld() {
        return world;
    }
}
