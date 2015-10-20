package lasersharks;

import lasersharks.controllers.DirectionCallback;

/**
 * LaserShark class.
 *
 */
public class LaserShark extends SeaObject implements DirectionCallback {

  private final String imageResource = "shark.png";
  private final float widthScale = 1.5f;
  private static final int STARTING_AMMO = 10;
  private static final int MAX_AMMO = 10;
  
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
  public LaserShark(Position position, float size, double startSpeed, Direction direction) {
    super(position, size, startSpeed, direction);
    collisionBehaviour = new DefaultCollisionBehaviour(this);
    moveBehaviour = new SharkMoveBehaviour(this);
    eatBehaviour = new DefaultEatBehaviour(this);
    lastHorizontalDirection = Direction.East;
    ammo = STARTING_AMMO;
  }

  @Override
  public String getImageResource() {
    return imageResource;
  }

  @Override
  public double getWidthScale() {
    return widthScale;
  }

  @Override
  public void setDirection(Direction dir) {
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
    Logger.getInstance().write("Loss", "Player has colided with a bigger fish");
    super.kill();
  }

  @Override
  public void putDirection(Direction dir) {
    this.setDirection(dir);
  }
  
  /**
   * decreases the ammo.
   * @param amount the amount to decrease.
   * @return the current ammo of the shark.
   */
  public int decreaseAmmo(int amount) {
    ammo = ammo - amount;
    return ammo;
  }
  
  /**
   * decreases the ammo by one.
   * @return the current ammo of the shark.
   */
  public int decreaseAmmo() {
    return decreaseAmmo(1);
  }
  
  /**
   * returns the current ammo.
   * @return the ammo.
   */
  public int getAmmo() {
    return ammo;
  }
  
  /**
   * returns the maximum ammo.
   * @return maximum ammo.
   */
  public int getMaxAmmo() {
    return MAX_AMMO;
  }
  
  /**
   * increases the ammo.
   * @param amount the amount to increase.
   * @return the current ammo of the shark.
   */
  public int increaseAmmo(int amount) {
    ammo = Math.min(ammo + amount, MAX_AMMO);
    return ammo;
  }
  
  /**
   * gets the last horizontal direction the shark went to.
   * @return the last horizontal direction the shark went to.
   */
  public Direction getLastHorizontalDirection() {
    return lastHorizontalDirection;
  }
}
