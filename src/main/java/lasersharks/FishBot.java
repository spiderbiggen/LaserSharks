package lasersharks;

import java.util.Random;

/**
 * This class represent the fishes on the screen that are not player controllable.
 * 
 * @author Sytze, Youri
 *
 */
public class FishBot extends Fish {

  /**
   * This value is used to modify the speed of the fishes that are generated. The generated speed is
   * equal to SpeedModifier*RandomNumber, where RandomNumber is a random int between 0 and 100.
   */
  private static final int SPEED_MODIFIER = 25;
  private static final int BASE_SPEED = 5;
  private final String imageResource = "FishBotSmall.png";
  private final float widthScale = 1.1f;

  /**
   * This value is used to modify the size of the fishes that are generated. The generated speed is
   * equal to SizeModifier*RandomNumber, where RandomNumber is a random int between 0 and 100.
   */

  private static final int SIZE_MODIFIER = 200;
  private static final int BASE_SIZE = 30;

  /**
   * Constructor class for FishBot.
   * 
   * @param position
   *          initial position
   * @param size
   *          init size
   * @param speed
   *          init speed
   * @param direction
   *          init direction
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
    return FishBot.generateFish(new Random());
  }

  /**
   * This function creates a new FishBot with random values. This should be used to spawn fishes.
   * Starts on either the left side or the right side.
   * 
   * @return a random fish with random speed, size and position.
   * @param rng
   *          random number generator to use.
   */
  public static FishBot generateFish(Random rng) {
    int posX;
    Direction dir;
    if (rng.nextBoolean()) {
      // starts on the right side
      dir = Direction.West;
      posX = Position.getWidthPanel();
    } else {
      // starts on the left side
      posX = 0;
      dir = Direction.East;
    }

    return new FishBot(new Position(posX, (int) (Position.getHeightPanel() * rng.nextFloat())),
        (int) Math.round(rng.nextFloat() * SIZE_MODIFIER + BASE_SIZE),
        (int) Math.round(rng.nextFloat() * SPEED_MODIFIER + BASE_SPEED), dir);
  }
  
  @Override
  public String getImageResource() {
    return imageResource;
  }

  @Override
  public double getWidthScale() {
    return widthScale;
  }
}