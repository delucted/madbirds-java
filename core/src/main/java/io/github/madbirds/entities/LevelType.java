package io.github.madbirds.entities;

import io.github.madbirds.levels.LevelOne;

public enum LevelType {
    ONE(LevelOne.class);

    LevelType(Class<?extends Level> clazz) {
        level = clazz;
    }

    private final Class<?extends Level> level;
}
