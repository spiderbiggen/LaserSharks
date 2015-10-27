package lasersharks.behaviour.collisionHitbox;

import javafx.scene.shape.Rectangle;
import lasersharks.Position;
import lasersharks.behaviour.CollisionHitBoxBehaviour;
import lasersharks.interfaces.Displayable;

/**
 * The default collision behaviour.
 * 
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction") public class DefaultCollisionHitBoxBehaviour
    implements CollisionHitBoxBehaviour {

  private static final double WIDTH_TO_COORDINATES = 0.85;
  private static final double SIZE_TO_COORDINATES = 0.075;
  private static final double HALF_SCALE = 0.5f;
  private Displayable displayable;

  /**
   * The constructor.
   * 
   * @param displayable
   *          the swimmer it should apply to.
   */
  public DefaultCollisionHitBoxBehaviour(Displayable displayable) {
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
    return swimmer.makeHitBox().intersects(displayable.makeHitBox().getLayoutBounds());
  }

  /**
   * Gets the middle point of the swimmer.
   */
  @Override
  public Position getMiddlePoint() {
    Position startPos = displayable.getPosition();
    return new Position(
        startPos.getPosX() + (HALF_SCALE * displayable.getWidthScale() * displayable.getSize()),
        startPos.getPosY() + (HALF_SCALE * displayable.getSize()));
  }

  /**
   * Draw a rectangle shaped hit box around the fish.
   *
   * @return a rectangle hit box.
   */
  public Rectangle makeHitBox() {
    double xCoordinate = displayable.getPosition().getPosX();
    double yCoordinate = displayable.getPosition().getPosY();
    return new Rectangle(xCoordinate + SIZE_TO_COORDINATES * displayable.getSize(),
        yCoordinate + SIZE_TO_COORDINATES * displayable.getSize(),
        displayable.getWidthScale() * displayable.getSize() * WIDTH_TO_COORDINATES,
        displayable.getSize() * WIDTH_TO_COORDINATES);
  }
}
