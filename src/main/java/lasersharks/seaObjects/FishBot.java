package lasersharks.seaObjects;

import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.behaviour.DefaultMoveBehaviour;
import lasersharks.behaviour.DefaultEatBehaviour;
import lasersharks.behaviour.DefaultCollisionBehaviour;

/**
 * This class represent the fishes on the screen that are not player controllable.
 * 
 * @author SEMGroup27
 *
 */
public abstract class FishBot extends SeaObject {

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
    collisionBehaviour = new DefaultCollisionBehaviour(this);
    moveBehaviour = new DefaultMoveBehaviour(this);
    eatBehaviour = new DefaultEatBehaviour();
  }
}