package lasersharks.interfaces;

import javafx.scene.shape.Rectangle;
import lasersharks.Direction;
import lasersharks.Position;

/**
 * Interface for swimmer class.
 * 
 * @author Youri
 *
 */
@SuppressWarnings("restriction")
public interface Displayable {

  /**
   * Returns the Position of this Displayable.
   * 
   * @return the current position
   */
  Position getPosition();

  /**
   * Sets the position of the Displayable.
   * 
   * @param position
   *          the position to set to.
   */
  void setPosition(Position position);

  /**
   * Returns the Size of this Displayable.
   * 
   * @return the current size
   */
  float getSize();

  /**
   * @return the speed.
   */
  double getSpeed();

  /**
   * @param speed
   *          the speed to set.
   */
  void setSpeed(int speed);

  /**
   * @return the direction.
   */
  Direction getDirection();

  /**
   * @param direction
   *          the direction to set.
   */
  void setDirection(Direction direction);

  /**
   * The current Displayable will move, this will return false if it moves out of the view.
   * 
   * @param frametime
   *          the time between frames in seconds
   * 
   * @return true if Displayable is in view
   */
  boolean move(double frametime);

  /**
   * We calculate the distance between the Displayable. The sum of the size of both Displayable is our hitbox.
   * Hitbox is now a circle, with size the radius in pixels.
   * 
   * @param Displayable
   *          we want to check if the Displayable collides with this Displayable,
   * @return true if the Displayables collide and false if not.
   */
  boolean collision(Displayable swimmer);

  /**
   * this function checks if the Displayable is on the screen or not.
   * 
   * @return true if the Displayable is on the screen, and false if not.
   */
  boolean isOnScreen();

  /**
   * Method for killing Displayable.
   */
  void kill();

  /**
   * Check if Displayable is alive.
   * 
   * @return aliveness of the Displayable.
   */
  boolean isAlive();

  /**
   * Will return the string resource for this Displayable.
   * 
   * @return The resource's name
   */
  String getImageResource();

  /**
   * return the aspect ratio between the width and the height. height/width
   * 
   * @return the aspect ratio
   */
  double getWidthScale();

  /**
   * Draw a rectangle shaped hitbox around the Displayable.
   * 
   * @return a rectangle hitbox.
   */
  Rectangle makeHitbox();
  
  /**
   * Method used for growing Displayable.
   * 
   * @param size
   *          the delta by which to increase.
   */
  public void increaseSize(float size);
  
  /**
   * Method used for decreasing Displayable.
   * 
   * @param size
   *          the delta by which to increase.
   */
  public void decreaseSize(float size);
  
  public Position getMiddlePoint();

  void eat(Displayable swimmer);
}
