package lasersharks.behaviour.collisionhitbox;

import javafx.scene.shape.Rectangle;
import lasersharks.Position;
import lasersharks.behaviour.CollisionHitBoxBehaviour;
import lasersharks.interfaces.Displayable;

/**
 * the default collision behaviour.
 * 
 * @author sytze
 *
 */
@SuppressWarnings("restriction")
public class DefaultCollisionHitBoxBehaviour implements CollisionHitBoxBehaviour {

  private static final double WIDTH_TO_COORD = 0.85;
  private static final double SIZE_TO_COORD = 0.075;
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
    
    //float distance = this.displayable.getMiddlePoint()
    //    .calculateDistance(swimmer.getMiddlePoint());
    //return distance < this.displayable.getSize() + swimmer.getSize();
  }

  /**
   * Gets the middle point of the swimmer.
   */
  @Override
  public Position getMiddlePoint() {
    final Position startPos = displayable.getPosition();
    return new Position(
        startPos.getPosX() + (HALF_SCALE * displayable.getWidthScale() * displayable.getSize()),
        startPos.getPosY() + (HALF_SCALE * displayable.getSize()));
  }

  /**
   * Draw a rectangle shaped hitbox around the fishbot.
   * 
   * @return a rectangle hitbox.
   */
  public Rectangle makeHitBox() {
    final double xCoordinate = displayable.getPosition().getPosX();
    final double yCoordinate = displayable.getPosition().getPosY();
    return new Rectangle(xCoordinate + SIZE_TO_COORD * displayable.getSize(),
        yCoordinate + SIZE_TO_COORD * displayable.getSize(),
        displayable.getWidthScale() * displayable.getSize() * WIDTH_TO_COORD,
        displayable.getSize() * WIDTH_TO_COORD);
  }
}
