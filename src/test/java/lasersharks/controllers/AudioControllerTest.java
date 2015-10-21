package lasersharks.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Dimension;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import lasersharks.Options;

/**
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public class AudioControllerTest {

  private AudioController audioController;
  private Options options;
  private final int size = 100;
  private final double floatDelta = 0.0001f;

  /**
   * 
   */
  @Before
  public void setUp() {
    new JFXPanel();
    options = Options.getInstance();
    Options.getInstance().setDimension(new Dimension(size, size));
    audioController = AudioController.getInstance();
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
  @Test
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
        .playSoundEffect(Options.getInstance().getLaserSoundFileName()));
  }

  /**
   * Test method for
   * {@link lasersharks.controllers.AudioController#playSoundEffect(java.lang.String)}.
   */
  @Test
  public void testPlaySoundEffect() {
    assertTrue(AudioController.getInstance()
        .playSoundEffect(Options.getInstance().getLaserSoundFileName()));
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
  @Test
  public void testResumeMusic() {
    AudioController.getInstance().playMusic(Options.getInstance().getMusicFileName());
    assertTrue(AudioController.getInstance().resumeMusic());
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
   * Test method for {@link lasersharks.controllers.AudioController#unmuteAll()}.
   */
  @Test
  public void testUnmuteAll() {
    AudioController.getInstance().muteAll();
    AudioController.getInstance().unmuteAll();
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
   * Test method for {@link lasersharks.controllers.AudioController#unmuteMusic()}.
   */
  @Test
  public void testUnmuteMusic() {
    AudioController.getInstance().muteMusic();
    AudioController.getInstance().unmuteMusic();
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
   * Test method for {@link lasersharks.controllers.AudioController#unmuteSfx()}.
   */
  @Test
  public void testUnmuteSfx() {
    AudioController.getInstance().muteSfx();
    AudioController.getInstance().unmuteSfx();
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
    assertEquals(audioController, AudioController.getInstance());
  }

  /**
   * Test method for {@link lasersharks.controllers.AudioController#SetInstance(AudioController)} .
   */
  @Test
  public void testSetInstance() {
    AudioController ac = new AudioController();
    assertEquals(ac, AudioController.getInstance());
    AudioController.setInstance(audioController);
    assertEquals(audioController, AudioController.getInstance());
  }

}
