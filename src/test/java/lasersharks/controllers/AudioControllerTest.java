package lasersharks.controllers;

import javafx.embed.swing.JFXPanel;
import lasersharks.Options;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author SEMGroup27
 */
@SuppressWarnings("restriction")
public class AudioControllerTest {

  private static final double FLOAT_DELTA = 0.0001f;

  /**
   *
   */
  @Before
  public void setUp() {
    new JFXPanel();
  }

  /**
   *
   */
  @After
  public void tearDown() {
    AudioController.destroyInstance();
    Options.destroyInstance();
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#playMusic(java.lang.String)}.
   */
  @Test
  public void playMusicMutedTest() {
    Options.getInstance().setMutedMusic(true);
    assertFalse(AudioController.getInstance().playMusic(Options.getInstance().getMusicFileName()));
    assertFalse(Options.getInstance().isPlayingMusic());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#playMusic(java.lang.String)}.
   */
  //@Test
  public void playMusicTestTest() {
    assertTrue(AudioController.getInstance().playMusic(Options.getInstance().getMusicFileName()));
    assertTrue(Options.getInstance().isPlayingMusic());
  }

  /**
   * Test method for
   * {@link lasersharks.controllers.AudioController#playSoundEffect(java.lang.String)}.
   */
  @Test
  public void playSoundEffectMutedTest() {
    Options.getInstance().setMutedSfx(true);
    assertFalse(AudioController.getInstance()
        .playEatSoundEffect());
  }

  /**
   * Test method for
   * {@link lasersharks.controllers.AudioController#playSoundEffect(java.lang.String)}.
   */
  //@Test
  public void playSoundEffectTest() {
    assertTrue(AudioController.getInstance()
        .playEatSoundEffect());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#resumeMusic()}.
   */
  @Test
  public void resumeMusicNoPlayerTest() {
    assertFalse(AudioController.getInstance().resumeMusic());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#resumeMusic()}.
   */
  @Test
  public void resumeMusicMutedTest() {
    Options.getInstance().setMutedMusic(true);
    AudioController.getInstance().playMusic(Options.getInstance().getMusicFileName());
    assertFalse(AudioController.getInstance().resumeMusic());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#resumeMusic()}.
   */
  //@Test
  public void resumeMusicTest() {
    AudioController.getInstance().playMusic(Options.getInstance().getMusicFileName());
    AudioController.getInstance().muteMusic(); // To effectively pause the music
    Options.getInstance().setMutedMusic(false); // To un-pause but not yet play the music
    assertTrue(AudioController.getInstance().resumeMusic());
    assertTrue(Options.getInstance().isPlayingMusic());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#muteAll()}.
   */
  @Test
  public void muteAllTest() {
    AudioController.getInstance().muteAll();
    assertTrue(Options.getInstance().isMutedMusic() && Options.getInstance().isMutedSfx());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#unMuteAll()}.
   */
  @Test
  public void unMuteAllTest() {
    AudioController.getInstance().muteAll();
    AudioController.getInstance().unMuteAll();
    assertFalse(Options.getInstance().isMutedMusic() || Options.getInstance().isMutedSfx());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#muteMusic()}.
   */
  @Test
  public void muteMusicTest() {
    AudioController.getInstance().muteMusic();
    assertTrue(Options.getInstance().isMutedMusic());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#unMuteMusic()}.
   */
  @Test
  public void unMuteMusicTest() {
    AudioController.getInstance().muteMusic();
    AudioController.getInstance().unMuteMusic();
    assertFalse(Options.getInstance().isMutedMusic());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#muteSfx()}.
   */
  @Test
  public void muteSfxTest() {
    AudioController.getInstance().muteSfx();
    assertTrue(Options.getInstance().isMutedSfx());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#unMuteSfx()}.
   */
  @Test
  public void unMuteSfxTest() {
    AudioController.getInstance().muteSfx();
    AudioController.getInstance().unMuteSfx();
    assertFalse(Options.getInstance().isMutedSfx());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#adjustMusicVolume(double)}.
   */
  @Test
  public void adjustMusicVolumeAboveOneTest() {
    AudioController.getInstance().adjustMusicVolume(2.0);
    assertEquals(1.0f, Options.getInstance().getMusicVolume(), FLOAT_DELTA);
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#adjustMusicVolume(double)}.
   */
  @Test
  public void adjustMusicVolumeBelowZeroTest() {
    AudioController.getInstance().adjustMusicVolume(-1.0);
    assertEquals(0.0f, Options.getInstance().getMusicVolume(), FLOAT_DELTA);
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#adjustMusicVolume(double)}.
   */
  @Test
  public void adjustMusicVolumeBetweenZeroAndOneTest() {
    final double newVolume = 0.5;
    AudioController.getInstance().adjustMusicVolume(newVolume);
    assertEquals(newVolume, Options.getInstance().getMusicVolume(), FLOAT_DELTA);
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#adjustSfxVolume(double)}.
   */
  @Test
  public void adjustSfxVolumeAboveOneTest() {
    AudioController.getInstance().adjustSfxVolume(2.0);
    assertEquals(1.0f, Options.getInstance().getSfxVolume(), FLOAT_DELTA);
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#adjustSfxVolume(double)}.
   */
  @Test
  public void adjustSfxVolumeBelowZeroTest() {
    AudioController.getInstance().adjustSfxVolume(-1.0);
    assertEquals(0.0f, Options.getInstance().getSfxVolume(), FLOAT_DELTA);
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#adjustSfxVolume(double)}.
   */
  @Test
  public void adjustSfxVolumeBetweenZeroAndOneTest() {
    final double newVolume = 0.5;
    AudioController.getInstance().adjustSfxVolume(newVolume);
    assertEquals(newVolume, Options.getInstance().getSfxVolume(), FLOAT_DELTA);
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#getInstance()}.
   */
  @Test
  public void getInstanceTest() {
    final AudioController ac = AudioController.getInstance();
    assertEquals(ac, AudioController.getInstance());
  }

}
