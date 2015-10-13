package lasersharks;

/**
 * LaserShark class.
 *
 */
public class LaserShark extends Fish implements DirectionCallback {

  private final String imageResource = "shark.png";
  private final float widthScale = 1.5f;
  private static final int STARTING_AMMO = 10;
  
  private int ammo;
  
  /**
   * Constructor class for FishBot.
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
    ammo =- amount;
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
   * increases the ammo.
   * @param amount the amount to increase.
   * @return the current ammo of the shark.
   */
  public int increaseAmmo(int amount) {
    ammo =+ amount;
    return ammo;
  }
}
