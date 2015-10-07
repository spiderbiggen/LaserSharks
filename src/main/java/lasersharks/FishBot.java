package lasersharks;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import lasersharks.enemies.Enemy1;
import lasersharks.enemies.Enemy10;
import lasersharks.enemies.Enemy12;
import lasersharks.enemies.Enemy2;
import lasersharks.enemies.Enemy4;
import lasersharks.enemies.Enemy5;
import lasersharks.enemies.Enemy6;
import lasersharks.enemies.Enemy7;
import lasersharks.enemies.Enemy8;

/**
 * This class represent the fishes on the screen that are not player controllable.
 * 
 * @author Sytze, Youri
 *
 */
public abstract class FishBot extends Fish {

  /**
   * This value is used to modify the speed of the fishes that are generated. The generated speed is
   * equal to SpeedModifier*RandomNumber, where RandomNumber is a random int between 0 and 100.
   */
  private static final int SPEED_MODIFIER = 420;
  private static final int BASE_SPEED = 80;

  /**
   * This value is used to modify the size of the fishes that are generated. The generated speed is
   * equal to SizeModifier*RandomNumber, where RandomNumber is a random int between 0 and 100.
   */

  private static final int SIZE_MODIFIER = 200;
  private static final int BASE_SIZE = 30;

  @SuppressWarnings("unchecked")
  private static final Class<? extends FishBot>[] FISH_CLASSES = new Class[] { Enemy1.class,
      Enemy2.class, Enemy4.class, Enemy5.class, Enemy6.class, Enemy7.class, Enemy8.class,
      Enemy10.class, Enemy12.class };

  @SuppressWarnings("unchecked")
  private static final Class<? extends Object>[] CONSTRUCTOR_HEAD = new Class[] { Position.class,
      java.lang.Float.class, java.lang.Double.class, Direction.class };

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
  public FishBot(Position position, float size, double speed, Direction direction) {
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
    double posX;
    Direction dir;
    float size = rng.nextFloat() * SIZE_MODIFIER + BASE_SIZE;
    
    if (rng.nextBoolean()) {
      // starts on the right side
      dir = Direction.West;
      posX = Position.getWidthPanel();
    } else {
      // starts on the left side
      posX = -size;
      dir = Direction.East;
    }

    try {
      return getRandomFishClass(rng).getDeclaredConstructor(CONSTRUCTOR_HEAD).newInstance(
          new Position(posX, (int) ((Position.getHeightPanel() - size) * rng.nextFloat())),
          Float.valueOf(size),
          Double.valueOf(Math.round(rng.nextFloat() * SPEED_MODIFIER + BASE_SPEED)), dir);
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
      Logger.getInstance().write(e.getClass().getName() + " exception", e.getMessage());
      e.printStackTrace();
      return null;
    }
  }

  private static Class<? extends FishBot> getRandomFishClass(Random rng) {
    return FISH_CLASSES[rng.nextInt(FISH_CLASSES.length)];
  }
}