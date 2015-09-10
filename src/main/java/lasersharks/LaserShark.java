package lasersharks;

/**
 * LaserShark class.
 *
 */
public class LaserShark extends Fish {

  private static final float ENERGY_DISSERPATION_RATE = 7.5f;
  private static final float HALF_RATE = 0.5F;
  private final String imageResource = "LaserShark.gif";
  private final float widthScale = 2.0f;

  /**
   * Constructor class for FishBot.
   * 
   * @param position
   *          initial position
   * @param size
   *          init size
   * @param speed
   *          init speed
   * @param direction
   *          init direction
   */
  public LaserShark(Position position, float size, int speed, Direction direction) {
    super(position, size, speed, direction);
  }

  /**
   * The LaserShark eats a fish. This kills fish and increases size of the shark.
   * 
   * @param fish
   *          the fish the shark eats
   */
  public void eat(Fish fish) {
    if (fish.isAlive()) {
      this.increaseSize(fish.getSize() / ENERGY_DISSERPATION_RATE);
    }
    fish.kill();

  }

  @Override
  public boolean move() {
    super.move();
    //this will make sure the fish stay within both
    
    this.getPosition().clipPosition(
        (int) (this.getSize() * this.getWidthScale() * HALF_RATE),
        (int) (this.getSize() * HALF_RATE));
    return true;
  }

  @Override
  public String getImageResource() {
    return imageResource;
  }

  @Override
  public double getWidthScale() {
    return widthScale;
  }
}
