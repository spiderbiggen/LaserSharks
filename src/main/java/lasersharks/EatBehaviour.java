package lasersharks;

/**
 * the interface of objects that represent an eat behaviour
 * @author sytze
 *
 */
public interface EatBehaviour {
  
  /**
   * eats an other fish.
   * @param fish the fish that should be eaten.
   */
  public void eat(Swimmer fish);
}
