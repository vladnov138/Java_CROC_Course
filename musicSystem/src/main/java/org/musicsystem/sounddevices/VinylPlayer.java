package org.musicsystem.sounddevices;

import org.musicsystem.songs.Song;
import org.musicsystem.storages.Storage;
import org.musicsystem.storages.Vinyl;

/**
 * Класс виниловой вертушки
 */
public class VinylPlayer extends SoundDevice {
    private static final String name = "Виниловая вертушка";
    public VinylPlayer() {
        super(name);
    }

    /**
     * Проигрывает все треки с носителя. Поддерживает только Виниловые пластинки (класс Vinyl).
     * @param storage носитель
     * @throws ClassCastException носитель не совместим с текущим плеером
     */
    public void playMusic(Storage storage) throws ClassCastException {
        if (storage instanceof Vinyl) {
            super.playMusic(storage);
        } else {
            throw new ClassCastException("Invalid type of storage");
        }
    }
}
