package lasersharks.seaobjects;

import lasersharks.Direction;
import lasersharks.Logger;
import lasersharks.Position;
import lasersharks.behaviour.collision.SharkCollisionBehaviour;
import lasersharks.behaviour.collisionhitbox.DefaultCollisionHitBoxBehaviour;
import lasersharks.behaviour.move.LaserSharkMoveBehaviour;
import lasersharks.interfaces.DirectionCallback;

/**
 * LaserShark class.
 * 
 * @author SEMGroup27
 *
 */
public class LaserShark extends AbstractSeaObject implements DirectionCallback {

  private static final String IMAGE_RESOURCE = "shark.png";
  private static final float WIDTH_SCALE = 1.628664f;
  private static final int STARTING_AMMO = 5;
  private static final int MAX_AMMO = 5;

  private int ammo;
  private Direction lastHorizontalDirection;

  /**
   * Constructor class for LaserShark.
   * 
   * @param position
   *          initial position
   * @param size
   *          init size
   * @param startSpeed
   *          init speed
   * @param direction
   *          init direction
   */
  public LaserShark(final Position position, final float size, final double startSpeed,
      final Direction direction) {
    super(position, size, startSpeed, direction);
    collisionHitBoxBehaviour = new DefaultCollisionHitBoxBehaviour(this);
    moveBehaviour = new LaserSharkMoveBehaviour(this);
    lastHorizontalDirection = Direction.East;
    collisionBehaviour = new SharkCollisionBehaviour(this);
    ammo = STARTING_AMMO;
  }

  @Override
  public String getImageResource() {
    return IMAGE_RESOURCE;
  }

  @Override
  public double getWidthScale() {
    return WIDTH_SCALE;
  }

  @Override public void setDirection(final Direction dir) {
    if (dir.equals(Direction.East) || dir.equals(Direction.West)) {
      lastHorizontalDirection = dir;
    }
    if (this.isAlive() && this.getDirection() != dir) {
      Logger.getInstance().write("Change of direction",
          "From: " + this.getDirection() + ", to: " + dir);
    }
    super.setDirection(dir);
  }

  @Override
  public void kill() {
    Logger.getInstance().write("Loss", "Player has collided with a bigger fish");
    super.kill();
  }

  @Override public void putDirection(final Direction dir) {
    this.setDirection(dir);
  }

  /**
   * decreases the ammo.
   * 
   * @param amount
   *          the amount to decrease.
   * @return the current ammo of the shark.
   */
  public int decreaseAmmo(final int amount) {
    ammo = ammo - amount;
    return ammo;
  }

  /**
   * decreases the ammo by one.
   * 
   * @return the current ammo of the shark.
   */
  public int decreaseAmmo() {
    return decreaseAmmo(1);
  }

  /**
   * returns the current ammo.
   * 
   * @return the ammo.
   */
  public int getAmmo() {
    return ammo;
  }

  /**
   * gets the last horizontal direction the shark went to.
   * 
   * @return the last horizontal direction the shark went to.
   */
  public Direction getLastHorizontalDirection() {
    return lastHorizontalDirection;
  }
  
  /**
   * Increment ammunition.
   * @param onCollisionAmmunitionIncrement Ammunition increment.
   */
  public void increaseAmmunition(final int onCollisionAmmunitionIncrement) {
    this.ammo = Math.min(this.ammo + onCollisionAmmunitionIncrement, MAX_AMMO);
  }

  /**
   * Lasersharks are collisionActors.
   * @return true.
   */
  public boolean collisionActor() {
    return true;
  }
  
}
