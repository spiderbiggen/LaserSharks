package lasersharks;

/**
 * LaserShark class.
 *
 */
public class LaserShark implements Fish {

  private Position position;
  private Direction direction;
  private float size;

  /**
   * Initializes the laserShark(player).
   * 
   * @param position
   *          the starting position
   * @param direction
   *          the starting direction
   * @param size
   *          the starting size
   */
  public LaserShark(Position position, Direction direction, float size) {
    this.position = position;
    this.direction = direction;
    this.size = size;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public void setSize(float size) {
    this.size = size;
  }

  @Override
  public Position getPosition() {
    return this.position;
  }

  public void setNextMove(Direction newDirection) {
    this.direction = newDirection;
  }

  @Override
  public float getSize() {
    return size;
  }

  public boolean collision(Fish fish) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean move() {
    return getPosition().updatePosition(getDirection());
  }

}
