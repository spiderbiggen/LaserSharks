package lasersharks.behaviour.sizeincrement;

import lasersharks.behaviour.GetSizeIncrementBahaviour;

/**
 * getSizeIncrement behaviour for SeaObjects.
 *
 * @author SEMGroup27
 */
public class DefaultGetSizeIncrementBehaviour implements GetSizeIncrementBahaviour {

  @Override
  public float onCollisionSizeIncrement() {
    return 0.0f;
  }
}
