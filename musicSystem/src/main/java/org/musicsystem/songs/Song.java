package org.musicsystem;

abstract public class Song {
    String name;
    String singer;

    public Song(String name, String singer) {
        this.name = name;
        this.singer = singer;
    }
}
