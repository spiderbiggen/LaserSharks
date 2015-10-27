package lasersharks.seaobjects;

import javafx.scene.shape.Rectangle;
import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.behaviour.*;
import lasersharks.behaviour.ammunitionIncrement.DefaultAmmunitionIncrementBehaviour;
import lasersharks.behaviour.checkforloss.DefaultCheckForLossBehaviour;
import lasersharks.behaviour.collision.DefaultCollisionBehaviour;
import lasersharks.behaviour.collisionHitbox.DefaultCollisionHitBoxBehaviour;
import lasersharks.behaviour.eaten.DefaultEatenBehaviour;
import lasersharks.behaviour.highscoreincrement.DefaultHighScoreIncrementBehaviour;
import lasersharks.behaviour.lasercollision.DefaultLaserCollisionBehaviour;
import lasersharks.behaviour.move.DefaultMoveBehaviour;
import lasersharks.behaviour.sizedecrement.DefaultSizeDecrementBehaviour;
import lasersharks.behaviour.sizeincrement.DefaultGetSizeIncrementBehaviour;
import lasersharks.interfaces.Displayable;

/**
 * Abstract class for objects of the great blue.
 * 
 * @author SEMGroup27
 */
@SuppressWarnings("restriction")

public abstract class SeaObject implements Displayable {

  private static final float MIN_SIZE = 30.0f;
  protected CollisionHitBoxBehaviour collisionHitBoxBehaviour;
  protected MoveBehaviour moveBehaviour;
  
  protected AmmunitionIncrementBehaviour ammunitionIncrementBehaviour;
  protected CheckForLossBehaviour checkForLossBehaviour;
  protected EatenBehaviour eatenBehaviour;
  protected GetSizeIncrementBehaviour getSizeIncrementBehaviour;
  protected LaserCollisionBehaviour laserCollisionBehaviour;
  protected SizeDecrementBehaviour sizeDecrementBehaviour;
  protected CollisionBehaviour collisionBehaviour;
  protected HighScoreIncrementBehaviour highScoreIncrementBehaviour;
  
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

    this.collisionHitBoxBehaviour = new DefaultCollisionHitBoxBehaviour(this);
    this.moveBehaviour = new DefaultMoveBehaviour(this);
    
    this.ammunitionIncrementBehaviour = new DefaultAmmunitionIncrementBehaviour();
    this.checkForLossBehaviour = new DefaultCheckForLossBehaviour();
    this.eatenBehaviour = new DefaultEatenBehaviour();
    this.getSizeIncrementBehaviour = new DefaultGetSizeIncrementBehaviour();
    this.laserCollisionBehaviour = new DefaultLaserCollisionBehaviour();
    this.sizeDecrementBehaviour = new DefaultSizeDecrementBehaviour();
    this.collisionBehaviour = new DefaultCollisionBehaviour();
    this.highScoreIncrementBehaviour = new DefaultHighScoreIncrementBehaviour();
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
   * @param frameTime
   *          the time between frames in seconds
   * 
   * @return true if seaObject is in view
   */
  public boolean move(double frameTime) {
    return moveBehaviour.move(frameTime);
  }

  /**
   * We calculate the distance between the seaObjects.
   * The sum of the size of both seaObjects is our hit box.
   * Hit box is now a circle, with size the radius in pixels.
   * 
   * @param swimmer
   *          we want to check if the seaObjectBot collides with this seaObject,
   * @return true if the seaObjects collide and false if not.
   */
  public boolean checkForCollision(Displayable swimmer) {
    return collisionHitBoxBehaviour.collide(swimmer);
  }

  /**
   * gets the middle of the seaObject.
   *
   * @return the middle point of the seaObject.
   */
  public Position getMiddlePoint() {
    return collisionHitBoxBehaviour.getMiddlePoint();
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
   * Draw a rectangle shaped hit box around the seaObjectBot.
   *
   * @return a rectangle hit box.
   */
  public Rectangle makeHitBox() {
    return collisionHitBoxBehaviour.makeHitBox();
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
  public void onCollisionPlayerLoses(float size) {
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
    return this.getSizeIncrementBehaviour.onCollisionSizeIncrement();
  }
  
  /**
   * See if laser needs to be destroyed after colliding with this object.
   */
  @Override
  public void onCollisionDestroyLaser() {
    this.laserCollisionBehaviour.onCollisionDestroyLaser();
  }
  
  /**
   * Notify ~ has been hit by the laser.
   * @param size size by which object needs to decrement.
   */
  @Override
  public void onCollisionSizeDecrement(int size) {
    this.sizeDecrementBehaviour.onCollisionSizeDecrement(size);
  }
  
  /**
   * Get decrement on collision.
   * @return decrementing size.
   */
  public int getOnCollisionSizeDecrement() {
    return 0;
  }
  
  
  /**
   * Increment ammunition.
   * @param onCollisionAmmunitionIncrement increment ammunition by given value.
   */
  public void increaseAmmunition(int onCollisionAmmunitionIncrement) {
  
  }
  
  /**
   * Handle collisions.
   * @param object object with which the actor has collided.
   */
  public void collideWith(Displayable object) {
    this.collisionBehaviour.collideWith(object);
  }
  
  /**
   * See if object is a collision actor.
   * @return boolean if the actor has a useful collision function.
   */
  public boolean collisionActor() {
    return false;
  }
  

  /**
   * Let displayable object increase the player high score on collision.
   * @param timePenalty penalty from time limit.
   * @return High score increment.
   */
  public int getOnCollisionHighScoreIncrement(int timePenalty) {
    return this.highScoreIncrementBehaviour.onCollisionHighScoreIncrement(timePenalty);
  }
  
}


