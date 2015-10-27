package lasersharks.controllers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lasersharks.Logger;
import lasersharks.Options;
import java.io.File;

/**
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public class AudioController {

  private static AudioController instance;

  private MediaPlayer musicPlayer;

  /**
   * The constructor to create a new instance of audio controller.
   */
  protected AudioController() {
    setInstance(this);
  }

  /**
   * This function plays a music track on repeat.
   * 
   * @param path
   *          the path of the music file that should be played.
   * @return true iff music has started Playing
   */
  public boolean playMusic(String path) {
    if (musicPlayer != null) {
      AudioController.instance.musicPlayer.stop();
    }
    try {
      musicPlayer = new MediaPlayer(new Media(new File(path).toURI().toString()));
      musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
      return resumeMusic();

    } catch (Exception e) {
      Logger.getInstance().write("MusicPlay failed", e.getMessage());
    }
    return false;
  }

  /**
   * This function plays a sound effect.
   * 
   * @param path
   *          the path of the sound file that should be played.
   * @return true iff a sound effect has started Playing
   */
  private boolean playSoundEffect(String path) {
    if (Options.getInstance().isMutedSfx()) {
      return false;
    }
    try {
      MediaPlayer sfxPlayer = new MediaPlayer(new Media(new File(path).toURI().toString()));
      sfxPlayer.setVolume(
              Options.getInstance().getSfxVolume() * Options.getInstance().getMasterVolume());
      sfxPlayer.play();
      return true;
    } catch (Exception e) {
      Logger.getInstance().write("sfxPlay failed", e.getMessage());
    }
    return false;
  }

  /**
   * Plays the eat sound.
   * 
   * @return true iff a sound effect has started Playing
   */
  public boolean playEatSoundEffect() {
    return playSoundEffect(Options.getInstance().getEatSoundFileName());
  }

  /**
   * Plays a laserSound.
   * 
   * @return true iff a sound effect has started Playing
   */
  public boolean playLaserSoundEffect() {
    return playSoundEffect(Options.getInstance().getLaserSoundFileName());
  }

  /**
   * Plays the pickup sound.
   * 
   * @return true iff a sound effect has started Playing
   */
  public boolean playPickupSoundEffect() {
    return playSoundEffect(Options.getInstance().getPickupSoundFileName());
  }

  /**
   * Resumes the music play back.
   * 
   * @return false if music Player is not yet initialized.
   */
  public boolean resumeMusic() {
    if (musicPlayer == null) {
      return false;
    }
    if (Options.getInstance().isMutedMusic()) {
      return false;
    }
    musicPlayer.setVolume(
        Options.getInstance().getMusicVolume() * Options.getInstance().getMasterVolume());
    musicPlayer.play();
    Options.getInstance().setPlayingMusic(true);
    return true;
  }

  /**
   * Mute music and sound effect playback.
   */
  public void muteAll() {
    muteMusic();
    muteSfx();
  }

  /**
   * Un-mute music and sound effect playback.
   */
  public void unMuteAll() {
    unMuteMusic();
    unMuteSfx();
  }

  /**
   * Mute music playback.
   */
  public void muteMusic() {
    Options.getInstance().setMutedMusic(true);
    if (musicPlayer != null) {
      musicPlayer.pause();
    }
  }

  /**
   * Un-mute music playback.
   */
  public void unMuteMusic() {
    Options.getInstance().setMutedMusic(false);
    resumeMusic();
  }

  /**
   * Mute sound effect playback.
   */
  public void muteSfx() {
    Options.getInstance().setMutedSfx(true);
  }

  /**
   * Un-mute sound effect playback.
   */
  public void unMuteSfx() {
    Options.getInstance().setMutedSfx(false);
  }

  /**
   * Adjusts the master volume in the {@link Options} Class.
   * 
   * @param newVolume
   *          new volume, anything above 1 will be changed to 1 and everything below 0 will be 0
   */
  public void adjustMasterVolume(double newVolume) {
    newVolume = Math.min(Math.max(newVolume, 0), 1.0);
    Options.getInstance().setMasterVolume(newVolume);

    if (musicPlayer != null) {
      musicPlayer.setVolume(Options.getInstance().getMusicVolume() * newVolume);
    }
  }

  /**
   * Adjusts the music volume of the musicPlayer and in the {@link Options} Class.
   * 
   * @param newVolume
   *          new volume, anything above 1 will be changed to 1 and everything below 0 will be 0
   */
  public void adjustMusicVolume(double newVolume) {
    newVolume = Math.min(Math.max(newVolume, 0), 1.0);
    Options.getInstance().setMusicVolume(newVolume);

    if (musicPlayer != null) {
      musicPlayer.setVolume(newVolume * Options.getInstance().getMasterVolume());
    }
  }

  /**
   * Adjusts the sound effects volume in the {@link Options} Class.
   * 
   * @param newVolume
   *          new volume, anything above 1 will be changed to 1 and everything below 0 will be 0
   */
  public void adjustSfxVolume(double newVolume) {
    newVolume = Math.min(Math.max(newVolume, 0), 1.0);
    Options.getInstance().setSfxVolume(newVolume);
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
  public static void setInstance(AudioController instance) {
    if (AudioController.instance != null) {
      destroyInstance();
    }
    AudioController.instance = instance;
  }

  /**
   * Sets the Singleton instance for the audio controller to null to reset all the settings.
   */
  public static void destroyInstance() {
    if (AudioController.instance.musicPlayer != null) {
      AudioController.instance.musicPlayer.stop();
    }
    AudioController.instance = null;
  }

}
