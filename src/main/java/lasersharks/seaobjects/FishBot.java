package lasersharks.seaobjects;

import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.behaviour.collisionHitbox.DefaultCollisionHitboxBehaviour;
import lasersharks.behaviour.move.DefaultMoveBehaviour;
import lasersharks.behaviour.DefaultEatBehaviour;

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
    collisionHitBoxBehaviour = new DefaultCollisionHitboxBehaviour(this);
    moveBehaviour = new DefaultMoveBehaviour(this);
    eatBehaviour = new DefaultEatBehaviour();
  }
}