package lasersharks.behaviour;

import lasersharks.behaviour.interfaces.MoveBehaviour;
import lasersharks.interfaces.Displayable;

/**
 * represents the moving behaviour of a FishBot.
 * @author sytze
 *
 */
public class BotMoveBehaviour implements MoveBehaviour {
  
  Displayable swimmer;
  
  /**
   * the constructor.
   * @param swimmer the fish this behaviour applies to.
   */
  public BotMoveBehaviour(Displayable swimmer) {
    this.swimmer = swimmer;
  }
  
  /**
   * moves the FishBot in the right direction.
   * @param frametime the frametime of the screen.
   * @return true of the fish was able to move.
   */
  @Override
  public boolean move(double frametime) {
    return swimmer.getPosition().updatePosition(swimmer.getDirection(), (swimmer.getSpeed() / frametime), swimmer.getSize());
  }

}
