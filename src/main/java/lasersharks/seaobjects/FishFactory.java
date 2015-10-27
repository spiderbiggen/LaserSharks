package lasersharks.seaobjects;

import lasersharks.Direction;
import lasersharks.Options;
import lasersharks.Position;
import lasersharks.interfaces.FishSpawner;

import java.util.Random;

/**
 * Default FishFactory implementation.
 * 
 * @author SEMGroup27
 *
 */
public class FishFactory implements FishSpawner {

  /**
   * This value is used to modify the speed of the fishes that are generated. The generated speed is
   * equal to SpeedModifier*RandomNumber, where RandomNumber is a random int between 0 and 100.
   */
  private static final int SPEED_MODIFIER = 600;
  private static final int BASE_SPEED = 70;

  /**
   * This value is used to modify the size of the fishes that are generated. The generated speed is
   * equal to SizeModifier*RandomNumber, where RandomNumber is a random int between 0 and 100.
   */
  private static final int SIZE_MODIFIER = 300;
  private static final int BASE_SIZE = 70;
  /**
   * The string url to the image.
   */
  private static final String[] FISH_RESOURCES = { "enemy-1.png", "enemy-2.png", "enemy-4.png",
      "enemy-5.png", "enemy-6.png", "enemy-7.png", "enemy-8.png", "enemy-10.png", "enemy-12.png" };
  /**
   * store the image sizes as {width, height} pairs.
   */
  private static final double[] FISH_SIZES = { 1.666667, 1.048951, 0.869565, 0.740740, 2.307692,
      1.369863, 1.102941, 1.388880, 1.764706 };
  /**
   * this value is used as the seed for the fish.
   */
  private final Random enemyRng;

  /**
   * initialize the enemy spawner.
   */
  public FishFactory() {
    this.enemyRng = Options.getInstance().getFactoryRng();
  }

  @Override public Fish generateFish(final Random rng) {
    double posX;
    Direction dir;

    final float size = rng.nextFloat() * SIZE_MODIFIER + BASE_SIZE;

    if (rng.nextBoolean()) {
      // starts on the right side
      dir = Direction.West;
      posX = Position.getWidthPanel();
    } else {
      // starts on the left side
      posX = -size;
      dir = Direction.East;
    }

    final int enemyImageIndex = rng.nextInt(FISH_RESOURCES.length);
    return new Fish(FISH_RESOURCES[enemyImageIndex], FISH_SIZES[enemyImageIndex],
        new Position(posX, (int) ((Position.getHeightPanel() - size) * rng.nextFloat())), size,
        (double) Math.round(rng.nextFloat() * SPEED_MODIFIER + BASE_SPEED), dir);
  }

  @Override
  public Fish generateFish() {
    return generateFish(this.enemyRng);
  }
}
