package org.musicsystem.sounddevices;

import org.musicsystem.songs.Song;
import org.musicsystem.storages.Storage;

import static java.lang.Thread.sleep;

abstract class SoundDevice {
    private final String name; // Название проигрывателя, плеера

    /**
     * Constructor
     * @param name - название проигрывателя, плеера
     */
    protected SoundDevice(String name) {
        this.name = name;
    }

    /**
     * Проигрывает все треки с носителя с интервалом 1 с
     * @param storage носитель
     */
    protected void playMusic(Storage storage) {
        Song[] songs = storage.getMusics();
        for (Song song : songs) {
            if (song != null){
                System.out.println("Сейчас играет: " + song + "\nНа носителе: " + storage
                        + "\nУстройство воспроизведения: " + name + "\n");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
