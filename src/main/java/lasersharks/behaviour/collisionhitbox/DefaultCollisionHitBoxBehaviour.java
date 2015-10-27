package lasersharks.behaviour.collisionhitbox;

import javafx.scene.shape.Rectangle;
import lasersharks.behaviour.CollisionHitBoxBehaviour;
import lasersharks.interfaces.Displayable;

/**
 * the default collision behaviour.
 *
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public class DefaultCollisionHitBoxBehaviour implements CollisionHitBoxBehaviour {

  private static final double WIDTH_TO_COORDINATES = 0.85;
  private static final double SIZE_TO_COORDINATES = 0.075;
  private static final double HALF_SCALE = 0.5f;

  private final Displayable displayable;

  /**
   * the constructor.
   *
   * @param displayable
   *          the swimmer it should apply to.
   */
  public DefaultCollisionHitBoxBehaviour(final Displayable displayable) {
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
  public boolean collide(final Displayable swimmer) {
    return swimmer.makeHitBox().intersects(displayable.makeHitBox().getLayoutBounds());
  }

  /**
   * Draw a rectangle shaped hit box around the fish.
   *
   * @return a rectangle hit box.
   */
  public Rectangle makeHitBox() {
    final double xCoordinate = displayable.getPosition().getPosX();
    final double yCoordinate = displayable.getPosition().getPosY();
    return new Rectangle(xCoordinate + SIZE_TO_COORDINATES * displayable.getSize(),
        yCoordinate + SIZE_TO_COORDINATES * displayable.getSize(),
        displayable.getWidthScale() * displayable.getSize() * WIDTH_TO_COORDINATES,
        displayable.getSize() * WIDTH_TO_COORDINATES);
  }
}
