package org.musicsystem.sounddevices;

import org.musicsystem.storages.CD;
import org.musicsystem.storages.FlashDrive;
import org.musicsystem.storages.Storage;
import org.musicsystem.storages.Vinyl;

public class UniversalMusicPlayer extends SoundDevice {
    private static String name = "Музыкальный центр";
    public UniversalMusicPlayer() {
        super(name);
    }

    /**
     * Проигрывает все треки с носителя. Поддерживает все типы носителей информации (музыки)
     * @param storage носитель
     */
    public void playMusic(Storage storage) {
        super.playMusic(storage);
    }
}
