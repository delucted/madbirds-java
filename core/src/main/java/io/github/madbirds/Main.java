package io.github.madbirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import io.github.madbirds.game.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private final Game game = new Game();
    private float accumulation = 0f;

    @Override
    public void create() {
        game.create();
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        accumulation = Math.min(accumulation + delta, 0.2f);

        while (accumulation >= Game.STEP_DELTA) {
            accumulation -= Game.STEP_DELTA;
            game.step();
        }
        game.update();

        game.render();
    }

    @Override
    public void dispose() {
        game.dispose();
    }
}
