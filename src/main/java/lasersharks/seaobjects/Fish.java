package lasersharks.seaobjects;

import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.behaviour.checkforloss.FishCheckForLossBehaviour;
import lasersharks.behaviour.collision.FishCollisionBehaviour;
import lasersharks.behaviour.eaten.FishEatenBehaviour;
import lasersharks.behaviour.highscoreincrement.FishHighScoreIncrementBehaviour;
import lasersharks.behaviour.sizedecrement.FishSizeDecrementBehaviour;
import lasersharks.behaviour.sizeincrement.FishGetSizeIncrementBehaviour;

/**
 * Class for the first enemy.
 * 
 * @author SEMGroup27
 *
 */
public class Fish extends SeaObject {
  private final String image;
  private final double widthScale;

  /**
   * propagation for construction.
   * 
   * @param image
   *          Image of the enemy.
   * @param widthScale
   *          The aspect ratio between the width and the height.
   * @param position
   *          Starting position.
   * @param size
   *          Starting size.
   * @param speed
   *          Starting speed.
   * @param direction
   *          Starting direction.
   */
  public Fish(final String image, final double widthScale, final Position position,
      final Float size, final Double speed, final Direction direction) {

    super(position, size, speed, direction);
    this.image = image;
    this.widthScale = widthScale;
    checkForLossBehaviour = new FishCheckForLossBehaviour(this);
    eatenBehaviour = new FishEatenBehaviour(this);
    getSizeIncrementBahaviour = new FishGetSizeIncrementBehaviour(this);
    sizeDecrementBahaviour = new FishSizeDecrementBehaviour(this);
    collisionBehaviour = new FishCollisionBehaviour(this);
    highScoreIncrementBehaviour = new FishHighScoreIncrementBehaviour(this);
  }

  /**
   * Will return the string resource for this seaObject.
   *
   * @return The resource's name
   */
  @Override
  public String getImageResource() {
    return image;
  }

  /**
   * return the aspect ratio between the width and the height. height/width
   *
   * @return the aspect ratio
   */
  @Override
  public double getWidthScale() {
    return widthScale;
  }

  /**
   * Fishes are collisionActors.
   * @return true
   */
  public boolean collisionActor() {
    return true;
  }
  
}
