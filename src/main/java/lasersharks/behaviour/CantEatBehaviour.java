package lasersharks.behaviour;

import lasersharks.Displayable;

/**
 * represents behaviour of a fish that cant eat an other fish.
 * 
 * @author SEMGroup27
 *
 */
public class CantEatBehaviour implements EatBehaviour {

  /**
   * the constructor of the behaviour object.
   */
  public CantEatBehaviour() {
  }

  /**
   * does nothing, as this behavour specifies a fish that cant eat.
   * 
   * @param fish
   *          the fish it should eat.
   */
  @Override
  public void eat(Displayable fish) {
    // DO NOTHING
  }

}
