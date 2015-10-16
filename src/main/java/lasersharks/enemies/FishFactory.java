package lasersharks.enemies;

import java.util.Random;

//import groovyjarjarcommonscli.Options;
import lasersharks.Direction;
import lasersharks.FishBot;
import lasersharks.LaserBullet;
import lasersharks.LaserShark;
import lasersharks.Position;
import lasersharks.controllers.Options;

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
   * These values imply the default values for a laser object.
   */
  private static final int LASER_SIZE = 13;
  private static final int LASER_SPEED = 800;

  /**
   * this value is used as the seed. Only used when useSeed = true;
   */
  private Random rng;
  
  /**
   * Struct for holding fishImageData.
   * 
   * It appears that many Java people are not familiar with the Sun Java Coding Guidelines 
   * which say it is quite appropriate to use public instance variable
   *  when the class is essentially a "Struct", 
   * if Java supported "struct" (when there is no behavior).
   * People tend to think getters and setters are the Java way, 
   * as if they are at the heart of Java. This is not so. 
   * If you follow the Sun Java Coding Guidelines, 
   * using public instance variables in appropriate situations, 
   * you are actually writing better code than cluttering it with needless getters and setters.
   * 
   * Java Code Conventions from 1999 and still unchanged.
   * 10.1 Providing Access to Instance and Class Variables
   * Don't make any instance or class variable public without good reason. Often, 
   * instance variables don't need to be explicitly set or gotten-often 
   * that happens as a side effect of method calls.
   * One example of appropriate public instance variables is 
   * the case where the class is essentially a data structure,  with no behavior. *In other words, 
   * if you would have used a struct instead of a class (if Java supported struct), 
   * then it's appropriate to make the class's instance variables public.*
   * 
   * http://www.oracle.com/technetwork/java/javase/documentation/codeconventions-137265.html#177
   * http://en.wikipedia.org/wiki/Plain_old_data_structure
   * http://docs.oracle.com/javase/1.3/docs/guide/collections/designfaq.html#28
   * 
   * @author SEMGroup27
   *
   */
  private class FishImage {
    public String image;
    public int height;
    public int width;
    
    public FishImage(String image, int height, int width) {
      this.image = image;
      this.height = height;
      this.width = width;
    }
  }
  
  private final FishImage[] fishImages = {
      new FishImage("enemy-1.png", 36, 60),
      new FishImage("enemy-2.png", 46, 49),
      new FishImage("enemy-4.png", 69, 60),
      new FishImage("enemy-5.png", 36, 60),
      new FishImage("enemy-6.png", 36, 60),
      new FishImage("enemy-7.png", 36, 60),
      new FishImage("enemy-8.png", 36, 60),
      new FishImage("enemy-10.png", 36, 60),
      new FishImage("enemy-12.png", 36, 60)
  };
  
  /**
   * initialize the fishspawner.
   */
  public FishFactory() {
    this.rng = Options.getInstance().getFactoryRng();
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
    
    int enemyImageIndex = rng.nextInt(fishImages.length);
    return new Enemy(
        fishImages[enemyImageIndex].image, 
        fishImages[enemyImageIndex].width, 
        fishImages[enemyImageIndex].height,
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
  
  @Override
  public LaserBullet createLaser(LaserShark origin) {
    Position posShark = origin.getPosition();
    Position posLaser = new Position(posShark.getPosX(), posShark.getPosY());
    return new LaserBullet(posLaser, LASER_SIZE, LASER_SPEED, origin.getLastHorizontalDirection());
  }
}
