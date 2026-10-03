package io.github.madbirds.entities;

import com.badlogic.gdx.math.Vector2;

public class Block  {
    private BlockType type;
    private int width;
    private int height;
    private Vector2 position;

    public Block(BlockType type, Vector2 position, int width, int height) {
        this.type = type;
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPosition(Vector2 newPosition) {
        this.position = newPosition;
    }

    public Vector2 getPosition() {
        return position;
    }
}
