package io.github.madbirds.levels;

import io.github.madbirds.entities.*;
import io.github.madbirds.entities.creature.bird.Bird;

public class LevelOne extends Level {
    private static final float SLINGSHOT_X = 6f;
    private static final float SLINGSHOT_HEIGHT = 4f;
    private static final float SHELTER_X = 28f;

    public LevelOne() {
        super();
        createGround(-10f, 60f);
        slingshot = above(SLINGSHOT_X, SLINGSHOT_HEIGHT);

        float birdRadius = BirdType.RED.getRadius();
        for (int i = 0; i < 3; i++) {
            float x = SLINGSHOT_X - 2.5f - i * (2 * birdRadius + 0.5f);
            birds.add(new Bird(BirdType.RED, above(x, birdRadius)));
        }

        blocks.add(new Block(BlockType.WOOD, above(SHELTER_X - 2f, 1.5f), 1, 3));
        blocks.add(new Block(BlockType.WOOD, above(SHELTER_X + 2f, 1.5f), 1, 3));
        blocks.add(new Block(BlockType.WOOD, above(SHELTER_X, 3.5f), 5, 1));

        blocks.add(new Block(BlockType.GLASS, above(SHELTER_X - 2f, 4.5f), 1, 1));
        blocks.add(new Block(BlockType.GLASS, above(SHELTER_X + 2f, 4.5f), 1, 1));

        coins.add(new Coin(CoinType.GOLD, above(SHELTER_X, 4f + Coin.RADIUS)));
        coins.add(new Coin(CoinType.SILVER, above(SHELTER_X, Coin.RADIUS)));
    }
}
