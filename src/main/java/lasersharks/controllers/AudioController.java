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
        musicPlayer.play();
        Options.getInstance().setPlayingMusic(true);
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

  public boolean resumeMusic() {
    if (musicPlayer != null) {
      musicPlayer.play();
      return true;
    }
    return false;
  }
  
  public void muteAll() {
    muteMusic();
    muteSfx();
  }
  
  public void unMuteAll() {
    unMuteMusic();
    unMuteSfx();
  }

  public void muteMusic() {
    musicPlayer.stop();
    Options.getInstance().setMutedMusic(true);
  }

  public void unMuteMusic() {
    resumeMusic();
    Options.getInstance().setMutedMusic(false);
  }

  public void muteSfx() {
    Options.getInstance().setMutedSfx(true);
  }

  public void unMuteSfx() {
    Options.getInstance().setMutedSfx(false);
  }
}
