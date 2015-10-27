package lasersharks.behaviour;

/**
 * Behaviour to check if the game has been lost.
 */
public interface CheckForLossBehaviour {

  /**
   * Check to see if player has lost the game.
   *
   * @param size shark size.
   */
  void onCollisionPlayerLoses(float size);
}
