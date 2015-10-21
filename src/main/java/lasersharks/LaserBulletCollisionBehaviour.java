package lasersharks;

import javafx.scene.shape.Rectangle;
import lasersharks.behaviour.interfaces.CollisionBehaviour;
import lasersharks.interfaces.Displayable;

public class LaserBulletCollisionBehaviour implements CollisionBehaviour {
  
  private static final double WIDTH_TO_COORD = 0.85;
  private static final double SIZE_TO_COORD = 0.075;
  private static final double HALF_SCALE = 0.5f;
  private static final float DEVIDE_DECREASE_SIZE = 10;
  
  private Displayable swimmer;
  
  /**
   * the constructor.
   * @param swimmer the swimmer it should apply to.
   */
  public LaserBulletCollisionBehaviour(Displayable swimmer) {
    this.swimmer = swimmer;
  }
  
  
  @Override
  public Position getMiddlePoint() {
    Position startPos = swimmer.getPosition();
    Position middlePointPosition = new Position(
        startPos.getPosX() + (HALF_SCALE * swimmer.getWidthScale() * swimmer.getSize()),
        startPos.getPosY() + (HALF_SCALE * swimmer.getSize()));
    return middlePointPosition;
  }

  @Override
  public Rectangle makeHitbox() {
    double xcoordinate = swimmer.getPosition().getPosX();
    double ycoordinate = swimmer.getPosition().getPosY();
    Rectangle rekt = new Rectangle(xcoordinate + SIZE_TO_COORD * swimmer.getSize(),
        ycoordinate + SIZE_TO_COORD * swimmer.getSize(),
        swimmer.getWidthScale() * swimmer.getSize() * WIDTH_TO_COORD, swimmer.getSize() * WIDTH_TO_COORD);
    return rekt;
  }

  @Override
  public boolean collide(Displayable Enemy) {
    if (swimmer instanceof Displayable) {
      swimmer.decreaseSize(swimmer.getSize() / DEVIDE_DECREASE_SIZE);
      this.swimmer.kill();
    }
    return true;
  }

}
