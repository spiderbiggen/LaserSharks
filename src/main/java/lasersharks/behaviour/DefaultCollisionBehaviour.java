package lasersharks.behaviour;

import javafx.scene.shape.Rectangle;
import lasersharks.Position;
import lasersharks.behaviour.interfaces.CollisionBehaviour;
import lasersharks.interfaces.Displayable;

/**
 * the default collision behaviour.
 * 
 * @author sytze
 *
 */
public class DefaultCollisionBehaviour implements CollisionBehaviour {

  private static final double WIDTH_TO_COORD = 0.85;
  private static final double SIZE_TO_COORD = 0.075;
  private static final double HALF_SCALE = 0.5f;

  private Displayable displayable;

  /**
   * the constructor.
   * 
   * @param displayable
   *          the swimmer it should apply to.
   */
  public DefaultCollisionBehaviour(Displayable displayable) {
    this.displayable = displayable;
  }

  /**
   * Checks if two fishes collide.
   * 
   * @param swimmer
   *          the swimmer to check for collision
   * @return true if the fish collide
   */
  @Override
  public boolean collide(Displayable swimmer) {
    float distance = this.displayable.getMiddlePoint().calculateDistance(swimmer.getMiddlePoint());
    return distance < this.displayable.getSize() + swimmer.getSize();
  }

  /**
   * Gets the middlepoint of the swimmer.
   */
  @Override
  public Position getMiddlePoint() {
    Position startPos = displayable.getPosition();
    Position middlePointPosition = new Position(
        startPos.getPosX() + (HALF_SCALE * displayable.getWidthScale() * displayable.getSize()),
        startPos.getPosY() + (HALF_SCALE * displayable.getSize()));
    return middlePointPosition;
  }

  /**
   * Draw a rectangle shaped hitbox around the fishbot.
   * 
   * @return a rectangle hitbox.
   */
  public Rectangle makeHitbox() {
    double xcoordinate = displayable.getPosition().getPosX();
    double ycoordinate = displayable.getPosition().getPosY();
    Rectangle rekt = new Rectangle(xcoordinate + SIZE_TO_COORD * displayable.getSize(),
        ycoordinate + SIZE_TO_COORD * displayable.getSize(),
        displayable.getWidthScale() * displayable.getSize() * WIDTH_TO_COORD,
        displayable.getSize() * WIDTH_TO_COORD);
    return rekt;
  }
}
