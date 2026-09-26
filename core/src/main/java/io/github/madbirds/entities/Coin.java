package io.github.madbirds.entities;

public class Coin {
    private CoinType type;

    public Coin() {
        this.type = CoinType.NORMAL;
    }

    public Coin(CoinType type) {
        this.type = type;
    }

    public CoinType getType() {
        return type;
    }
}
