package lasersharks;

/**
 * represents behaviour of a fish that cant eat an other fish.
 * @author sytze
 *
 */
public class CantEatBehaviour implements EatBehaviour{
  
  /**
   * the constructor of the behaviour object.
   * @param swimmer the swimmer it should be applied to.
   */
  public CantEatBehaviour(Displayable swimmer){
  }
  
  /**
   * does nothing, as this behavour specifies a fish that cant eat.
   * @param fish the fish it should eat.
   */
  @Override
  public void eat(Displayable fish) {
    //DO NOTHING
  }

}
