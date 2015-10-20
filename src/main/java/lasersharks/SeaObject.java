package lasersharks;

import javafx.scene.shape.Rectangle;

/**
 * Abstract class for objects of the great blue.
 * 
 * @author Youri
 */
@SuppressWarnings("restriction")
public abstract class SeaObject implements Displayable {

  protected CollisionBehaviour collisionBehaviour;
  protected MoveBehaviour moveBehaviour;
  protected EatBehaviour eatBehaviour;
  
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
   * @param size
   */
  public void decreaseSize(float size) {
    this.size = this.size - size;
  }

  /**
   * Method to set the size of the seaObject.
   * 
   * @param size
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
   * We calculate the distance between the seaObjectes. The sum of the size of both seaObjectes is our hitbox.
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
    return "seaObjectBot [" + "position=" + position.toString() + ", size=" + size + ", speed=" + speed
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

}
