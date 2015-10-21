package lasersharks.interfaces;

import javafx.scene.shape.Rectangle;
import lasersharks.Direction;
import lasersharks.Position;

/**
 * Interface for swimmer class.
 * 
 * @author SEMGroup27
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
   * We calculate the distance between the Displayable. The sum of the size of both Displayable is
   * our hitbox. Hitbox is now a circle, with size the radius in pixels.
   * 
   * @param swimmer
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
  void increaseSize(float size);

  /**
   * Method used for decreasing Displayable.
   * 
   * @param size
   *          the delta by which to increase.
   */
  void decreaseSize(float size);

  /**
   * Get the middle-point of the actor.
   * @return middle point of ~
   */
  Position getMiddlePoint();

  /**
   * Handle collision with other object.
   * @param swimmer other object with wich is collided.
   */
  void collideWith(Displayable swimmer);
  

  /**
   * Increment ammunition by an int value when collided.
   * @return increment value.
   */
  int onCollisionAmmunitionIncrement();
  
  /**
   * Check to see if player has lost the game.
   * @param size shark size.
   */
  void onCollisionPlayerLoses(int size);
  
  /**
   * Notify object it is eaten.
   */
  void onCollisionEaten();
  
  /**
   * get size increment gained by colliding with object.
   * @return size increment.
   */
  float onCollisionSizeIncrement();
  
  /**
   * See if laser needs to be destroyed after colliding with this object.
   * @return boolean weater laser object needs to be destroyed.
   */
  boolean onCollisionDestroyLaser();
  
  /**
   * Notify ~ has been hit by the laster.
   * @param size size by which object needs to decrement.
   */
  void onCollisionSizeDecrement(int size);

}
