package io.github.madbirds.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Graphics {
    private static SpriteBatch batch;

    public static void init() {
        batch = new SpriteBatch();
    }

    public static void dispose() {
        batch.dispose();
    }

    public static SpriteBatch getSpriteBatch() {
        return batch;
    }
}
