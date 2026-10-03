package io.github.madbirds.audio;

public enum Audio {
    COIN_COLLECT("sfx/coin_collect.mp3"),
    PULLBACK("sfx/pullback.mp3"),
    IMPACT_WOOD("sfx/impact_wood.mp3"),
    IMPACT_GLASS("sfx/impact_glass.mp3"),
    IMPACT_FLESH("sfx/impact_flesh.mp3");

    Audio(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    String path;
}
