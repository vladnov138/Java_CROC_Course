import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.musicsystem.exceptions.NoFreeStoragePlace;
import org.musicsystem.songs.Song;
import org.musicsystem.sounddevices.MusicCenter;
import org.musicsystem.sounddevices.MusicColumn;
import org.musicsystem.sounddevices.UniversalMusicPlayer;
import org.musicsystem.sounddevices.VinylPlayer;
import org.musicsystem.storages.FlashDrive;

public class TestFlashDrivePlaying {
    @Test
    @DisplayName("Adding song test")
    public void testAddingSong() throws NoFreeStoragePlace {
        Song song = new Song("Creep", "R3HAB");
        FlashDrive flashDrive = new FlashDrive("test flashdrive");
        Assertions.assertEquals(0, flashDrive.getCurrentSize());
        flashDrive.addMusic(song);
        Assertions.assertEquals(1, flashDrive.getCurrentSize());
        Assertions.assertNotNull(flashDrive.getMusics()[0]);
    }


    @Test
    @DisplayName("Removing song test")
    public void testRemovingSong() throws NoFreeStoragePlace {
        Song song = new Song("Creep", "R3HAB");
        FlashDrive flashDrive = new FlashDrive("test flashdrive");
        flashDrive.addMusic(song);
        flashDrive.removeMusic(0);
        Song[] music = flashDrive.getMusics();
        Assertions.assertEquals(0, flashDrive.getCurrentSize());
        for (int i = 0; i < flashDrive.getCapacity(); i++) {
            Assertions.assertNull(music[i]);
        }
    }

    @Test
    @DisplayName("Playing musicCenter test")
    public void testMusicCenterPlaying() throws NoFreeStoragePlace {
        MusicCenter musicCenter = new MusicCenter();
        Song song = new Song("Creep", "R3HAB");
        FlashDrive flashDrive = new FlashDrive("test flashdrive");
        flashDrive.addMusic(song);
        musicCenter.playMusic(flashDrive);
    }

    @Test
    @DisplayName("Playing musicColumn test")
    public void testMusicColumnPlaying() throws NoFreeStoragePlace {
        MusicColumn musicColumn = new MusicColumn();
        Song song = new Song("Creep", "R3HAB");
        FlashDrive flashDrive = new FlashDrive("test flashdrive");
        flashDrive.addMusic(song);
        musicColumn.playMusic(flashDrive);
    }

    @Test
    @DisplayName("Playing universal music player test")
    public void testUniversalMusicPlaying() throws NoFreeStoragePlace {
        UniversalMusicPlayer universalMusicPlayer = new UniversalMusicPlayer();
        Song song = new Song("Creep", "R3HAB");
        FlashDrive flashDrive = new FlashDrive("test flashdrive");
        flashDrive.addMusic(song);
        universalMusicPlayer.playMusic(flashDrive);
    }

    @Test
    @DisplayName("Playing vinylPlayer test")
    public void testVinylPlaying() throws NoFreeStoragePlace {
        VinylPlayer vinylPlayer = new VinylPlayer();
        Song song = new Song("Creep", "R3HAB");
        FlashDrive flashDrive = new FlashDrive("test flashdrive");
        flashDrive.addMusic(song);
        Assertions.assertThrows(ClassCastException.class, () -> vinylPlayer.playMusic(flashDrive));
    }
}
