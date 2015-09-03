package lasersharks;

/**
 * This class represent the fishes on the screen that are not player controllable.
 * 
 * @author Sytze
 *
 */
public class FishBot implements Fish {

  /**
   * This value is used to modify the speed of the fishes that are generated. The generated speed is
   * equal to SpeedModifier*RandomNumber, where RandomNumber is a random int between 0 and 100.
   */
  private static int speedModifier = 30;

  /**
   * This value is used to modify the size of the fishes that are generated. The generated speed is
   * equal to SizeModifier*RandomNumber, where RandomNumber is a random int between 0 and 100.
   */
  private static int sizeModifier = 40;

  /**
   * This value represents the chance for fish to spawn on the left side of the screen.
   */
  private static int chanceForLeft = 50;

  private Position position;
  private float size;
  private int speed;
  private Direction direction;

  /**
   * The constructor for fishbot.
   * @param pos the position where the fishbot is.
   * @param siz the size of the fishbot.
   * @param sp the speed parameter of the fishbot.
   * @param dir the direction the fishbot is heading to.
   */
  public FishBot(Position pos, float siz, int sp, Direction dir) {
    position = pos;
    size = siz;
    speed = sp;
    direction = dir;
  }

  /**
   * @return the position
   */
  @Override
  public Position getPosition() {
    return position;
  }

  /**
   * @return the size
   */
  @Override
  public float getSize() {
    return size;
  }

  /**
   * @return the size
   */
  public float getSpeed() {
    return speed;
  }

  /**
   * @return the direction
   */
  public Direction getDirection() {
    return direction;
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
   * moves the fishbot using the direction and speed values of this object.
   */
  @Override
  public boolean move() {
    return position.updatePosition(direction, speed);
  }

  /**
   * This function creates a new FishBot with random values. This should be used to spawn fishes.
   * Starts on either the left side on
   * 
   * @return a random fish with random speed, size and position.
   */
  public static FishBot generateFish() {
    int posX;
    Direction dir;

    if (Math.random() * 100 > chanceForLeft) {
      // starts on the right side
      dir = Direction.East;
      posX = Position.getWidthPanel();
    } else {
      // starts on the left side
      posX = 0;
      dir = Direction.West;
    }

    return new FishBot(new Position(posX, (int) (Position.getHeightPanel() * Math.random())),
        (int) Math.round(Math.random() * sizeModifier),
        (int) Math.round(Math.random() * speedModifier), dir);
  }

  /**
   * this function checks if the fish is on the screen or not.
   * 
   * @return true if the fish is on the screen, and false if not.
   */
  public final boolean isOnScreen() {
    return position.isOnScreen();
  }

  @Override
  public String toString() {
    return "FishBot [" + "position=" + position.toString() + ", size=" + size + ", speed=" + speed
        + ", direction=" + direction + "]";
  }
}