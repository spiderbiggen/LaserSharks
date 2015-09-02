package lasersharks;

/**
 * Class Position.
 */
public class Position {
  private int posX;
  private int posY;

  /**
   * 
   * @param posX
   * @param posY
   */
  public Position(int posX, int posY) {
    this.posX = posX;
    this.posY = posY;
  }

  /**
   * 
   * @return
   */
  public int getPosX() {
    return posX;
  }

  /**
   * 
   * @param posX
   */
  public void setPosX(int posX) {
    this.posX = posX;
  }

  /**
   * 
   * @param deltaX
   */
  public void adjustPosX(int deltaX) {
    this.posX += deltaX;
  }

  /**
   * 
   * @return
   */
  public int getPosY() {
    return posY;
  }

  /**
   * 
   * @param posY
   */
  public void setPosY(int posY) {
    this.posY = posY;
  }

  /**
   * 
   * @param deltaY
   */
  public void adjustPosY(int deltaY) {
    this.posY += deltaY;
  }

  /**
   * 
   * @param deltaX
   * @param deltaY
   */
  public void adjustPos(int deltaX, int deltaY) {
    this.adjustPosX(deltaX);
    this.adjustPosY(deltaY);
  }

  /**
   * Updates the position.
   * 
   * @param dir
   * @return false if fish moves off the screen
   */
  public boolean updatePosition(Direction dir) {
    switch (dir) {
      case North:
        adjustPos(1, 0);
        break;
      case NorthEast:
        adjustPos(1, 1);
        break;
      case East:
        adjustPos(0, 1);
        break;
      case SouthEast:
        adjustPos(-1, 1);
        break;
      case South:
        adjustPos(-1, 0);
        break;
      case SoutWest:
        adjustPos(-1, -1);
        break;
      case West:
        adjustPos(0, -1);
        break;
      case NorthWest:
        adjustPos(1, -1);
        break;
      default:
        break;
    }

    // TODO check if fish is outside of the view and then return false
    return true;
  }
}
