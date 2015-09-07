package lasersharks;

/**
 * Abstract class for Floating creatures of the great blue.
 * @author Youri
 */
public abstract class Fish {
  
  private Position position;
  private float size;
  private int speed;
  private Direction direction;

  /**
   * <abstract> Method for creating a fish.
   * 
   * @param position
   *          start position.
   * @param size
   *          start size.
   * @param speed
   *          start speed.
   * @param direction
   *          start direction.
   */
  public Fish(Position position, float size, int speed, Direction direction) {
    super();
    this.position = position;
    this.size = size;
    this.speed = speed;
    this.direction = direction;
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
   * Returns the Size of this fish.
   * 
   * @return the current size
   */
  public float getSize() {
    return this.size;
  }

  /**
   * @return the speed
   */
  public int getSpeed() {
    return speed;
  }

  /**
   * @param speed the speed to set
   */
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  /**
   * @return the direction
   */
  public Direction getDirection() {
    return direction;
  }

  /**
   * @param direction the direction to set
   */
  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  /**
   * The current fish will move, this will return false if it moves out of the view.
   * 
   * @return true if fish is in view
   */
  public boolean move() {
    return position.updatePosition(direction, speed);
  }
  
  /**
   * We calculate the distance between the fishes. The sum of the size of both fishes is our hitbox.
   * Hitbox is now a circle, with size the radius in pixels.
   * 
   * @param fish we want to check if the fishbot collides with this fish,
   * @return true if the fishes collide and false if not.
   */
  public boolean collision(Fish fish) {
    return (position.calculateDistance(fish.getPosition()) < size + fish.getSize());
  }

  /**
   * this function checks if the fish is on the screen or not.
   * 
   * @return true if the fish is on the screen, and false if not.
   */
  public final boolean isOnScreen() {
    return position.onScreen();
  }

  @Override
  public String toString() {
    return "FishBot [" + "position=" + position.toString() + ", size=" + size + ", speed=" + speed
        + ", direction=" + direction + "]";
  }
}
