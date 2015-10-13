package lasersharks.enemies;

import java.util.Random;

import lasersharks.Direction;
import lasersharks.FishBot;
import lasersharks.Position;

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
  
  private static final String[] FISH_IMAGES = {
      "enemy-1.png",
      "enemy-2.png",
      "enemy-4.png",
      "enemy-5.png",
      "enemy-6.png",
      "enemy-7.png",
      "enemy-8.png",
      "enemy-10.png",
      "enemy-12.png",
  };
  
  private static final Integer[][] IMAGE_SIZE = {
      {36, 60},
      {46, 49},
      {69, 60},
      {90, 67},
      {69, 157},
      {84, 113},
      {138, 151},
      {54, 75},
      {54, 94}
  };
  
  /**
   * initialize the fishspawner.
   */
  public FishFactory() {
    this.rng = new Random();
  }

  @Override
  public FishBot generateFish(Random rng) {
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
    
    int enemyImageIndex = rng.nextInt(Math.min(FISH_IMAGES.length, IMAGE_SIZE.length));
    return new Enemy(
        FISH_IMAGES[enemyImageIndex], 
        IMAGE_SIZE[enemyImageIndex][1], 
        IMAGE_SIZE[enemyImageIndex][0],
        new Position(posX, (int) ((Position.getHeightPanel() - size) * rng.nextFloat())), 
        size, 
        (double) Math.round(rng.nextFloat() * SPEED_MODIFIER + BASE_SPEED), 
        dir
    );
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
