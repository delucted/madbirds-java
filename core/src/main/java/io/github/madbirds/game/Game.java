package io.github.madbirds.game;

import com.badlogic.gdx.utils.ScreenUtils;
import io.github.madbirds.entities.Bird;
import io.github.madbirds.entities.Coin;
import io.github.madbirds.entities.CoinType;

import java.util.ArrayDeque;
import java.util.List;

public class Game {
    public static final float STEP_DELTA = 1f / 120;

    private int score;
    private ArrayDeque<Bird> birds;

    public void create() {

    }

    public Game() {
        score = 0;
    }

    public Game(List<Bird> birds) {
        this.birds = new ArrayDeque<>(birds);
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

    private void incScore(int n) {
        score += n;
    }

    public void consumeCoin(Coin coin) {
        int coinValue = coin.getType() == CoinType.SILVER ? 50 : 100;
        incScore(coinValue);
    }

    public Bird getBird() {
        if (birds.isEmpty()) {
            throw new OutOfBirdsException("Player is out of birds.");
        }

        return birds.removeFirst();
    }
}
