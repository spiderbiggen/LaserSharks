package lasersharks.seaobjects;

import java.util.Random;

import lasersharks.Direction;
import lasersharks.Options;
import lasersharks.Position;
import lasersharks.interfaces.FishSpawner;

/**
 * Default Fishfactory implementation.
 * 
 * @author SEMGroup27
 *
 */
public class FishFactory implements FishSpawner {

  /**
   * This value is used to modify the speed of the fishes that are generated. The generated speed is
   * equal to SpeedModifier*RandomNumber, where RandomNumber is a random int between 0 and 100.
   */
  private static final int SPEED_MODIFIER = 420;
  private static final int BASE_SPEED = 90;

  /**
   * This value is used to modify the size of the fishes that are generated. The generated speed is
   * equal to SizeModifier*RandomNumber, where RandomNumber is a random int between 0 and 100.
   */
  private static final int SIZE_MODIFIER = 210;
  private static final int BASE_SIZE = 85;

  /**
   * this value is used as the seed for the fish.
   */
  private Random enemyRng;

  /**
   * The string url to the image.
   */
  private final String[] fishResources = { "enemy-1.png", "enemy-2.png", "enemy-4.png",
      "enemy-5.png", "enemy-6.png", "enemy-7.png", "enemy-8.png", "enemy-10.png", "enemy-12.png" };

  /**
   * store the image sizes as {width, height} pairs.
   */
  private final Integer[][] fishSizes = { { 180, 300 }, { 286, 300 }, { 345, 300 }, { 405, 300 },
      { 130, 300 }, { 219, 300 }, { 272, 300 }, { 216, 300 }, { 170, 300 } };

  /**
   * initialize the enemy spawner.
   */
  public FishFactory() {
    this.enemyRng = Options.getInstance().getFactoryRng();
  }

  @Override
  public Fish generateFish(Random rng) {
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

    int enemyImageIndex = rng.nextInt(fishResources.length);
    return new Fish(fishResources[enemyImageIndex], fishSizes[enemyImageIndex][1],
        fishSizes[enemyImageIndex][0],
        new Position(posX, (int) ((Position.getHeightPanel() - size) * rng.nextFloat())), size,
        (double) Math.round(rng.nextFloat() * SPEED_MODIFIER + BASE_SPEED), dir);
  }

  @Override
  public Fish generateFish() {
    return generateFish(this.enemyRng);
  }

  @Override
  public void setFishRng(Random newRng) {
    this.enemyRng = newRng;
  }
}
