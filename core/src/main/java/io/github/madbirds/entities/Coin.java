package io.github.madbirds.entities;

import io.github.madbirds.entities.entity.Entity;

public class Coin extends Entity {
    private CoinType type;

    public Coin() {
        this.type = CoinType.SILVER;
    }

    public Coin(CoinType type) {
        this.type = type;
    }

    public CoinType getType() {
        return type;
    }
}
