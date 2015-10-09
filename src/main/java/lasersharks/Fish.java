package lasersharks;

import javafx.scene.shape.Rectangle;

/**
 * Abstract class for Floating creatures of the great blue.
 * 
 * @author Youri
 */
@SuppressWarnings("restriction")
public abstract class Fish implements Swimmer {

  protected CollisionBehaviour collisionBehaviour;
  protected HitboxBehaviour hitboxBehaviour;
  protected MoveBehaviour moveBehaviour;
  protected EatBehaviour eatBehaviour;
  
  private Position position;
  private float size;
  private double speed;
  private Direction direction;
  private boolean alive;

  /**
   * <abstract> Method for creating a fish.
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
  public Fish(Position position, float size, double startSpeed, Direction direction) {
    this.position = position;
    this.size = size;
    this.speed = startSpeed;
    this.direction = direction;
    this.alive = true;
  }

  /**
   * Returns the Position of this fish.
   * 
   * @return the current position
   */
  public Position getPosition() {
    return this.position;
  }

  /**
   * Sets the position of the fish.
   * 
   * @param position
   *          the position to set to.
   */
  public void setPosition(Position position) {
    this.position = position;
  }

  /**
   * Returns the Size of this fish.
   * 
   * @return the current size
   */
  public float getSize() {
    return this.size;
  }

  /**
   * Method used for growing fish.
   * 
   * @param size
   *          the delta by which to increase.
   */
  public void increaseSize(float size) {
    this.size += size;
  }

  /**
   * Method to set the size of the fish.
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
   * The current fish will move, this will return false if it moves out of the view.
   * 
   * @param frametime
   *          the time between frames in seconds
   * 
   * @return true if fish is in view
   */
  public boolean move(double frametime) {
    return moveBehaviour.move(frametime);
  }

  /**
   * We calculate the distance between the fishes. The sum of the size of both fishes is our hitbox.
   * Hitbox is now a circle, with size the radius in pixels.
   * 
   * @param swimmer
   *          we want to check if the fishbot collides with this fish,
   * @return true if the fishes collide and false if not.
   */
  public boolean collision(Swimmer swimmer) {
    return collisionBehaviour.collide(swimmer);
  }

  public Position getMiddlePoint() {
    return hitboxBehaviour.getMiddlePoint();
  }

  /**
   * this function checks if the fish is on the screen or not.
   * 
   * @return true if the fish is on the screen, and false if not.
   */
  public boolean isOnScreen() {
    return this.alive && position.onScreen((int) this.size);
  }

  /**
   * Method for killing fish.
   */
  public void kill() {
    this.alive = false;
  }

  /**
   * Check if fish is alive.
   * 
   * @return aliveness of the fish.
   */
  public boolean isAlive() {
    return this.alive;
  }

  /**
   * Set the fish alive.
   */
  public void setAlive() {
    this.alive = true;
  }

  @Override
  public String toString() {
    return "FishBot [" + "position=" + position.toString() + ", size=" + size + ", speed=" + speed
        + ", direction=" + direction + ", Alive =" + alive + "]";
  }

  /**
   * Will return the string resource for this fish.
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
   * Draw a rectangle shaped hitbox around the fishbot.
   * 
   * @return a rectangle hitbox.
   */
  public Rectangle makeHitbox() {
    return hitboxBehaviour.makeHitbox();
  }
  
  @Override
  public void eat(Swimmer swimmer) {
    eatBehaviour.eat(swimmer);
  }

}
