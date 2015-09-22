package lasersharks;

import javafx.scene.shape.Rectangle;

/**
 * Abstract class for Floating creatures of the great blue.
 * 
 * @author Youri
 */
@SuppressWarnings("restriction")
public abstract class Fish {

  private static final double WIDTH_TO_COORD = 0.85;
  private static final double SIZE_TO_COORD = 0.075;
  private Position position;
  private float size;
  private double speed;
  private Direction direction;
  private boolean alive;
  private static final double HALF_SCALE = 0.5;

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
  protected void increaseSize(float size) {
    this.size += size;
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
    return position.updatePosition(direction, (speed / frametime), size);
  }

  /**
   * We calculate the distance between the fishes. The sum of the size of both fishes is our hitbox.
   * Hitbox is now a circle, with size the radius in pixels.
   * 
   * @param fish
   *          we want to check if the fishbot collides with this fish,
   * @return true if the fishes collide and false if not.
   */
  public boolean collision(Fish fish) {
    float distance = this.getMiddlePoint().calculateDistance(fish.getMiddlePoint());
    return distance < this.size + fish.getSize();
  }

  private Position getMiddlePoint() {
    Position startPos = this.getPosition();

    Position middlePointPosition = new Position(startPos.getPosX()
        + (HALF_SCALE * this.getWidthScale() * this.getSize()), startPos.getPosY()
        + (HALF_SCALE * this.getSize()));
    return middlePointPosition;
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
        + ", direction=" + direction + "]";
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
    double xcoordinate = this.getPosition().getPosX();
    double ycoordinate = this.getPosition().getPosY();
    Rectangle rekt = new Rectangle(
        xcoordinate + SIZE_TO_COORD * this.getSize(), 
        ycoordinate + SIZE_TO_COORD * this.getSize(), 
        this.getWidthScale() * this.getSize() * WIDTH_TO_COORD, 
        this.getSize() * WIDTH_TO_COORD
    );
    return rekt;
  }

}
