package lasersharks;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javafx.scene.shape.Rectangle;

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
  private static float FISH_SPAWN_CHANCE = 0.06258f;

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
   * @param fish
   *          the fish to add
   */
  public void addFish(Fish fish) {
    this.fishList.add(fish);
  }

  /**
   * Update all fish positions.
   */
  private void updatePositions() {
    this.fishList.removeAll(this.fishList.stream().filter(v -> !v.move())
        .collect(Collectors.toList()));
  }

  /**
   * 
   * @return List of fish and their positions.
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
    if(!checkForCollisions()) {
      FISH_SPAWN_CHANCE = 0.0f;
    }    
    if (this.rng.nextFloat() <= FISH_SPAWN_CHANCE) {
      this.addFish(FishBot.generateFish());
    }
    return this.getNewFishPositions();
  }

  /**
   * checks if the shark collides with any fish. 
   * If it does it compares the size of the fish and the shark.
   * If the shark is bigger, the shark eats the fish.
   * If the fish is equal or bigger the shark is killed.
   * @return
   */
  private boolean checkForCollisions() {
    LaserShark shark = getShark(fishList);
    if (shark == null) {
      return false;
    }
    Rectangle sharkHitbox = shark.makeHitbox();
    for (int i = 0; i < fishList.size(); i++) {
      Rectangle fishHitbox = fishList.get(i).makeHitbox();
      if (sharkHitbox.intersects(fishHitbox.getLayoutBounds())) {
        // System.out.println("shark collides with fish");
        if (!(fishList.get(i) instanceof LaserShark)) {
          if (fishList.get(i).getSize() < shark.getSize()) {
            // shark eats fish
            shark.eat(fishList.get(i));

          } else {
            // fish eats shark
            shark.kill();
           // System.out.println(shark.getSize() + ":" + fishList.get(i).getSize());
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * returns the first lasershark from a list of fish. if no lasershark is present, it returns null.
   * 
   * @param list
   *          of Fishes on the board
   * @return the LaserShark
   */
  public LaserShark getShark(List<Fish> list) {
    Fish res = null;
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i) instanceof LaserShark) {
        res = list.get(i);
        return (LaserShark) res;
      }
    }
    return (LaserShark) res;
  }
}
