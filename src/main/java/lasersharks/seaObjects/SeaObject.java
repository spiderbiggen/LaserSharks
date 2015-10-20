package lasersharks.seaObjects;

import javafx.scene.shape.Rectangle;
import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.behaviour.DefaultCollisionBehaviour;
import lasersharks.behaviour.DefaultEatBehaviour;
import lasersharks.behaviour.DefaultMoveBehaviour;
import lasersharks.behaviour.collision.DefaultAmmunitionIncrementBehaviour;
import lasersharks.behaviour.collision.DefaultCheckForLossBehaviour;
import lasersharks.behaviour.collision.DefaultEatenBehaviour;
import lasersharks.behaviour.collision.DefaultGetSizeIncrementBehaviour;
import lasersharks.behaviour.collision.DefaultLaserCollisionBehaviour;
import lasersharks.behaviour.collision.DefaultSizeDecrementBehaviour;
import lasersharks.behaviour.collision.interfaces.AmmunitionIncrementBehaviour;
import lasersharks.behaviour.collision.interfaces.CheckForLossBehaviour;
import lasersharks.behaviour.collision.interfaces.EeatenBehaviour;
import lasersharks.behaviour.collision.interfaces.GetSizeIncrementBahaviour;
import lasersharks.behaviour.collision.interfaces.LaserCollisionBehaviour;
import lasersharks.behaviour.collision.interfaces.SizeDecrementBahaviour;
import lasersharks.behaviour.interfaces.CollisionBehaviour;
import lasersharks.behaviour.interfaces.EatBehaviour;
import lasersharks.behaviour.interfaces.MoveBehaviour;
import lasersharks.interfaces.Displayable;

/**
 * Abstract class for objects of the great blue.
 * 
 * @author SEMGroup27
 */
@SuppressWarnings("restriction")
public abstract class SeaObject implements Displayable {

  private static final float MIN_SIZE = 30.0f;
  protected CollisionBehaviour collisionBehaviour;
  protected MoveBehaviour moveBehaviour;
  protected EatBehaviour eatBehaviour;
  
  protected AmmunitionIncrementBehaviour ammunitionIncrementBehaviour;
  protected CheckForLossBehaviour checkForLossBehaviour;
  protected EeatenBehaviour eatenBehaviour;
  protected GetSizeIncrementBahaviour getSizeIncrementBahaviour;
  protected LaserCollisionBehaviour laserCollisionBehaviour;
  protected SizeDecrementBahaviour sizeDecrementBahaviour;
  
  private Position position;
  private float size;
  private double speed;
  private Direction direction;
  private boolean alive;

  /**
   * <abstract> Method for creating a seaObject.
   * 
   * @param position
   *          start position.
   * @param size
   *          start size.
   * @param startSpeed
   *          start speed.
   * @param direction
   *          start direction.
   */
  public SeaObject(Position position, float size, double startSpeed, Direction direction) {
    this.position = position;
    this.size = size;
    this.speed = startSpeed;
    this.direction = direction;
    this.alive = true;
    
    this.collisionBehaviour = new DefaultCollisionBehaviour(this);
    this.moveBehaviour = new DefaultMoveBehaviour(this);
    this.eatBehaviour = new DefaultEatBehaviour();
    
    this.ammunitionIncrementBehaviour = new DefaultAmmunitionIncrementBehaviour();
    this.checkForLossBehaviour = new DefaultCheckForLossBehaviour();
    this.eatenBehaviour = new DefaultEatenBehaviour();
    this.getSizeIncrementBahaviour = new DefaultGetSizeIncrementBehaviour();
    this.laserCollisionBehaviour = new DefaultLaserCollisionBehaviour();
    this.sizeDecrementBahaviour = new DefaultSizeDecrementBehaviour();
  }

  /**
   * Returns the Position of this seaObject.
   * 
   * @return the current position
   */
  public Position getPosition() {
    return this.position;
  }

  /**
   * Sets the position of the seaObject.
   * 
   * @param position
   *          the position to set to.
   */
  public void setPosition(Position position) {
    this.position = position;
  }

  /**
   * Returns the Size of this seaObject.
   * 
   * @return the current size
   */
  public float getSize() {
    return this.size;
  }

  /**
   * Method used for growing seaObject.
   * 
   * @param size
   *          the delta by which to increase.
   */
  public void increaseSize(float size) {
    this.size += size;
  }
  
  /**
   * Method used for shrinking seaObject.
   * 
   * @param size decrement size
   */
  public void decreaseSize(float size) {
    this.setSize(Math.max(MIN_SIZE, this.getSize() - size));
  }

  /**
   * Method to set the size of the seaObject.
   * 
   * @param size size
   */
  public void setSize(float size) {
    this.size = size;
  }

  /**
   * @return the speed.
   */
  public double getSpeed() {
    return speed;
  }

  /**
   * @param speed
   *          the speed to set.
   */
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  /**
   * @return the direction.
   */
  public Direction getDirection() {
    return direction;
  }

  /**
   * @param direction
   *          the direction to set.
   */
  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  /**
   * The current seaObject will move, this will return false if it moves out of the view.
   * 
   * @param frametime
   *          the time between frames in seconds
   * 
   * @return true if seaObject is in view
   */
  public boolean move(double frametime) {
    return moveBehaviour.move(frametime);
  }

  /**
   * We calculate the distance between the seaObjectes. 
   * The sum of the size of both seaObjectes is our hitbox.
   * Hitbox is now a circle, with size the radius in pixels.
   * 
   * @param swimmer
   *          we want to check if the seaObjectbot collides with this seaObject,
   * @return true if the seaObjectes collide and false if not.
   */
  public boolean collision(Displayable swimmer) {
    return collisionBehaviour.collide(swimmer);
  }

  /**
   * gets the middle of the seaObject.
   * @return the middlepoint of the seaObject.
   */
  public Position getMiddlePoint() {
    return collisionBehaviour.getMiddlePoint();
  }

  /**
   * this function checks if the seaObject is on the screen or not.
   * 
   * @return true if the seaObject is on the screen, and false if not.
   */
  public boolean isOnScreen() {
    return this.alive && position.onScreen((int) this.size);
  }

  /**
   * Method for killing seaObject.
   */
  public void kill() {
    this.alive = false;
  }

  /**
   * Check if seaObject is alive.
   * 
   * @return aliveness of the seaObject.
   */
  public boolean isAlive() {
    return this.alive;
  }

  /**
   * Set the seaObject alive.
   */
  public void setAlive() {
    this.alive = true;
  }

  @Override
  public String toString() {
    return "seaObject [" + "position=" + position.toString() + ", size=" + size + ", speed=" + speed
        + ", direction=" + direction + ", Alive =" + alive + "]";
  }

  /**
   * Will return the string resource for this seaObject.
   * 
   * @return The resource's name
   */
  public abstract String getImageResource();

  /**
   * return the aspect ratio between the width and the height. height/width
   * 
   * @return the aspect ratio
   */
  public abstract double getWidthScale();

  /**
   * Draw a rectangle shaped hitbox around the seaObjectbot.
   * 
   * @return a rectangle hitbox.
   */
  public Rectangle makeHitbox() {
    return collisionBehaviour.makeHitbox();
  }
  
  /**
   * The SeaObject eats an other seaObject. This kills seaObject and increases size of the shark.
   * 
   * @param seaObject
   *          the seaObject the shark eats
   */
  @Override
  public void eat(Displayable swimmer) {
    eatBehaviour.eat(swimmer);
  }
  

  /**
   * Increment ammunition by an int value when collided.
   * @return increment value.
   */
  @Override
  public int onCollisionAmmunitionIncrement() {
    return this.ammunitionIncrementBehaviour.onCollisionAmmunitionIncrement();
  }
  
  /**
   * Check to see if player has lost the game.
   * @param size shark size.
   */
  @Override
  public void onCollisionPlayerLoses(int size) {
    this.checkForLossBehaviour.onCollisionPlayerLoses(size);
  }
  
  /**
   * Notify object it is eaten.
   */
  @Override
  public void onCollisionEaten() {
    this.eatenBehaviour.onCollisionEaten();
  }
  
  /**
   * get size increment gained by colliding with object.
   * @return size increment.
   */
  @Override
  public float onCollisionSizeIncrement() {
    return this.getSizeIncrementBahaviour.onCollisionSizeIncrement();
  }
  
  /**
   * See if laser needs to be destroyed after colliding with this object.
   * @return boolean weather laser object needs to be destroyed.
   */
  @Override
  public boolean onCollisionDestroyLaser() {
    return this.laserCollisionBehaviour.onCollisionDestroyLaser();
  }
  
  /**
   * Notify ~ has been hit by the laster.
   * @param size size by which object needs to decrement.
   */
  @Override
  public void onCollisionSizeDecrement(int size) {
    this.sizeDecrementBahaviour.onCollisionSizeDecrement(size);
  }

}
