package org.musicsystem.storages;

import org.musicsystem.exceptions.NoFreeStoragePlace;
import org.musicsystem.songs.Song;

public class Vinyl extends Storage {
    private static final int capacity = 1;
    private final String name; // Имя накопителя (устанавливает пользователь)

    /**
     * Constructor
     * @param name - уникальное имя накопителя
     */
    public Vinyl(String name) {
        super(capacity);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Виниловая пластинка " + name;
    }
}
