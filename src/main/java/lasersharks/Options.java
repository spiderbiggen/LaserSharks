package lasersharks;

import javafx.scene.paint.Color;
import java.awt.Dimension;
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
   * The dimension of the screen.
   */
  private Dimension dimension;
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
  private String backGround;
  /**
   * The random number generator to be used by factories.
   */
  private Random factoryRng;
  /**
   * The random number generator to be used by the fish controller.
   */
  private Random spawnRng;
  /**
   * The current Singleton instance.
   */
  private static Options instance;

  /**
   * constructor of the options class. Creates an Options object.
   * 
   * @param screenRes
   *          the resolution this Options object should use.
   */
  public Options(Dimension screenRes) {
    setInstance(this);
    this.dimension = screenRes;
    factoryRng = new Random();
    spawnRng = factoryRng;

    this.setBackGround(DEFAULT_BACKGROUND_IMAGE);
    this.setMusicFileName(DEFAULT_MUSIC_FILENAME);
    this.setEatSoundFileName(DEFAULT_EAT_SOUND_FILENAME);
    this.setLaserSoundFileName(DEFAULT_LASER_SOUND_FILENAME);
    this.setPickupSoundFileName(DEFAULT_PICKUP_SOUND_FILENAME);

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
   * Destroy current options.
   */
  public static void destroyInstance() {
    Options.instance = null;
  }

  /**
   * @return the hashcode of the dimension.
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
  public boolean equals(Object object) {
    if (object instanceof Options) {
      Options other = (Options) object;
      if (other.getDimension().equals(dimension)) {
        return true;
      }
    }
    return false;
  }

  /**
   * sets the current options instance to a certain options object.
   * 
   * @param options
   *          the options object to set.
   */
  public static void setInstance(Options options) {
    instance = options;
  }

  /**
   * gets the current screen size that is being used.
   * 
   * @return the screen resolution of the systems screen.
   */
  protected static Dimension getScreenSize() {
    try {
      return Toolkit.getDefaultToolkit().getScreenSize();
    } catch (Exception e) {
      return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
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
   * sets the resolution of this options object.
   * 
   * @param dimension
   *          the resolution to set to.
   */
  public void setDimension(Dimension dimension) {
    this.dimension = dimension;
  }

  /**
   * Gets the height of the screen now used.
   * 
   * @return the height of the screen.
   */
  public static double getGlobalHeight() {
    return getInstance().getDimension().getHeight();
  }

  /**
   * Gets the Width of the screen now used.
   * 
   * @return the width of the screen.
   */
  public static double getGlobalWidth() {
    return getInstance().getDimension().getWidth();
  }

  /**
   * sets the height of the screen.
   * 
   * @param height
   *          the height to set.
   */
  public static void setGlobalHeight(double height) {
    double oldWidth = getInstance().getDimension().getWidth();
    getInstance().getDimension().setSize(oldWidth, height);
  }

  /**
   * sets the width of the screen.
   * 
   * @param width
   *          the height to set.
   */
  public static void setGlobalWidth(double width) {
    double oldHeight = getInstance().getDimension().getHeight();
    getInstance().getDimension().setSize(width, oldHeight);
  }

  /**
   * sets the background image.
   * 
   * @param backGround
   *          the background to set.
   */
  public void setBackGround(String backGround) {
    this.backGround = backGround;
  }

  /**
   * sets the music in terms of a filename.
   * 
   * @param musicFile
   *          the music to set.
   */
  public void setMusicFileName(String musicFile) {
    this.musicFileName = musicFile;
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
   * @param eatSoundFileName
   *          the eatSoundFileName to set
   */
  public void setEatSoundFileName(String eatSoundFileName) {
    this.eatSoundFileName = eatSoundFileName;
  }

  /**
   * @return the laserSoundFileName
   */
  public String getLaserSoundFileName() {
    return laserSoundFileName;
  }

  /**
   * @param laserSoundFileName
   *          the laserSoundFileName to set
   */
  public void setLaserSoundFileName(String laserSoundFileName) {
    this.laserSoundFileName = laserSoundFileName;
  }

  /**
   * @return the ammoPickupSoundFileName
   */
  public String getPickupSoundFileName() {
    return pickupSoundFileName;
  }

  /**
   * @param pickupSoundFileName
   *          the pickupSoundFileName to set
   */
  public void setPickupSoundFileName(String pickupSoundFileName) {
    this.pickupSoundFileName = pickupSoundFileName;
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
  public void setPlayingMusic(boolean playMusic) {
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
  public void setMutedMusic(boolean muteMusic) {
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
  public void setMutedSfx(boolean muteSfx) {
    this.mutedSfx = muteSfx;
  }

  /**
   * @return the masterVolume
   */
  public double getMasterVolume() {
    return masterVolume;
  }

  /**
   * @param masterVolume the masterVolume to set
   */
  public void setMasterVolume(double masterVolume) {
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
  public void setMusicVolume(double newVolume) {
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
  public void setSfxVolume(double sfxVolume) {
    this.sfxVolume = sfxVolume;
  }

  /**
   * Get the backGroundImage.
   * 
   * @return the backGroundImage.
   */
  public String getBackGroundImage() {
    return backGround;
  }

  /**
   * Get the backGroundColor.
   *
   * @return the default background color
   */
  public static Color getBackGroundColor() {
    return DEFAULT_BACKGROUND_COLOUR;
  }

  /**
   * Get random number generator for factory.
   * 
   * @return random number generator.
   */
  public Random getFactoryRng() {
    return factoryRng;
  }

  /**
   * set the random number generator for factory.
   * 
   * @param rng
   *          random number generator.
   */
  public void setFactoryRng(Random rng) {
    this.factoryRng = rng;
  }

  /**
   * Get random number generator for factory.
   * 
   * @return random number generator.
   */
  public Random getSpawnRng() {
    return spawnRng;
  }

  /**
   * set the random number generator for factory.
   * 
   * @param rng
   *          random number generator.
   */
  public void setSpawnRng(Random rng) {
    this.spawnRng = rng;
  }
}
