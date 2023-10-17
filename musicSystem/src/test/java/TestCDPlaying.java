import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.musicsystem.exceptions.NoFreeStoragePlace;
import org.musicsystem.songs.Song;
import org.musicsystem.sounddevices.MusicCenter;
import org.musicsystem.sounddevices.MusicColumn;
import org.musicsystem.sounddevices.UniversalMusicPlayer;
import org.musicsystem.sounddevices.VinylPlayer;
import org.musicsystem.storages.CD;

public class TestCDPlaying {
    @Test
    @DisplayName("Adding song test")
    public void testAddingSong() throws NoFreeStoragePlace {
        Song song = new Song("Creep", "R3HAB");
        CD cd = new CD("test cd");
        Assertions.assertEquals(0, cd.getCurrentSize());
        cd.addMusic(song);
        Assertions.assertEquals(1, cd.getCurrentSize());
        Assertions.assertNotNull(cd.getMusics()[0]);
    }


    @Test
    @DisplayName("Removing song test")
    public void testRemovingSong() throws NoFreeStoragePlace {
        Song song = new Song("Creep", "R3HAB");
        CD cd = new CD("test cd");
        cd.addMusic(song);
        cd.removeMusic(0);
        Song[] music = cd.getMusics();
        Assertions.assertEquals(0, cd.getCurrentSize());
        for (int i = 0; i < cd.getCapacity(); i++) {
            Assertions.assertNull(music[i]);
        }
    }

    @Test
    @DisplayName("Playing musicCenter test")
    public void testMusicCenterPlaying() throws NoFreeStoragePlace {
        MusicCenter musicCenter = new MusicCenter();
        Song song = new Song("Creep", "R3HAB");
        CD cd = new CD("test cd");
        cd.addMusic(song);
        musicCenter.playMusic(cd);
    }

    @Test
    @DisplayName("Playing musicColumn test")
    public void testMusicColumnPlaying() throws NoFreeStoragePlace {
        MusicColumn musicColumn = new MusicColumn();
        Song song = new Song("Creep", "R3HAB");
        CD cd = new CD("test cd");
        cd.addMusic(song);
        Assertions.assertThrows(ClassCastException.class, () -> musicColumn.playMusic(cd));
    }

    @Test
    @DisplayName("Playing universal music player test")
    public void testUniversalMusicPlaying() throws NoFreeStoragePlace {
        UniversalMusicPlayer universalMusicPlayer = new UniversalMusicPlayer();
        Song song1 = new Song("Creep", "R3HAB");
        Song song2 = new Song("One day", "Arash&Helen");
        CD cd = new CD("test cd");
        cd.addMusic(song1);
        cd.addMusic(song2);
        universalMusicPlayer.playMusic(cd);
    }

    @Test
    @DisplayName("Playing vinylPlayer test")
    public void testVinylPlaying() throws NoFreeStoragePlace {
        VinylPlayer vinylPlayer = new VinylPlayer();
        Song song = new Song("Creep", "R3HAB");
        CD cd = new CD("test cd");
        cd.addMusic(song);
        Assertions.assertThrows(ClassCastException.class, () -> vinylPlayer.playMusic(cd));
    }
}
