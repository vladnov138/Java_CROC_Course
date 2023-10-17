package org.musicsystem.storages;

import org.musicsystem.exceptions.NoFreeStoragePlace;
import org.musicsystem.songs.Song;

/**
 * Абстрактный класс носителя информации (музыки)
 * */
abstract public class Storage {
    private final int capacity; // Объем накопителя
    private int size = 0; // Кол-во текущих элементов в массиве
    private final Song[] songs; // массив песен

    /**
     * Инициализирует массив песен
     * @param capacity - объем накопителя
     */
    protected Storage(int capacity) {
        this.capacity = capacity;
        songs = new Song[capacity];
    }

    /**
     * Добавляет песню в массив если есть место, иначе возвращает exception
     * @param song - песня
     * @throws NoFreeStoragePlace - Кастомный exception, который сигнализирует, что нет места на накопителе
     */
    public void addMusic(Song song) throws NoFreeStoragePlace {
        if (size < capacity) {
            for (int i = 0; i < capacity; i++) { // Вставляет в первое попавшее свободное место
                if (songs[i] == null) {
                    songs[i] = song;
                    size++;
                    break;
                }
            }
        } else {
            throw new NoFreeStoragePlace();
        }
    }

    /**
     * Удаляет музыку по индексу
     * @param index - индекс элемента
     * @throws IndexOutOfBoundsException - если индекс выходит за пределы.
     */
    public void removeMusic(int index) throws IndexOutOfBoundsException {
        if (index < capacity && index >= 0) {
            songs[index] = null;
            size--;
        } else {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    /**
     * Возвращает массив песен
     * @return songs - массив песен на накопителе
     */
    public Song[] getMusics() {
        return songs;
    }

    /**
     * Геттер size
     * @return size - кол-во треков на носителе информации
     */
    public int getCurrentSize() { return size; }

    public int getCapacity() { return capacity; }
}
