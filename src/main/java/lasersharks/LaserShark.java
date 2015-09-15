package lasersharks;

/**
 * LaserShark class.
 *
 */
public class LaserShark extends Fish {

  private static final float ENERGY_DISSERPATION_RATE = 7.5f;

  private final String imageResource = "shark.png";
  private final float widthScale = 1.5f;

  private int score;
  private static final float HALF_RATE = 0.5F;

  /**
   * Constructor class for FishBot.
   * 
   * @param position
   *          initial position
   * @param size
   *          init size
   * @param startSpeed
   *          init speed
   * @param direction
   *          init direction
   */
  public LaserShark(Position position, float size, double startSpeed, Direction direction, int score) {
    super(position, size, startSpeed, direction);
    score = this.score;
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
    double scorePlus = fish.getSize() * HALF_RATE + 20;
    this.score = this.score + (int) scorePlus;
  }

  @Override
  public boolean move(double frametime) {
    super.move(frametime);
    // this will make sure the fish stay within both

    this.getPosition().clipPosition((this.getSize() * this.getWidthScale() * HALF_RATE),
        (this.getSize() * HALF_RATE));
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

  /**
   * Returns the current score the player has.
   * 
   * @return current score
   */
  public int getScore() {
    return score;
  }

  /**
   * Set the current score the player has.
   * 
   */
  public void setScore(int score) {
    this.score = score;
  }
}
