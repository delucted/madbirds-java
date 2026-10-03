package io.github.madbirds.entities;

import com.badlogic.gdx.graphics.Color;

public enum BirdType {
    RED(Color.RED, 1f, 1f),
    BLUE(Color.BLUE, 0.7f, 0.8f);

    BirdType(Color color, float radius, float density) {
        this.color = color;
        this.radius = radius;
        this.density = density;
    }

    public Color getColor() {
        return color;
    }

    public float getRadius() {
        return radius;
    }

    public float getDensity() {
        return density;
    }

    private Color color;
    private float radius;
    private float density;
}
