package lasersharks;

/**
 * Interface Fish.
 */
public interface Fish {

  /**
   * Returns the Position of this fish.
   * 
   * @return the current position
   */
  Position getPosition();

  /**
   * Returns the Size of this fish.
   * 
   * @return the current size
   */
  float getSize();

  /**
   * The current fish will move, this will return false if it moves out of the view.
   * 
   * @return true if fish is in view
   */
  boolean move();
  
  
  void setPosition(Position position);
}
