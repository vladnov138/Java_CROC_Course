package org.musicsystem.songs;

/**
 * Дата-класс песни/трека
 */
public class Song {
    String name; // имя песни
    String singer; // имя исполнителя

    /**
     * Constructor
     * @param name название песни/трека
     * @param singer исполнитель
     */
    public Song(String name, String singer) {
        this.name = name;
        this.singer = singer;
    }

    @Override
    public String toString() {
        return singer + " - " + name;
    }
}
