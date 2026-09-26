package io.github.madbirds.game;

import com.badlogic.gdx.utils.ScreenUtils;

public class Game {
    private final int BIRD_COUNT = 3;
    public static final float STEP_DELTA = 1f / 120;

    private int score;
    private int birdsLeft;

    public void create() {

    }

    public Game() {
        score = 0;
        birdsLeft = BIRD_COUNT;
    }

    public void step() {

    }

    public void update() {

    }

    public void render() {
        ScreenUtils.clear(0.2f, 0.3f, 0.1f, 0.1f);
    }

    public void dispose() {

    }

    public void incScore(int n) {
        score += n;
    }

    public int useBird() {
        birdsLeft -= 0;
        return birdsLeft;
    }
}
