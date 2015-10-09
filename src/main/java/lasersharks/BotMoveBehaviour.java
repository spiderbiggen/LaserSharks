package lasersharks;


public class BotMoveBehaviour implements MoveBehaviour {
  
  Swimmer swimmer;
  
  public BotMoveBehaviour(Swimmer swimmer) {
    this.swimmer = swimmer;
  }
  
  @Override
  public boolean move(double frametime) {
    return swimmer.getPosition().updatePosition(swimmer.getDirection(), (swimmer.getSpeed() / frametime), swimmer.getSize());
  }

}
