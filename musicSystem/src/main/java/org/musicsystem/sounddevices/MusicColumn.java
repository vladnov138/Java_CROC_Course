package org.musicsystem.sounddevices;

import org.musicsystem.storages.CD;
import org.musicsystem.storages.FlashDrive;
import org.musicsystem.storages.Storage;

/**
 * Класс музыкальной колонки
 */
public class MusicColumn extends SoundDevice {
    private static final String name = "Музыкальная колонка";
    public MusicColumn() {
        super(name);
    }

    /**
     * Проигрывает все треки с носителя. Поддерживает только Флешки (класс FlashDrive).
     * @param storage носитель
     * @throws ClassCastException носитель не совместим с текущим плеером
     */
    public void playMusic(Storage storage) throws ClassCastException {
        if (storage instanceof FlashDrive) {
            super.playMusic(storage);
        } else {
            throw new ClassCastException("Invalid type of storage");
        }
    }
}
