package io.github.madbirds.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public enum BirdType {
    RED("red", 1f, 1f),
    BLUE("blue", 0.7f, 0.8f);

    BirdType(String textureName, float radius, float density) {
        this.texture = new Texture(Gdx.files.internal("textures/" + textureName + ".png"));
        this.radius = radius;
        this.density = density;
    }

    public Texture getTexture() {
        return texture;
    }

    public float getRadius() {
        return radius;
    }

    public float getDensity() {
        return density;
    }

    private Texture texture;
    private float radius;
    private float density;
}
