package org.musicsystem;

import org.musicsystem.exceptions.NoFreeStoragePlace;
import org.musicsystem.songs.Song;
import org.musicsystem.sounddevices.MusicCenter;
import org.musicsystem.sounddevices.MusicColumn;
import org.musicsystem.sounddevices.VinylPlayer;
import org.musicsystem.storages.CD;
import org.musicsystem.storages.FlashDrive;
import org.musicsystem.storages.Vinyl;

public class Main {
    public static void main(String[] args) {
        Song song1 = new Song("Creep", "R3HAB");
        Song song2 = new Song("One day", "Arash&Helen");
        System.out.println("Проигрывание музыки с флешки");
        FlashDrive flashDrive = new FlashDrive("flashka name");
        try {
            flashDrive.addMusic(song1);
            flashDrive.addMusic(song2);
        } catch (NoFreeStoragePlace e) {
            System.out.println("Нет свободного места");
        }
        System.out.println("Музыка добавлена.");
        System.out.println("Пробуем проиграть музыку через музыкальную колонку");
        MusicColumn musicColumn = new MusicColumn();
        musicColumn.playMusic(flashDrive);
        System.out.println("Пробуем проиграть музыку через виниловую вертушку");
        VinylPlayer vinylPlayer = new VinylPlayer();
        try {
            vinylPlayer.playMusic(flashDrive);
        } catch (ClassCastException exception) {
            System.out.println("Несовместимый тип");
        }
        System.out.println("");

        System.out.println("Проигрывание музыки с CD диска");
        CD cd = new CD("cd name");
        try {
            cd.addMusic(song1);
            cd.addMusic(song2);
        } catch (NoFreeStoragePlace e) {
            System.out.println("Нет свободного места");
        }
        System.out.println("Музыка добавлена.");
        System.out.println("Пробуем проиграть музыку через музыкальную колонку");
        try {
            musicColumn.playMusic(cd);
        } catch (ClassCastException exception) {
            System.out.println("Несовместимый тип");
        }
        System.out.println("Пробуем проиграть музыку через музыкальный центр");
        MusicCenter musicCenter = new MusicCenter();
        musicCenter.playMusic(cd);
        System.out.println("");

        System.out.println("Проигрывание музыки с виниловой пластинки: ");
        Vinyl vinyl = new Vinyl("vinyl name retro");
        try {
            vinyl.addMusic(song1);
        } catch (NoFreeStoragePlace e) {
            System.out.println("Нет свободного места");
        }
        System.out.println("Музыка добавлена");
        System.out.println("Пытаемся проиграть музыку через музыкальную колонку:");
        try {
            musicColumn.playMusic(vinyl);
        } catch (ClassCastException exception) {
            System.out.println("Несовместимый тип");
        }
        System.out.println("Пытаемся проиграть музыку через виниловую вертушку:");
        vinylPlayer.playMusic(vinyl);
    }
}