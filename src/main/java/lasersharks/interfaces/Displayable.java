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
   * @param frameTime
   *          the time between frames in seconds
   * 
   * @return true if Displayable is in view
   */
  boolean move(double frameTime);

  /**
   * We calculate the distance between the Displayable. The sum of the size of both Displayable is
   * our hit box. Hit box is now a circle, with size the radius in pixels.
   * 
   * @param swimmer
   *          we want to check if the Displayable collides with this Displayable,
   * @return true if the Displayables collide and false if not.
   */
  boolean checkForCollision(Displayable swimmer);

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
   * Draw a rectangle shaped hit box around the Displayable.
   *
   * @return a rectangle hit box.
   */
  Rectangle makeHitBox();

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
   * Increment ammunition by an int value when collided.
   * @return increment value.
   */
  int onCollisionAmmunitionIncrement();
  
  /**
   * Check to see if player has lost the game.
   * @param f shark size.
   */
  void onCollisionPlayerLoses(float f);
  
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
   */
  void onCollisionDestroyLaser();
  
  /**
   * Notify ~ has been hit by the laser.
   * @param size size by which object needs to decrement.
   */
  void onCollisionSizeDecrement(int size);
  
  /**
   * Get size by which to decrement on collision.
   * @return size by which object needs to decrement.
   */
  int getOnCollisionSizeDecrement();

  /**
   * Increase ammunition of current object.
   * @param amount the amount by which to increase the ammo
   */
  void increaseAmmunition(int amount);
  
  /**
   * Notify that the object have collided.
   * @param object the object that this object collided with
   */
  void collideWith(Displayable object);
  
  /**
   * See if this is the actor or the receiver in collisions.
   * @return true iff this object can start a collision
   */
  boolean collisionActor();
  
  /**
   * Let displayable object increase the player high score on collision.
   * @param timePenalty penalty from time limit.
   * @return High score increment.
   */
  int getOnCollisionHighScoreIncrement(int timePenalty);
  
}
