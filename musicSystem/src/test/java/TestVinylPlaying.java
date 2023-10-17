import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.musicsystem.exceptions.NoFreeStoragePlace;
import org.musicsystem.songs.Song;
import org.musicsystem.sounddevices.MusicCenter;
import org.musicsystem.sounddevices.MusicColumn;
import org.musicsystem.sounddevices.UniversalMusicPlayer;
import org.musicsystem.sounddevices.VinylPlayer;
import org.musicsystem.storages.Vinyl;

public class TestVinylPlaying {
    @Test
    @DisplayName("Adding song test")
    public void testAddingSong() throws NoFreeStoragePlace {
        Song song = new Song("Creep", "R3HAB");
        Vinyl vinyl = new Vinyl("test vinyl");
        Assertions.assertEquals(0, vinyl.getCurrentSize());
        vinyl.addMusic(song);
        Assertions.assertEquals(1, vinyl.getCurrentSize());
        Assertions.assertNotNull(vinyl.getMusics()[0]);
    }


    @Test
    @DisplayName("Removing song test")
    public void testRemovingSong() throws NoFreeStoragePlace {
        Song song = new Song("Creep", "R3HAB");
        Vinyl vinyl = new Vinyl("test vinyl");
        vinyl.addMusic(song);
        Song[] music = vinyl.getMusics();
        Assertions.assertEquals(1, vinyl.getCurrentSize());
        Assertions.assertNotNull(music[0]);

        vinyl.removeMusic(0);
        music = vinyl.getMusics();
        Assertions.assertEquals(0, vinyl.getCurrentSize());
        Assertions.assertNull(music[0]);
    }

    @Test
    @DisplayName("Test overflow storage")
    public void testOverflowStorage() throws NoFreeStoragePlace {
        Vinyl vinyl = new Vinyl("test vinyl");
        Song song = new Song("Creep", "R3HAB");
        while (vinyl.getCurrentSize() < vinyl.getCapacity()) {
            vinyl.addMusic(song);
        }
        Assertions.assertThrows(NoFreeStoragePlace.class, () -> vinyl.addMusic(song));
    }

    @Test
    @DisplayName("Removing song test index exception")
    public void testRemovingSongThrows() {
        Vinyl vinyl = new Vinyl("test vinyl");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> vinyl.removeMusic(50));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> vinyl.removeMusic(-1));
    }

    @Test
    @DisplayName("Playing musicCenter test")
    public void testMusicCenterPlaying() throws NoFreeStoragePlace {
        MusicCenter musicCenter = new MusicCenter();
        Song song = new Song("Creep", "R3HAB");
        Vinyl vinyl = new Vinyl("test vinyl");
        vinyl.addMusic(song);
        Assertions.assertThrows(ClassCastException.class, () -> musicCenter.playMusic(vinyl));
    }

    @Test
    @DisplayName("Playing musicColumn test")
    public void testMusicColumnPlaying() throws NoFreeStoragePlace {
        MusicColumn musicColumn = new MusicColumn();
        Song song = new Song("Creep", "R3HAB");
        Vinyl vinyl = new Vinyl("test vinyl");
        vinyl.addMusic(song);
        Assertions.assertThrows(ClassCastException.class, () -> musicColumn.playMusic(vinyl));
    }

    @Test
    @DisplayName("Playing universal music player test")
    public void testUniversalMusicPlaying() throws NoFreeStoragePlace {
        UniversalMusicPlayer universalMusicPlayer = new UniversalMusicPlayer();
        Song song = new Song("Creep", "R3HAB");
        Vinyl vinyl = new Vinyl("test vinyl");
        vinyl.addMusic(song);
        universalMusicPlayer.playMusic(vinyl);
    }

    @Test
    @DisplayName("Playing vinylPlayer test")
    public void testVinylPlaying() throws NoFreeStoragePlace {
        VinylPlayer vinylPlayer = new VinylPlayer();
        Song song = new Song("Creep", "R3HAB");
        Vinyl vinyl = new Vinyl("test vinyl");
        vinyl.addMusic(song);
        vinylPlayer.playMusic(vinyl);
    }
}
