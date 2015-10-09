package lasersharks.enemies;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import lasersharks.Direction;
import lasersharks.FishBot;
import lasersharks.Logger;
import lasersharks.Position;

/**
 * Default Fishfactory implementation.
 * 
 * @author Sytze
 *
 */
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

  /**
   * this value is used as the seed. Only used when useSeed = true;
   */
  private Random rng;

  @SuppressWarnings("unchecked")
  private static final Class<? extends FishBot>[] FISH_CLASSES = new Class[] { Enemy1.class,
      Enemy2.class, Enemy4.class, Enemy5.class, Enemy6.class, Enemy7.class, Enemy8.class,
      Enemy10.class, Enemy12.class };

  @SuppressWarnings("unchecked")
  private static final Class<? extends Object>[] CONSTRUCTOR_HEAD = new Class[] { Position.class,
      java.lang.Float.class, java.lang.Double.class, Direction.class };

  /**
   * initialize the fishspawner.
   */
  public DefaultFishSpawner() {
    this.rng = new Random();
  }

  @Override
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

  @Override
  public FishBot generateFish() {
    return generateFish(this.rng);
  }

  @Override
  public void setRng(Random newRng) {
    this.rng = newRng;
  }
}
