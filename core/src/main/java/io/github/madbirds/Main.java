package io.github.madbirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import io.github.madbirds.game.Game;
import io.github.madbirds.state.Physics;
import io.github.madbirds.state.Time;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private final Game game = new Game();

    @Override
    public void create() {
        game.create();
    }

    @Override
    public void render() {
        int count = Time.update();
        for (int i = 0; i < count; i++) {
            Physics.step();
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
