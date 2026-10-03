package io.github.madbirds.entities;

import io.github.madbirds.entities.entity.Entity;

public class Block extends Entity {
    private int width;
    private int height;

    public Block(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
