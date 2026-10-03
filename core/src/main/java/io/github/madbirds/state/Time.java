package io.github.madbirds.state;

import com.badlogic.gdx.Gdx;

public class Time {
    public static float STEP_DELTA = 1f / 60f;
    public static float MAX_STEP_DELTA = 1f / 5f;

    private static float delta = 0f;
    private static float accumulation = 0f;
    private static float alpha = 0f;
    private static float elapsed = 0f;
    private static float scale = 1f;

    public static int update() {
        delta = Gdx.graphics.getDeltaTime() * scale;
        accumulation = Math.min(accumulation + delta, MAX_STEP_DELTA);
        elapsed += delta;

        int count = 0;

        while (accumulation >= STEP_DELTA) {
            accumulation -= STEP_DELTA;
            count++;
        }

        alpha = accumulation / STEP_DELTA;

        return count;
    }

    public static void setScale(float scale) {
        Time.scale = Math.clamp(scale, 0f, 100f);
    }

    public static float getDelta() {
        return delta;
    }

    public static float getAccumulation() {
        return accumulation;
    }

    public static float getAlpha() {
        return alpha;
    }

    public static float getElapsed() {
        return elapsed;
    }

    public static float getScale() {
        return scale;
    }
}
