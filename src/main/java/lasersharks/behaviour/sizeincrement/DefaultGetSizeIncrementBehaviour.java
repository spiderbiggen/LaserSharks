package lasersharks.behaviour.sizeincrement;

import lasersharks.behaviour.GetSizeIncrementBehaviour;

/**
 * getSizeIncrement behaviour for SeaObjects.
 * @author SEMGroup27
 *
 */
public class DefaultGetSizeIncrementBehaviour implements GetSizeIncrementBehaviour {
  @Override
  public float onCollisionSizeIncrement() {
    return 0.0f;
  }
}
