package org.musicsystem.storages;

public class FlashDrive extends Storage {
    private static final int capacity = 5;
    private final String name; // Имя накопителя (устанавливает пользователь)

    /**
     * Constructor
     * @param name - уникальное имя накопителя
     */
    public FlashDrive(String name) {
        super(capacity);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Флеш-накопитель " + name;
    }
}
