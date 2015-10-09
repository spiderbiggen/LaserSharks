package lasersharks;

public class SharkMoveBehaviour implements MoveBehaviour {
  private static final float HALF_RATE = 0.5F;
  Swimmer swimmer;
  
  public SharkMoveBehaviour(Swimmer swimmer) {
    this.swimmer = swimmer;
  }
  
  public boolean move(double frametime) {
    swimmer.getPosition().updatePosition(swimmer.getDirection(), (swimmer.getSpeed() / frametime), swimmer.getSize());
    // this will make sure the fish stay within both

    swimmer.getPosition().clipPosition((swimmer.getSize() * swimmer.getWidthScale() * HALF_RATE),
        (swimmer.getSize() * HALF_RATE));
    return true;
  }
}
