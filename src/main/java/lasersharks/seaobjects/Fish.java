package lasersharks.seaobjects;

import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.behaviour.checkforloss.FishCheckForLossBehaviour;
import lasersharks.behaviour.collision.FishCollisionBehaviour;
import lasersharks.behaviour.eaten.FishEatenBehaviour;
import lasersharks.behaviour.lasercollision.LaserLaserCollisionBehaviour;
import lasersharks.behaviour.sizedecrement.FishSizeDecrementBehaviour;
import lasersharks.behaviour.sizeincrement.FishGetSizeIncrementBehaviour;

/**
 * Class for the first enemy.
 * 
 * @author SEMGroup27
 *
 */
public class Fish extends SeaObject {
  private String image;
  private int imgHeight;
  private int imgWidth;

  /**
   * propagation for construction.
   * 
   * @param image
   *          Image of the enemy.
   * @param imgHeight
   *          Height of image.
   * @param imgWidth
   *          Width of image.
   * @param position
   *          Starting position.
   * @param size
   *          Starting size.
   * @param speed
   *          Starting speed.
   * @param direction
   *          Starting direction.
   */
  public Fish(String image, int imgHeight, int imgWidth, Position position, Float size,
      Double speed, Direction direction) {

    super(position, size, speed, direction);
    this.image = image;
    this.imgHeight = imgHeight;
    this.imgWidth = imgWidth;
    this.checkForLossBehaviour = new FishCheckForLossBehaviour(this);
    this.eatenBehaviour = new FishEatenBehaviour(this);
    this.getSizeIncrementBahaviour = new FishGetSizeIncrementBehaviour(this);
    this.sizeDecrementBahaviour = new FishSizeDecrementBehaviour(this);
    this.collisionBehaviour = new FishCollisionBehaviour(this);
  }

  @Override
  public String getImageResource() {
    return image;
  }

  @Override
  public double getWidthScale() {
    return ((double) imgHeight) / ((double) imgWidth);
  }

  /**
   * Fishes are collisionActors
   */
  public boolean collisionActor() {
    return true;
  }
  
}
