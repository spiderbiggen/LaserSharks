package lasersharks.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javafx.scene.shape.Rectangle;
import lasersharks.Direction;
import lasersharks.SeaObject;
import lasersharks.LaserShark;
import lasersharks.Logger;
import lasersharks.Position;
import lasersharks.Displayable;
import lasersharks.enemies.FishFactory;
import lasersharks.enemies.FishSpawner;

/**
 * Class for controlling fishdata.
 * 
 * @author SEMGroup27
 *
 */

@SuppressWarnings("restriction")
public class FishController {
  /**
   * Holder for fish data.
   */
  private List<Displayable> fishList;
  private LaserShark shark;
  
  private FishSpawner fishSpawner;

  /**
   * Holder for shark data.
   */
  private static final float START_SIZE = 80.0f;
  private static final double START_SPEED = 670;
  private static final Direction START_DIRECTION = Direction.None;

  /**
   * Spawn-chance for new fishes.
   */
  private static final float FISH_SPAWN_CHANCE_BASE = 1.0f;
  private float fishSpawnChance;

  /**
   * Random Number Generator holder.
   */
  private Random rng;

  /**
   * Constructor.
   */
  public FishController() {
    this.fishList = new LinkedList<Displayable>();
    this.rng = new Random();
    fishSpawnChance = FISH_SPAWN_CHANCE_BASE;
    this.shark = new LaserShark(Position.middlePosition(), START_SIZE, START_SPEED,
        START_DIRECTION);
    fishSpawner = new FishFactory();
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
   *          the Swimmer to add
   */
  public void addFish(Displayable fish) {
    this.fishList.add(fish);
  }

  /**
   * Sets the shark object.
   * 
   * @param shark
   *          the new shark;
   */
  public void setShark(LaserShark shark) {
    this.shark = shark;
  }

  /**
   * Method to get the lasershark.
   * 
   * @return the lasershark
   */
  public LaserShark getShark() {
    return this.shark;
  }

  /**
   * Set the shark to his beginning state.
   */
  public void setBeginShark() {
    this.setShark(
        new LaserShark(Position.middlePosition(), START_SIZE, START_SPEED, START_DIRECTION));
  }

  /**
   * method to return the start size.
   * 
   * @return the start size
   */
  public float getStartSize() {
    return FishController.START_SIZE;
  }

  /**
   * Method to return the FishSpawner.
   * 
   * @return the fishSpawner
   */
  public FishSpawner getFishSpawner() {
    return fishSpawner;
  }
  
  /**
   * Update all fish positions.
   * 
   * @param frametime
   */
  private void updatePositions(double frametime) {
    this.fishList.removeAll(
        this.fishList.stream().filter(v -> !v.move(frametime)).collect(Collectors.toList()));
    if (this.shark != null) {
      this.shark.move(frametime);
    }
  }

  /**
   * 
   * @param frametime
   * @return List of fish and their positions.
   */
  private List<Displayable> getNewFishPositions(double frametime) {
    this.updatePositions(frametime);
    return this.fishList;
  }

  /**
   * Add new fish with chance of SELF::FISHSPAWNCHANCE, then update fish positions and delete
   * offscreen fish.
   * 
   * @param frametime
   *          the time between frames in seconds
   * 
   * @return List<Swimmer> list of fishes at there current position.
   */
  public List<Displayable> getNextCycleInformation(double frametime) {
    checkForCollisions();
    if (this.rng.nextFloat() <= fishSpawnChance / frametime) {
      SeaObject f = fishSpawner.generateFish();
      SeaObject g = fishSpawner.generateAmmo();
      this.addFish(f);
      this.addFish(g);
      Logger.getInstance().write("Fish spawned",
          "Speed: " + f.getSpeed() + ", " + "Size: " + f.getSize() + ", " + "Direction: "
              + f.getDirection() + ", " + "Position: " + f.getPosition());
    }
    return this.getNewFishPositions(frametime);
  }

  /**
   * Clear the fishList when the game ends.
   * 
   */
  public void clearFish() {
    this.fishList.clear();
  }

  /**
   * Method to set the value of the fish spawn chance.
   * 
   * @param chance
   *          The int which is used as the new fish spawn chance.
   */
  public void setFishSpawnChance(int chance) {
    fishSpawnChance = chance;
  }

  /**
   * this function checks if there are any collisions between the shark and other fish. if so, this
   * function checks if the size of the fish is smaller or bigger than the shark. If smaller, the
   * fish is eaten by the shark. If bigger, the game ends.
   */
  private void checkForCollisions() {
    LaserShark shark = this.shark;
    if (shark == null) {
      return;
    }
    Rectangle sharkHitbox = shark.makeHitbox();
    for (int i = 0; i < fishList.size(); i++) {
      Rectangle fishHitbox = fishList.get(i).makeHitbox();
      if (sharkHitbox.intersects(fishHitbox.getLayoutBounds())) {
        if (fishList.get(i).getSize() < shark.getSize()) {
          // shark eats fish
          shark.eat(fishList.get(i));
        } else {
          // fish eats shark
          shark.kill();
        }
      }
    }
  }
  
  /**
   * A laser appears on the screen from the position of the shark and is added to the fishList.
   * @return true if the shark had enough ammo.
   */
  public boolean shootLaser() {
    if (shark.getAmmo() > 0) {
      shark.decreaseAmmo();
      addFish(fishSpawner.createLaser(this.shark));
      return true;
    }
    return false;
  }

}
