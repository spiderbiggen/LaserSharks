package lasersharks.controllers;

import lasersharks.Direction;
import lasersharks.Logger;
import lasersharks.Position;
import lasersharks.interfaces.AmmoSpawner;
import lasersharks.interfaces.Displayable;
import lasersharks.interfaces.FishSpawner;
import lasersharks.interfaces.LaserSpawner;
import lasersharks.seaobjects.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Class for controlling fish data.
 * 
 * @author SEMGroup27
 *
 */

@SuppressWarnings("restriction")
public class FishController {
  /**
   * Holder for fish data.
   */
  private List<Displayable> displayableList;
  private LaserShark shark;

  private FishSpawner enemySpawner;
  private AmmoSpawner ammoSpawner;
  private LaserSpawner laserSpawner;

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
   * Numbers for keeping the spawn rate of ammo in check.
   */

  private static final int ONE_HUNDRED = 100;
  private static final int AMMO_SPAWN_LIMITER = 88;

  /**
   * Random Number Generator holder.
   */
  private Random rng;

  /**
   * Constructor.
   */
  public FishController() {
    this.displayableList = new LinkedList<>();
    this.rng = new Random();
    fishSpawnChance = FISH_SPAWN_CHANCE_BASE;
    this.shark = new LaserShark(Position.middlePosition(), START_SIZE, START_SPEED, START_DIRECTION);
    enemySpawner = new FishFactory();
    ammoSpawner = new AmmoFactory();
    laserSpawner = new LaserFactory();
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
   * @param displayable
   *          the Swimmer to add
   */
  public void addDisplayable(Displayable displayable) {
    this.displayableList.add(displayable);
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
    this.setShark(new LaserShark(Position.middlePosition(), START_SIZE, START_SPEED,
        START_DIRECTION));
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
    return enemySpawner;
  }

  /**
   * Update all fish positions.
   * 
   * @param frametime scalar based time elapsed since the last update
   */
  private void updatePositions(double frametime) {
    this.displayableList.removeAll(this.displayableList.stream().filter(v -> !v.move(frametime))
        .collect(Collectors.toList()));
    if (this.shark != null) {
      this.shark.move(frametime);
    }
  }

  /**
   * 
   * @param frametime scalar based time elapsed since the last update
   * @return List of fish and their positions.
   */
  private List<Displayable> getNewFishPositions(double frametime) {
    this.updatePositions(frametime);
    return this.displayableList;
  }

  /**
   * Add new fish with chance of SELF::FISHSPAWNCHANCE, then update fish positions and delete
   * offscreen fish.
   * 
   * @param frametime
   *           scalar based time elapsed since the last update
   * 
   * @return List<Swimmer> list of fishes at there current position.
   */
  public List<Displayable> getNextCycleInformation(double frametime) {
    checkForCollisions();
    if (this.rng.nextFloat() <= fishSpawnChance / frametime) {
      SeaObject f = enemySpawner.generateFish();
      SeaObject g = ammoSpawner.generateAmmo();
      this.addDisplayable(f);

      if (this.rng.nextInt(ONE_HUNDRED - 0) > AMMO_SPAWN_LIMITER) {
        this.addDisplayable(g);
      }

      Logger.getInstance().write(
          "Fish spawned",
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
    this.displayableList.clear();
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
   * fish is eaten by the shark. If bigger, the game ends. It also checks if there is a collision
   * between a fish and a laser, if so, the fish will decrease in size and the laser will be removed
   * from the screen.
   *
   */
  private void checkForCollisions() {
    displayableList.add(0, this.shark);
    displayableList.stream()
        .filter(Displayable::collisionActor)
        .forEach(v -> 
        displayableList.stream()
            .filter(v::checkForCollision)
            .filter(Displayable::isAlive)
            .forEach(v::collideWith
        )
    );
    displayableList.remove(0);
  }

  /**
   * A laser appears on the screen from the position of the shark and is added to the fishList.
   * 
   * @return true if the shark had enough ammo.
   */
  public boolean shootLaser() {
    if (shark.getAmmo() > 0) {
      shark.decreaseAmmo();
      addDisplayable(laserSpawner.createLaser(this.shark));
      return true;
    }
    return false;
  }
}
