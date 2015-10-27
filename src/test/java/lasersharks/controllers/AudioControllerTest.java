package lasersharks.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import javafx.embed.swing.JFXPanel;
import lasersharks.Options;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public class AudioControllerTest {

  private final double floatDelta = 0.0001f;

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
  public void testPlayMusicMuted() {
    Options.getInstance().setMutedMusic(true);
    assertFalse(AudioController.getInstance().playMusic(Options.getInstance().getMusicFileName()));
    assertFalse(Options.getInstance().isPlayingMusic());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#playMusic(java.lang.String)}.
   */
  //@Test
  public void testPlayMusic() {
    assertTrue(AudioController.getInstance().playMusic(Options.getInstance().getMusicFileName()));
    assertTrue(Options.getInstance().isPlayingMusic());
  }

  /**
   * Test method for
   * {@link lasersharks.controllers.AudioController#playSoundEffect(java.lang.String)}.
   */
  @Test
  public void testPlaySoundEffectMuted() {
    Options.getInstance().setMutedSfx(true);
    assertFalse(AudioController.getInstance()
        .playEatSoundEffect());
  }

  /**
   * Test method for
   * {@link lasersharks.controllers.AudioController#playSoundEffect(java.lang.String)}.
   */
  //@Test
  public void testPlaySoundEffect() {
    assertTrue(AudioController.getInstance()
        .playEatSoundEffect());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#resumeMusic()}.
   */
  @Test
  public void testResumeMusicNoPlayer() {
    assertFalse(AudioController.getInstance().resumeMusic());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#resumeMusic()}.
   */
  @Test
  public void testResumeMusicMuted() {
    Options.getInstance().setMutedMusic(true);
    AudioController.getInstance().playMusic(Options.getInstance().getMusicFileName());
    assertFalse(AudioController.getInstance().resumeMusic());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#resumeMusic()}.
   */
  //@Test
  public void testResumeMusic() {
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
  public void testMuteAll() {
    AudioController.getInstance().muteAll();
    assertTrue(Options.getInstance().isMutedMusic() && Options.getInstance().isMutedSfx());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#unMuteAll()}.
   */
  @Test public void testUnMuteAll() {
    AudioController.getInstance().muteAll();
    AudioController.getInstance().unMuteAll();
    assertFalse(Options.getInstance().isMutedMusic() || Options.getInstance().isMutedSfx());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#muteMusic()}.
   */
  @Test
  public void testMuteMusic() {
    AudioController.getInstance().muteMusic();
    assertTrue(Options.getInstance().isMutedMusic());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#unMuteMusic()}.
   */
  @Test public void testUnMuteMusic() {
    AudioController.getInstance().muteMusic();
    AudioController.getInstance().unMuteMusic();
    assertFalse(Options.getInstance().isMutedMusic());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#muteSfx()}.
   */
  @Test
  public void testMuteSfx() {
    AudioController.getInstance().muteSfx();
    assertTrue(Options.getInstance().isMutedSfx());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#unMuteSfx()}.
   */
  @Test public void testUnMuteSfx() {
    AudioController.getInstance().muteSfx();
    AudioController.getInstance().unMuteSfx();
    assertFalse(Options.getInstance().isMutedSfx());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#adjustMusicVolume(double)}.
   */
  @Test
  public void testAdjustMusicVolumeAboveOne() {
    AudioController.getInstance().adjustMusicVolume(2.0);
    assertEquals(1.0f, Options.getInstance().getMusicVolume(), floatDelta);
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#adjustMusicVolume(double)}.
   */
  @Test
  public void testAdjustMusicVolumeBelowZero() {
    AudioController.getInstance().adjustMusicVolume(-1.0);
    assertEquals(0.0f, Options.getInstance().getMusicVolume(), floatDelta);
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#adjustMusicVolume(double)}.
   */
  @Test
  public void testAdjustMusicVolumeBetweenZeroAndOne() {
    final double newVolume = 0.5;
    AudioController.getInstance().adjustMusicVolume(newVolume);
    assertEquals(newVolume, Options.getInstance().getMusicVolume(), floatDelta);
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#adjustSfxVolume(double)}.
   */
  @Test
  public void testAdjustSfxVolumeAboveOne() {
    AudioController.getInstance().adjustSfxVolume(2.0);
    assertEquals(1.0f, Options.getInstance().getSfxVolume(), floatDelta);
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#adjustSfxVolume(double)}.
   */
  @Test
  public void testAdjustSfxVolumeBelowZero() {
    AudioController.getInstance().adjustSfxVolume(-1.0);
    assertEquals(0.0f, Options.getInstance().getSfxVolume(), floatDelta);
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#adjustSfxVolume(double)}.
   */
  @Test
  public void testAdjustSfxVolumeBetweenZeroAndOne() {
    final double newVolume = 0.5;
    AudioController.getInstance().adjustSfxVolume(newVolume);
    assertEquals(newVolume, Options.getInstance().getSfxVolume(), floatDelta);
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#getInstance()}.
   */
  @Test
  public void testGetInstance() {
    AudioController audioController = new AudioController();
    assertEquals(audioController, AudioController.getInstance());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#setInstance(AudioController)} .
   */
  @Test
  public void testSetInstance() {
    AudioController audioController = new AudioController();
    AudioController ac = new AudioController();
    assertEquals(ac, AudioController.getInstance());
    AudioController.setInstance(audioController);
    assertEquals(audioController, AudioController.getInstance());
  }

}
