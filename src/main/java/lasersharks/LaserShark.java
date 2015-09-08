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

  /**
   * 
   * @return the direction.
   */
  public Direction getDirection() {
    return direction;
  }

  /**
   * 
   * @param direction
   *          the direction to set.
   */
  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  /**
   * 
   * @param position
   *          the position to set.
   */
  public void setPosition(Position position) {
    this.position = position;
  }

  /**
   * 
   * @param size
   *          the size to set.
   */
  public void setSize(float size) {
    this.size = size;
  }

  /**
   * @return the position
   */
  @Override
  public Position getPosition() {
    return this.position;
  }

  /**
   * 
   * @param newDirection
   *          the direction to set to.
   */
  public void setNextMove(Direction newDirection) {
    setDirection(newDirection);
  }

  /**
   * @return the size.
   */
  @Override
  public float getSize() {
    return size;
  }

  /**
   * 
   * @param fish
   *          the fish we want to check if it collides with.
   * @return true if it collides.
   */
  public boolean collision(Fish fish) {

    return false;
  }

  /**
   * moves the shark.
   */
  @Override
  public boolean move() {
    return getPosition().updatePosition(getDirection());
  }

  /**
   * The LaserShark eats a fish. As a result: the fish is placed out of the board and the shark
   * grows according to the size of the fish.
   * 
   * @param fish
   *          the fish the shark eats
   */
  public void eat(Fish fish) {
    fish.setPosition(new Position(-100, -100));
    size += fish.getSize() / 10.0;
  }

  @Override
  public Position getMiddlePoint() {
    Position startPos = this.getPosition();
    Position middlePointPosition = new Position(startPos.getPosX() + (int) this.getSize(),
        startPos.getPosY() + (int) (0.5 * this.getSize()));
    return middlePointPosition;
  }

}
