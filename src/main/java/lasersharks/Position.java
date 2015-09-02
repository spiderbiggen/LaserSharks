package lasersharks;

/**
 * Class Position.
 */
public class Position {
  private int posX;
  private int posY;

  /**
   * Initialize a position object.
   * 
   * @param posX initial x position
   * @param posY initial y position
   */
  public Position(int posX, int posY) {
    this.posX = posX;
    this.posY = posY;
  }

  /**
   * Return the current x position.
   * 
   * @return current x position
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
   * Return the current y position.
   * 
   * @return current y position
   */
  public int getPosY() {
    return posY;
  }

  /**
   * 
   * @param posY the amount to change posX with
   */
  public void setPosY(int posY) {
    this.posY = posY;
  }

  /**
   * 
   * @param deltaY the amount to change posY with
   */
  public void adjustPosY(int deltaY) {
    this.posY += deltaY;
  }

  /**
   * 
   * @param deltaX the amount to change posX with
   * @param deltaY the amount to change posY with
   */
  public void adjustPos(int deltaX, int deltaY) {
    this.adjustPosX(deltaX);
    this.adjustPosY(deltaY);
  }

  /**
   * Updates the position.
   * 
   * @param dir the direction to move in
   * @return false if fish moves off the screen
   */
  public boolean updatePosition(Direction dir) { 
    //TODO Maybe Rename to update checkstyle thinks it's too long
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
