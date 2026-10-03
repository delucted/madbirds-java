package io.github.madbirds.tools;

import com.badlogic.gdx.utils.Disposable;

public interface Callable {
    void step();
    void update();
    void render();
}
