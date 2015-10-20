/**
 * 
 */
package lasersharks.controllers;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lasersharks.Logger;
import lasersharks.Options;

/**
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public class AudioController {

  private static AudioController instance;

  private MediaPlayer musicPlayer;
  private MediaPlayer sfxPlayer;

  /**
   * 
   */
  public AudioController() {
    setInstance(this);
  }

  /**
   * This function plays a music track on repeat.
   * 
   * @param path
   *          the path of the musicfile that should be played.
   */
  public void playMusic(String path) {
    try {
      if (!Options.getInstance().isMutedMusic()) {
        musicPlayer = new MediaPlayer(new Media(new File(path).toURI().toString()));
        musicPlayer.setAutoPlay(true);
        resumeMusic();
      }
    } catch (Exception e) {
      Logger.getInstance().write("MusicPlay failed", e.getMessage());
    }
  }

  /**
   * This function plays a sound effect.
   * 
   * @param path
   *          the path of the sound file that should be played.
   */
  public void playSoundEffect(String path) {
    try {
      if (!Options.getInstance().isMutedSfx()) {
        sfxPlayer = new MediaPlayer(new Media(new File(path).toURI().toString()));
        Options.getInstance().setPlayingMusic(true);
        sfxPlayer.setVolume(Options.getInstance().getSfxVolume());
        sfxPlayer.play();
      }
    } catch (Exception e) {
      Logger.getInstance().write("AudioPlay failed", e.getMessage());
    }
  }

  /**
   * Singleton AudioController instance.
   * 
   * @return the audio controller instance
   */
  public static AudioController getInstance() {
    if (instance == null) {
      return new AudioController();
    }
    return instance;
  }

  /**
   * Sets the Singleton instance for the audio controller.
   * 
   * @param instance
   *          the new instance
   */
  public void setInstance(AudioController instance) {
    AudioController.instance = instance;
  }

  /**
   * Resumes the music play back.
   * 
   * @return false if music Player is not yet initialized.
   */
  public boolean resumeMusic() {
    if (musicPlayer != null) {
      musicPlayer.setVolume(Options.getInstance().getMusicVolume());
      musicPlayer.play();
      Options.getInstance().setPlayingMusic(true);
      return true;
    }
    return false;
  }

  /**
   * Mute music and sound effect playback.
   */
  public void muteAll() {
    muteMusic();
    muteSfx();
  }

  /**
   * Unmute music and sound effect playback.
   */
  public void unmuteAll() {
    unmuteMusic();
    unmuteSfx();
  }

  /**
   * Mute music playback.
   */
  public void muteMusic() {
    musicPlayer.pause();
    Options.getInstance().setMutedMusic(true);
  }

  /**
   * Mute sound effect playback.
   */
  public void unmuteMusic() {
    resumeMusic();
    Options.getInstance().setMutedMusic(false);
  }

  /**
   * Unmute music playback.
   */
  public void muteSfx() {
    Options.getInstance().setMutedSfx(true);
  }

  /**
   * Unmute sound effect playback.
   */
  public void unmuteSfx() {
    Options.getInstance().setMutedSfx(false);
  }

  /**
   * Adjusts the music volume of the musicPlayer and in the {@link Option} Class.
   * 
   * @param newVolume
   *          the new volume, anything above 1 will be changed to 1 and everything below 0 will be 0
   */
  public void adjustMusicVolume(double newVolume) {
    newVolume = Math.min(Math.max(newVolume, 0), 1.0);
    Options.getInstance().setMusicVolume(newVolume);

    if (musicPlayer != null) {
      musicPlayer.setVolume(newVolume);
    }
  }

  /**
   * Adjusts the sound effects volume in the {@link Option} Class.
   * 
   * @param newVolume
   *          the new volume, anything above 1 will be changed to 1 and everything below 0 will be 0
   */
  public void adjustSfxVolume(double newVolume) {
    newVolume = Math.min(Math.max(newVolume, 0), 1.0);
    Options.getInstance().setSfxVolume(newVolume);
  }
}
