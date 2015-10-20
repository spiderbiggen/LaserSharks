package lasersharks;

import javafx.scene.shape.Rectangle;

/**
 * the default collision behaviour.
 * @author sytze
 *
 */
public class DefaultCollisionBehaviour implements CollisionBehaviour {

  private static final double WIDTH_TO_COORD = 0.85;
  private static final double SIZE_TO_COORD = 0.075;
  private static final double HALF_SCALE = 0.5f;
  
  Displayable swimmer;
  
  /**
   * the constructor.
   * @param swimmer the swimmer it should apply to.
   */
  public DefaultCollisionBehaviour(Displayable swimmer) {
    this.swimmer = swimmer;
  }
  
  /**
   * Checks if two fishes collide.
   * @param swimmer the swimmer to check for collision
   * @return true if the fish collide
   */
  @Override
  public boolean collide(Displayable swimmer) {
    float distance = this.swimmer.getMiddlePoint().calculateDistance(swimmer.getMiddlePoint());
    return distance < this.swimmer.getSize() + swimmer.getSize();
  }

  /**
   * Gets the middlepoint of the swimmer.
   */
  @Override
  public Position getMiddlePoint() {
    Position startPos = swimmer.getPosition();
    Position middlePointPosition = new Position(
        startPos.getPosX() + (HALF_SCALE * swimmer.getWidthScale() * swimmer.getSize()),
        startPos.getPosY() + (HALF_SCALE * swimmer.getSize()));
    return middlePointPosition;
  }


  /**
   * Draw a rectangle shaped hitbox around the fishbot.
   * 
   * @return a rectangle hitbox.
   */
  public Rectangle makeHitbox() {
    double xcoordinate = swimmer.getPosition().getPosX();
    double ycoordinate = swimmer.getPosition().getPosY();
    Rectangle rekt = new Rectangle(xcoordinate + SIZE_TO_COORD * swimmer.getSize(),
        ycoordinate + SIZE_TO_COORD * swimmer.getSize(),
        swimmer.getWidthScale() * swimmer.getSize() * WIDTH_TO_COORD, swimmer.getSize() * WIDTH_TO_COORD);
    return rekt;
  }
}
