package lasersharks.behaviour.collision;

import lasersharks.Highscores;
import lasersharks.behaviour.CollisionBehaviour;
import lasersharks.interfaces.Displayable;

public class SharkCollisionBehaviour implements CollisionBehaviour{
  Displayable object;
  
  /**
   * Constructor.
   * @param me this.
   */
  public SharkCollisionBehaviour(Displayable me) {
    super();
    this.object = me;
  }

  @Override
  public synchronized void colideWith(Displayable other) {
    other.onCollisionPlayerLoses(object.getSize());
    object.increaseSize(other.onCollisionSizeIncrement());
    object.increaseAmmunition(other.onCollisionAmmunitionIncrement());
    Highscores.getInstance().increaseScore(
        other.getOnCollisionHighScoreIncrement(
            Highscores.getInstance().getTimePenalty()
         )
    );
    other.onCollisionEaten();
  }
  
}
