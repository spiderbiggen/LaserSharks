package lasersharks;

import java.awt.AWTError;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import lasersharks.gui.LevelGUI;

/**
 * An options object is responsible for handling everything that has to do with options. 
 * This includes resolution.
 * @author Sytze
 *
 */
public class Options {
  
  private Dimension dimension;
  private static final int DEFAULT_WIDTH = 1920;
  private static final int DEFAULT_HEIGHT = 1080;
  private static final String DEFAULT_MUSIC_FILENAME = "src/main/resources/music.mp3";
  private static final String DEFAULT_BACKGROUND_IMAGE = "somber sea floor.jpg";
  
  private String musicFileName;
  private String backGround;
  
  private static Options currentOptions;
  
  /**
   * constructor of the options class. Creates an Options object.
   * @param screenRes the resolution this Options object should use.
   */
  public Options(Dimension screenRes) {
     this.dimension = screenRes;
  }
  
  /**
   * Gets the currentOptions object that is used.
   * If there is none, a new one is created using standard values.
   * @return the options object that is currently used.
   */
  public static Options getInstance() {
    if (currentOptions != null) {
      return currentOptions;
    }
    currentOptions = new Options(getScreenSize());
    currentOptions.setBackGround(DEFAULT_BACKGROUND_IMAGE);
    currentOptions.setMusicFilename(DEFAULT_MUSIC_FILENAME);
    return currentOptions;
  }
  

  /**
   * generates the hashCode for this object.
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((dimension == null) ? 0 : dimension.hashCode());
    return result;
  }
  
  /**
   * Checks if two options objects are equal.
   * @param object the object to compare to.
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
   * @param options the options object to set.
   */
  public static void setInstance(Options options) {
    currentOptions = options;
  }
  
  /**
   * gets the current screen size that is being used.
   * @return the screen resolution of the systems screen.
   */
  public static Dimension getScreenSize() {
    try {
    return Toolkit.getDefaultToolkit().getScreenSize();
    } catch (HeadlessException e ) {
      return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
  }

  /**
   * gets the resolution in the form of a Dimension object of this options object.
   * @return the resolution of this options object.
   */
  public Dimension getDimension() {
    return dimension;
  }
  
  /**
   * sets the resolution of this options object.
   * @param dimension the resolution to set to.
   */
  public void setDimension(Dimension dimension) {
    this.dimension = dimension;
  }
  
  /**
   * Gets the height of the screen now used.
   * @return the height of the screen.
   */
  public static double getGlobalHeight() {
    return getInstance().getDimension().getHeight();
  }
  
  /**
   * Gets the Width of the screen now used.
   * @return the width of the screen.
   */
  public static double getGlobalWidth() {
    return getInstance().getDimension().getWidth();
  }
  
  /**
   * sets the height of the screen.
   * @param height the height to set.
   */
  public static void setGlobalHeight(double height) {
    double oldWidth = getInstance().getDimension().getWidth();
    getInstance().getDimension().setSize(oldWidth, height);
  }

  /**
   * sets the width of the screen.
   * @param width the height to set.
   */
  public static void setGlobalWidth(double width) {
    double oldHeight = getInstance().getDimension().getHeight();
    getInstance().getDimension().setSize(width, oldHeight);
  }
  
  /**
   * sets the background image.
   * @param backGround the background to set.
   */
  public void setBackGround(String backGround) {
    this.backGround = backGround;
  }
  
  /**
   * sets the music in terms of a filename.
   * @param musicFile the music to set.
   */
  public void setMusicFilename(String musicFile) {
    this.musicFileName = musicFile;   
  }
  
  /**
   * Get the musicFileName.
   * @return the musicFileName.
   */
  public String getMusicFileName() {
    return musicFileName;
  }
  
  /**
   * Get the backGroundImage.
   * @return the backGroundImage.
   */
  public String getBackGroundImage() {
    return backGround;
  }
  
}
