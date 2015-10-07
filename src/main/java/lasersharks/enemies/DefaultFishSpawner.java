package lasersharks.enemies;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import lasersharks.Direction;
import lasersharks.FishBot;
import lasersharks.Logger;
import lasersharks.Position;

public class DefaultFishSpawner implements FishSpawner {

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
  private static final Class<? extends FishBot>[] FISH_CLASSES = new Class[] { ImageEnemy1.class,
      ImageEnemy2.class, ImageEnemy4.class, ImageEnemy5.class, ImageEnemy6.class, ImageEnemy7.class, ImageEnemy8.class,
      ImageEnemy10.class, ImageEnemy12.class };

  @SuppressWarnings("unchecked")
  private static final Class<? extends Object>[] CONSTRUCTOR_HEAD = new Class[] { Position.class,
      java.lang.Float.class, java.lang.Double.class, Direction.class };
  
  /**
   * This function creates a new FishBot with random values. This should be used to spawn fishes.
   * Starts on either the left side or the right side.
   * 
   * @return a random fish with random speed, size and position.
   * @param rng
   *          random number generator to use.
   */
  public FishBot generateFish(Random rng) {
    double posX;
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

    float size = rng.nextFloat() * SIZE_MODIFIER + BASE_SIZE;
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
  
  /**
   * This function creates a new FishBot with random values. This should be used to spawn fishes.
   * Starts on either the left side on
   * 
   * @return a random fish with random speed, size and position.
   */
  public FishBot generateFish() {
    return generateFish(new Random());
  }

}
