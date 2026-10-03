package io.github.madbirds.entities;

import io.github.madbirds.entities.creature.bird.Bird;
import io.github.madbirds.tools.Callable;

import java.util.ArrayList;

public class Level implements Callable {
    private ArrayList<Bird> birds;

    Level(ArrayList<Bird> birds) {

    }

    @Override
    public void step() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }

    public ArrayList<Bird> getBirds() {
        return birds;
    }
}
