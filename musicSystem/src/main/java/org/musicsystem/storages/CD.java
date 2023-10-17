package org.musicsystem.storages;

import org.musicsystem.exceptions.NoFreeStoragePlace;
import org.musicsystem.songs.Song;

public class CD extends Storage {
    private static final int capacity = 3;
    private final String name; // Имя накопителя (устанавливает пользователь)

    /**
     * Constructor
     * @param name - уникальное имя накопителя
     */
    public CD(String name) {
        super(capacity);
        this.name = name;
    }

    @Override
    public String toString() {
        return "CD диск " + name;
    }
}
