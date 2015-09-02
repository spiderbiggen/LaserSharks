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
    return updatePosition(dir,1);
  }
  
  /**
   * Updates the position with a speed parameter
   * 
   * @param dir
   * @return false if fish moves off the screen
   */
  public boolean updatePosition(Direction dir, int sp) {
    int negSp = sp*-1;
    switch (dir) {
      case North:
        adjustPos(sp, 0);
        break;
      case NorthEast:
        adjustPos(sp, sp);
        break;
      case East:
        adjustPos(0, sp);
        break;
      case SouthEast:
        adjustPos(negSp, sp);
        break;
      case South:
        adjustPos(negSp, 0);
        break;
      case SoutWest:
        adjustPos(negSp, negSp);
        break;
      case West:
        adjustPos(0, negSp);
        break;
      case NorthWest:
        adjustPos(sp, negSp);
        break;
      default:
        break;
    }

    // TODO check if fish is outside of the view and then return false
    return true;
  }
  
}
