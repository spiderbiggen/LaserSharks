package lasersharks;

import javafx.scene.paint.Color;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.util.Random;

/**
 * An options object is responsible for handling everything that has to do with options. This
 * includes resolution.
 * 
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public class Options {

  /**
   * The default Width of the screen.
   */
  private static final int DEFAULT_WIDTH = 1920;
  /**
   * The default height of the sceen.
   */
  private static final int DEFAULT_HEIGHT = 1080;
  /**
   * The default music file URI.
   */
  private static final String DEFAULT_MUSIC_FILENAME = "src/main/resources/music.mp3";
  /**
   * The default eat sound file URI.
   */
  private static final String DEFAULT_EAT_SOUND_FILENAME = "src/main/resources/soundEffect1.wav";
  /**
   * The default laser sound file URI.
   */
  private static final String DEFAULT_LASER_SOUND_FILENAME = "src/main/resources/shoot.wav";
  /**
   * The default pickup sound file URI.
   */
  private static final String DEFAULT_PICKUP_SOUND_FILENAME = "src/main/resources/pickup.wav";
  /**
   * The default background image file URI.
   */
  private static final String DEFAULT_BACKGROUND_IMAGE = "somber sea floor.jpg";
  /**
   * The default background colour.
   */
  private static final Color DEFAULT_BACKGROUND_COLOUR = Color.BLUE;
  /**
   * The current Singleton instance.
   */
  private static Options instance;
  /**
   * The dimension of the screen.
   */
  private Dimension dimension;
  /**
   * The current music URI.
   */
  private String musicFileName;
  /**
   * The current eat sound URI.
   */
  private String eatSoundFileName;
  /**
   * The current laser sound URI.
   */
  private String laserSoundFileName;
  /**
   * The current pickup sound URI.
   */
  private String pickupSoundFileName;
  /**
   * If the music is currently playing.
   */
  private boolean playingMusic;
  /**
   * If the music is currently muted.
   */
  private boolean mutedMusic;
  /**
   * If the sound effects are currently muted.
   */
  private boolean mutedSfx;
  /**
   * The current master volume.
   */
  private double masterVolume;
  /**
   * The current music volume.
   */
  private double musicVolume;
  /**
   * The current sound effects volume.
   */
  private double sfxVolume;
  /**
   * The current Background image URI.
   */
  private String background;
  /**
   * The random number generator to be used by factories.
   */
  private Random factoryRng;

  /**
   * constructor of the options class. Creates an Options object.
   * 
   * @param screenRes
   *          the resolution this Options object should use.
   */
  public Options(final Dimension screenRes) {
    setInstance(this);
    this.dimension = screenRes;
    factoryRng = new Random();

    background = DEFAULT_BACKGROUND_IMAGE;
    musicFileName = DEFAULT_MUSIC_FILENAME;
    eatSoundFileName = DEFAULT_EAT_SOUND_FILENAME;
    laserSoundFileName = DEFAULT_LASER_SOUND_FILENAME;
    pickupSoundFileName = DEFAULT_PICKUP_SOUND_FILENAME;

    playingMusic = false;
    mutedMusic = false;
    mutedSfx = false;
    masterVolume = 1.0;
    musicVolume = 1.0;
    sfxVolume = 1.0;
  }

  /**
   * Gets the instance object that is used. If there is none, a new one is created using
   * standard values.
   * 
   * @return the options object that is currently used.
   */
  public static synchronized Options getInstance() {
    if (instance != null) {
      return instance;
    }
    return new Options(getScreenSize());
  }

  /**
   * sets the current options instance to a certain options object.
   *
   * @param options the options object to set.
   */
  public static void setInstance(final Options options) {
    instance = options;
  }

  /**
   * Destroy current options.
   */
  public static void destroyInstance() {
    Options.instance = null;
  }

  /**
   * gets the current screen size that is being used.
   *
   * @return the screen resolution of the systems screen.
   */
  private static Dimension getScreenSize() {
    try {
      return Toolkit.getDefaultToolkit()
          .getScreenSize();
    } catch (HeadlessException e) {
      return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
  }

  /**
   * Gets the height of the screen now used.
   *
   * @return the height of the screen.
   */
  public static double getGlobalHeight() {
    return getInstance().getDimension()
        .getHeight();
  }

  /**
   * sets the height of the screen.
   *
   * @param height
   *          the height to set.
   */
  public static void setGlobalHeight(final double height) {
    final double oldWidth = getInstance().getDimension()
        .getWidth();
    getInstance().getDimension()
        .setSize(oldWidth, height);
  }

  /**
   * Gets the Width of the screen now used.
   *
   * @return the width of the screen.
   */
  public static double getGlobalWidth() {
    return getInstance().getDimension()
        .getWidth();
  }

  /**
   * sets the width of the screen.
   *
   * @param width
   *          the height to set.
   */
  public static void setGlobalWidth(final double width) {
    final double oldHeight = getInstance().getDimension()
        .getHeight();
    getInstance().getDimension()
        .setSize(width, oldHeight);
  }

  /**
   * Get the backgroundColor.
   *
   * @return the default background color
   */
  public static Color getBackgroundColor() {
    return DEFAULT_BACKGROUND_COLOUR;
  }

  /**
   * Get the backGroundColor.
   *
   * @return the default background color
   */
  public static Color getBackGroundColor() {
    return DEFAULT_BACKGROUND_COLOUR;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return dimension.hashCode();
  }

  /**
   * Checks if two options objects are equal.
   *
   * @param object
   *          the object to compare to.
   * @return true if they are equal.
   */
  @Override
  public boolean equals(final Object object) {
    if (object instanceof Options) {
      final Options other = (Options) object;
      if (other.getDimension()
          .equals(dimension)) {
        return true;
      }
    }
    return false;
  }

  /**
   * gets the resolution in the form of a Dimension object of this options object.
   *
   * @return the resolution of this options object.
   */
  public Dimension getDimension() {
    return dimension;
  }

  /**
   * Get the musicFileName.
   *
   * @return the musicFileName.
   */
  public String getMusicFileName() {
    return musicFileName;
  }

  /**
   * @return the hitSoundFileName
   */
  public String getEatSoundFileName() {
    return eatSoundFileName;
  }

  /**
   * @return the laserSoundFileName
   */
  public String getLaserSoundFileName() {
    return laserSoundFileName;
  }

  /**
   * @return the ammoPickupSoundFileName
   */
  public String getPickupSoundFileName() {
    return pickupSoundFileName;
  }

  /**
   * @return playMusic
   */
  public boolean isPlayingMusic() {
    return playingMusic;
  }

  /**
   * @param playMusic
   *          the playMusic to set
   */
  public void setPlayingMusic(final boolean playMusic) {
    this.playingMusic = playMusic;
  }

  /**
   * @return if the music should be muted.
   */
  public boolean isMutedMusic() {
    return mutedMusic;
  }

  /**
   * Set if the music should be muted.
   *
   * @param muteMusic
   *          true if music should be muted.
   */
  public void setMutedMusic(final boolean muteMusic) {
    this.mutedMusic = muteMusic;
  }

  /**
   * @return if the sfx should be muted.
   */
  public boolean isMutedSfx() {
    return mutedSfx;
  }

  /**
   * Set if the sfx should be muted.
   *
   * @param muteSfx
   *          true if music should be muted.
   */
  public void setMutedSfx(final boolean muteSfx) {
    this.mutedSfx = muteSfx;
  }

  /**
   * @return the masterVolume
   */
  public double getMasterVolume() {
    return masterVolume;
  }

  /**
   * @param masterVolume
   *          the masterVolume to set
   */
  public void setMasterVolume(final double masterVolume) {
    this.masterVolume = masterVolume;
  }

  /**
   * @return the musicVolume
   */
  public double getMusicVolume() {
    return musicVolume;
  }

  /**
   * @param newVolume
   *          the musicVolume to set
   */
  public void setMusicVolume(final double newVolume) {
    this.musicVolume = newVolume;
  }

  /**
   * @return the sfxVolume
   */
  public double getSfxVolume() {
    return sfxVolume;
  }

  /**
   * @param sfxVolume
   *          the sfxVolume to set
   */
  public void setSfxVolume(final double sfxVolume) {
    this.sfxVolume = sfxVolume;
  }

  /**
   * Get the backGroundImage.
   *
   * @return the backGroundImage.
   */
  public String getBackground() {
    return background;
  }

  /**
   * Get random number generator for factory.
   * 
   * @return random number generator.
   */
  public Random getFactoryRng() {
    return factoryRng;
  }

}
