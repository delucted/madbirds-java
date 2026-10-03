package io.github.madbirds.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.EnumMap;
import java.util.Map;

public class AudioPlayer {
    private static final Map<Audio, Sound> sounds = new EnumMap<>(Audio.class);

    public static void play(Audio sfx) {
        play(sfx, 1f);
    }

    public static void play(Audio sfx, float volume) {
        Sound sound = sounds.get(sfx);
        if (sound == null) {
            sound = Gdx.audio.newSound(Gdx.files.internal(sfx.getPath()));
            sounds.put(sfx, sound);
        }
        sound.play(volume);
    }

    public static void dispose() {
        for (Sound sound : sounds.values()) {
            sound.dispose();
        }
        sounds.clear();
    }
}
