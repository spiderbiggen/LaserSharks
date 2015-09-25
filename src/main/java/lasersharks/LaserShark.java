package lasersharks;

import lasersharksgui.MainGui;
import lasersharksgui.StandardPane;


/**
 * LaserShark class.
 *
 */
public class LaserShark extends Fish implements DirectionCallback {

  private static final float ENERGY_DISSERPATION_RATE = 7.5f;
  private static final String EAT_FISH_SOUND = "src/main/resources/soundEffect1.wav";

  private final String imageResource = "shark.png";
  private final float widthScale = 1.5f;
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
  public LaserShark(Position position, float size, double startSpeed, Direction direction) {
    super(position, size, startSpeed, direction);
  }

  /**
   * The LaserShark eats a fish. This kills fish and increases size of the shark.
   * 
   * @param fish
   *          the fish the shark eats
   */
  public void eat(Swimmer fish) {
    if (fish.isAlive()) {
      Logger.getInstance().write(
          "Fish eaten", 
          "Old sharksize: " + this.getSize() + ","
          + "Fish size: " + fish.getSize() + ", "
          + "New sharksize: " 
          + (this.getSize() + (fish.getSize() / ENERGY_DISSERPATION_RATE))
      );
      this.increaseSize(fish.getSize() / ENERGY_DISSERPATION_RATE);
      StandardPane.playSoundEffect(EAT_FISH_SOUND);
    }
    Highscores.increaseScore(fish);
    fish.kill();
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
  
  @Override
  public void setDirection(Direction dir) {
    if (this.isAlive() && this.getDirection() != dir) {
      Logger.getInstance().write(
          "Change of direction", 
          "From: " + this.getDirection() + ", to: " + dir
      );
    }
    super.setDirection(dir);
  }
  
  @Override
  public void kill() {
    Logger.getInstance().write("Loss", "Player has colided with a bigger fish");
    super.kill();
  }

  @Override
  public void putDirection(Direction dir) {
    this.setDirection(dir);
  }
}
