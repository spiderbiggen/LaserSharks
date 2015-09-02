package lasersharks;

/**
 * Class Position.
 */
public class Position {
  private int posX;
  private int posY;

  //these are now static values inside Position. These represent the height and width of the screen.
  static int HeightPanel = 600;
  static int WidthPanel = 800;
  
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
   * @param dir the direction the position should shift to.
   * @return false if fish moves off the screen
   */
  public boolean updatePosition(Direction dir) {
    return updatePosition(dir,1);
  }
  
  /**
   * Updates the position with a speed parameter
   * 
   * @param dir the direction the position should shift to.
   * @return false if fish moves off the screen.
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
    return isOnScreen();
  }
  
  /**
   * Returns the distance between the two positions using pythagoras
   * @param other The other position that should be compared to this position
   * @return the distance between this position and other
   */
  public float calculateDistance(Position other) { 
    float distX = other.getPosX()-posX;
    float distY = other.getPosY()-posX;
    return (float) Math.sqrt((Math.pow(distX,2))+Math.pow(distY, 2));    
  }
  
  /**
   * This boolean checks if the position is on the screen.
   * @return true if the position is on the screen.
   */
  public final boolean isOnScreen() {
    if (posX >= 0 && posX <= WidthPanel && posY >= 0 && posY <= HeightPanel) {
      return true;
    }
    return false;
  }
  
}
