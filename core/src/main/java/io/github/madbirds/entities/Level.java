package io.github.madbirds.entities;

import java.util.ArrayList;

public class Level {
    private final ArrayList<Entity> entities;

    Level(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    public void renderAllEntities() {
        for (Entity tit : entities) {  // tit is short for Entity
            tit.render();
        }
    }
}
