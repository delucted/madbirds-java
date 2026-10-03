package io.github.madbirds.entities;
import com.badlogic.gdx.graphics.Color;
import io.github.madbirds.entities.entity.Entity;

public class Bird extends Entity {
    private final double radius;
    private final Color color;

    public Bird(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }
}
