package lasersharks;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Class for controlling fishdata.
 * 
 * @author Youri
 *
 */
public class FishController {
  /**
   * Holder for fishdata.
   */
  private List<Fish> fishList;

  /**
   * Spawnchance for new fishes.
   */
  private static final float FISH_SPAWN_CHANCE = 0.06258f;

  /**
   * Random Number Generator holder.
   */
  private Random rng;

  /**
   * Constructor.
   */
  public FishController() {
    this.fishList = new LinkedList<Fish>();
    this.rng = new Random();
  }

  /**
   * Set the randomNumberGenerator (for testing purposes).
   * 
   * @param rng
   *          the random number generator to use.
   */
  public void setRng(Random rng) {
    this.rng = rng;
  }

  /**
   * Add a fish to the controller.
   * 
   * @param fish the fish to add
   */
  public void addFish(Fish fish) {
    this.fishList.add(fish);
  }

  /**
   * Update all fish positions.
   */
  private void updatePositions() {
    this.fishList.removeAll(
        this.fishList.stream()
          .filter(v -> !v.move())
        .collect(Collectors.toList())
    );
  }

  /**
   * 
   * @return List of fish and there positions.
   */
  private List<Fish> getNewFishPositions() {
    this.updatePositions();
    return this.fishList;
  }

  /**
   * Add new fish with chance of SELF::FISHSPAWNCHANCE, then update fish positions and delete
   * offscreen fish.
   * 
   * @return List<Fish> list of fishes at there current position.
   */
  public List<Fish> getNextCycleInformation() {
    if (this.rng.nextFloat() <= FISH_SPAWN_CHANCE) {
      System.out.println("Fishadded");
      this.addFish(FishBot.generateFish());
    }
    return this.getNewFishPositions();
  }
  
  
}
