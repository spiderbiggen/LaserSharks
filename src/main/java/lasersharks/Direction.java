package lasersharks;

/**
 * Direction Enum.
 */
public enum Direction {
  North(0, 1), 
  NorthEast(1, 1), 
  East(1, 0), 
  SouthEast(1, -1), 
  South(0, -1), 
  SouthWest(-1, -1), 
  West(-1, 0), 
  NorthWest(-1, 1), 
  None(0, 0);
  
  private int deltaX;
  private int deltaY;
  
  /**
   * Constructor for Direction.
   * @param deltaX deltaX of direction
   * @param deltaY deltaY op direction
   */
  Direction(int deltaX, int deltaY) {
    this.deltaX = deltaX;
    this.deltaY = deltaY;
  }
  
  /**
   * Get delta x of direction.
   * @return Delta x
   */
  public int getDeltaX() {
    return this.deltaX;
  }
  
  /**
   * Get delta y of direction.
   * @return Delta y
   */
  public int getDeltaY() {
    return this.deltaY;
  }
}