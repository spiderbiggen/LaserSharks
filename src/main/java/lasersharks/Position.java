package lasersharks;

/**
 * Class Position.
 */
public class Position {
  private static final int SECOND_HASH_PRIME = 31;
  private static final int HASH_PRIME = 17;
  private double posX;
  private double posY;

  /**
   * these are now static values inside Position. These represent the height and width of the
   * screen. TODO: when the gui is added, replace these static variables by getting the resolution
   * of the panel.
   */
  private static final int START_HEIGHT = 1080;
  private static final int START_WIDTH = 1920;

  private static int height = START_HEIGHT;
  private static int width = START_WIDTH;

  /**
   * 
   * @param posX
   *          initial x position
   * @param posY
   *          initial y position
   */
  public Position(double posX, double posY) {
    this.posX = posX;
    this.posY = posY;
  }

  /**
   * @return the height of the panel.
   */
  public static double getHeightPanel() {
    return height;
  }

  /**
   * @return the width of the panel.
   */
  public static double getWidthPanel() {
    return width;
  }

  /**
   * sets the height of the panel.
   * 
   * @param newHeight
   *          the new height of the panel to set.
   */
  public static void setHeightPanel(int newHeight) {
    height = newHeight;
  }

  /**
   * sets the width of the panel.
   * 
   * @param newWidth
   *          the new width of the panel to set.
   */
  public static void setWidthPanel(int newWidth) {
    width = newWidth;
  }

  /**
   * 
   * @return the x value.
   */
  public double getPosX() {
    return posX;
  }

  /**
   * 
   * @param posX
   *          the x value.
   */
  public void setPosX(double posX) {
    this.posX = posX;
  }

  /**
   * 
   * @param deltaX
   *          the amount we want to increase the x value.
   */
  public void adjustPosX(double deltaX) {
    this.posX += deltaX;
  }

  /**
   * 
   * @return the y value
   */
  public double getPosY() {
    return posY;
  }

  /**
   * 
   * @param posY
   *          the amount to change posX with
   */
  public void setPosY(double posY) {
    this.posY = posY;
  }

  /**
   * 
   * @param deltaY
   *          the amount to change posY with
   */
  public void adjustPosY(double deltaY) {
    this.posY += deltaY;
  }

  /**
   * 
   * @param deltaX
   *          the amount to change posX with
   * @param deltaY
   *          the amount to change posY with
   */
  public void adjustPos(double deltaX, double deltaY) {
    this.adjustPosX(deltaX);
    this.adjustPosY(deltaY);
  }

  /**
   * Updates the position with a speed parameter.
   * 
   * @param dir
   *          the direction the position should shift to.
   * @param sp
   *          the speed in witch the fish moves.
   * @param margin
   *          size of object to make sure it's entirely offscreen.
   * @return false if fish moves off the screen.
   */
  public boolean updatePosition(Direction dir, double sp, double margin) {
    if (dir != null && !dir.equals(Direction.None)) {
      adjustPos(sp * dir.getDeltaX(), sp * dir.getDeltaY());
    }
    return onScreen(margin);
  }

  /**
   * Returns the distance between the two positions using pythagoras.
   * 
   * @param other
   *          The other position that should be compared to this position.
   * @return the distance between this position and other.
   */
  public float calculateDistance(Position other) {
    return (float) Math
        .sqrt((Math.pow(other.getPosX() - posX, 2)) + Math.pow(other.getPosY() - posX, 2));
  }

  /**
   * 
   * This boolean checks if the position is on the screen.
   * 
   * @return true if the position is on the screen.
   * @param xMargin
   *          max offset margin
   */
  public final boolean onScreen(double xMargin) {
    return (posX + xMargin >= 0 && posX - xMargin <= width && posY >= 0 && posY <= height);
  }

  /**
   * Will make sure the position is within the the given boundaries.
   * 
   * @param xMargin
   *          how far something can go outside of the screen on the x axis
   * @param yMargin
   *          how far something can go outside of the screen on the y axis
   */
  public void clipPosition(double xMargin, double yMargin) {
    this.posX = Math.min(Math.max(0 - xMargin, this.posX), width - xMargin);
    this.posY = Math.min(Math.max(0 - yMargin, this.posY), height - yMargin);
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
    int hash = (int) (SECOND_HASH_PRIME * HASH_PRIME + getPosX());
    hash = (int) (SECOND_HASH_PRIME * hash + getPosY());
    return hash;
  }

  /**
   * This position represents the middle of the screen.
   * 
   * @return a position with coordinates on the middle of the screen
   */
  public static Position middlePosition() {
    return new Position(Math.round(width / 2.0), Math.round(height / 2.0));
  }
  
  /**
   * This position represents the middle of the screen.
   * 
   * @return a position with coordinates on the middle of the screen
   */
  public static Position upperCornerPosition() {
    return new Position(Math.round(width * 0.9), Math.round(height * 0.035));
  }
  

}
