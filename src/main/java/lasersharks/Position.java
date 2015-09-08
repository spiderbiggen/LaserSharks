package lasersharks;

/**
 * Class Position.
 */
public class Position {
  private int posX;
  private int posY;

  /**
   * these are now static values inside Position. These represent the height and width of the
   * screen. TODO: when the gui is added, replace these static variables by getting the resolution
   * of the panel.
   */
  private static int heightPanel = 1080;
  private static int widthPanel = 1920;

  /**
   * 
   * @param posX
   *          initial x position
   * @param posY
   *          initial y position
   */
  public Position(int posX, int posY) {
    this.posX = posX;
    this.posY = posY;
  }

  /**
   * @return the height of the panel.
   */
  public static int getHeightPanel() {
    return heightPanel;
  }

  /**
   * @return the width of the panel.
   */
  public static int getWidthPanel() {
    return widthPanel;
  }

  /**
   * 
   * @return the x value.
   */
  public int getPosX() {
    return posX;
  }

  /**
   * 
   * @param posX
   *          the x value.
   */
  public void setPosX(int posX) {
    this.posX = posX;
  }

  /**
   * 
   * @param deltaX
   *          the amount we want to increase the x value.
   */
  public void adjustPosX(int deltaX) {
    this.posX += deltaX;
  }

  /**
   * 
   * @return the y value
   */
  public int getPosY() {
    return posY;
  }

  /**
   * 
   * @param posY
   *          the amount to change posX with
   */
  public void setPosY(int posY) {
    this.posY = posY;
  }

  /**
   * 
   * @param deltaY
   *          the amount to change posY with
   */
  public void adjustPosY(int deltaY) {
    this.posY += deltaY;
  }

  /**
   * 
   * @param deltaX
   *          the amount to change posX with
   * @param deltaY
   *          the amount to change posY with
   */
  public void adjustPos(int deltaX, int deltaY) {
    this.adjustPosX(deltaX);
    this.adjustPosY(deltaY);
  }

  /**
   * Updates the position.
   * 
   * @param dir
   *          the direction the position should shift to.
   * @return false if fish moves off the screen
   */
  public boolean updatePosition(Direction dir) {
    return updatePosition(dir, 1);
  }

  /**
   * Updates the position with a speed parameter.
   * 
   * @param dir the direction the position should shift to.
   * @param sp the speed in witch the fish moves.
   * @return false if fish moves off the screen.
   */
  public boolean updatePosition(Direction dir, int sp) {
    if (dir != null && !dir.equals(Direction.None)) {
      adjustPos(sp * dir.getDeltaX(), sp * dir.getDeltaY());
    }
    return onScreen();
  }

  /**
   * Returns the distance between the two positions using pythagoras.
   * 
   * @param other
   *          The other position that should be compared to this position.
   * @return the distance between this position and other.
   */
  public float calculateDistance(Position other) {
    return (float) Math.sqrt((Math.pow(other.getPosX() - posX, 2))
        + Math.pow(other.getPosY() - posX, 2));
  }

  /**
   * This boolean checks if the position is on the screen.
   * 
   * @return true if the position is on the screen.
   */
  public final boolean onScreen() {
    return (posX >= 0 && posX <= widthPanel && posY >= 0 && posY <= heightPanel);
  }

  @Override
  public String toString() {
    return "Position [posX=" + posX + ", posY=" + posY + "]";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Position)) {
      return false;
    }

    Position position = (Position) obj;
    if (this.getPosX() != position.getPosX() || this.getPosY() != position.getPosY()) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hash = 17;
    int prime = 31;
    hash = prime * hash + getPosX();
    hash = prime * hash + getPosY();
    return hash;
  }
  
  /**
   * This position represents the middle of the screen.
   * @return a position with coordinates on the middle of the screen
   */
  public static Position middlePosition() {
    return new Position((int) Math.round(widthPanel / 2.0), (int) Math.round(heightPanel / 2.0));
  }
}
