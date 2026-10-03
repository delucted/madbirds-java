package io.github.madbirds.entities;

public class Block  {
    private BlockType type;
    private int width;
    private int height;

    public Block(BlockType type, int width, int height) {
        this.type = type;
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
