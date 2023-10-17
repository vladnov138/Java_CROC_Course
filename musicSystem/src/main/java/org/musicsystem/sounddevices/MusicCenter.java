package org.musicsystem.sounddevices;

import org.musicsystem.storages.CD;
import org.musicsystem.storages.FlashDrive;
import org.musicsystem.storages.Storage;
import org.musicsystem.storages.Vinyl;

/**
 * Класс музыкального центра
 */
public class MusicCenter extends SoundDevice {
    private static String name = "Музыкальный центр";
    public MusicCenter() {
        super(name);
    }

    /**
     * Проигрывает все треки с носителя. Поддерживает только Флешки (класс FlashDrive).
     * @param storage носитель
     * @throws ClassCastException носитель не совместим с текущим плеером
     */
    public void playMusic(Storage storage) {
        if (storage instanceof CD || storage instanceof FlashDrive) {
            super.playMusic(storage);
        } else {
            throw new ClassCastException("Invalid type of storage");
        }
    }
}
