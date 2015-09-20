package lasersharks;

import java.awt.Dimension;
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
    return Toolkit.getDefaultToolkit().getScreenSize();
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
  
  
}
