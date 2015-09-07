package lasersharks;

/**
 * This class represent the fishes on the screen that are not player controllable.
 * 
 * @author Sytze
 *
 */
public class FishBot extends Fish {

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

  /**
   * Constructor class for FishBot.
   * @param position initial position
   * @param size init size
   * @param speed init speed
   * @param direction init direction
   */
  public FishBot(Position position, float size, int speed, Direction direction) {
    super(position, size, speed, direction);
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
}