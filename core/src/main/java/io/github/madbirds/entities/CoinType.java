package io.github.madbirds.entities;

import com.badlogic.gdx.graphics.Color;

public enum CoinType {
    SILVER(Color.GRAY, 50),
    GOLD(Color.GOLD, 100);

    CoinType(Color color, int amount) {
        this.color = color;
        this.amount = amount;
    }

    public Color getColor() {
        return color;
    }

    public int getAmount() {
        return amount;
    }

    private Color color;
    private int amount;
}
