package lasersharks.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import lasersharks.Direction;
import lasersharks.Logger;
import lasersharks.Position;
import lasersharks.interfaces.AmmoSpawner;
import lasersharks.interfaces.Displayable;

import lasersharks.interfaces.LaserSpawner;
import lasersharks.seaobjects.AmmoFactory;
import lasersharks.seaobjects.FishFactory;
import lasersharks.interfaces.FishSpawner;
import lasersharks.seaobjects.LaserFactory;
import lasersharks.seaobjects.LaserShark;
import lasersharks.seaobjects.AbstractSeaObject;


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
  private final List<Displayable> displayableList;
  private LaserShark shark;

  private final FishSpawner enemySpawner;
  private final AmmoSpawner ammoSpawner;
  private final LaserSpawner laserSpawner;

  /**
   * Holder for shark data.
   */
  private static final float START_SIZE = 80.0f;
  private static final double START_SPEED = 450;
  private static final Direction START_DIRECTION = Direction.None;

  /**
   * Spawn-chance for new fishes.
   */
  private static final float FISH_SPAWN_CHANCE_BASE = 1.0f;
  private final float fishSpawnChance;

  /**
   * Numbers for keeping the spawn rate of ammo in check.
   */

  private static final int ONE_HUNDRED = 100;
  private static final int AMMO_SPAWN_LIMITER = 90;

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
    setBeginShark();
    enemySpawner = new FishFactory();
    ammoSpawner = new AmmoFactory();
    laserSpawner = new LaserFactory();
  }

  /**
   * Set the shark to his beginning state.
   */
  private void setBeginShark() {
    this.shark = new LaserShark(
        Position.middlePosition(),
        START_SIZE,
        START_SPEED,
        START_DIRECTION
    );
  }

  /**
   * Set the randomNumberGenerator (for testing purposes).
   * 
   * @param rng
   *          the random number generator to use.
   */
  public void setRng(final Random rng) {
    this.rng = rng;
  }

  /**
   * Add a fish to the controller.
   * 
   * @param displayable
   *          the Swimmer to add
   */
  public void addDisplayable(final Displayable displayable) {
    this.displayableList.add(displayable);
  }

  /**
   * Sets the shark object.
   * 
   * @param shark
   *          the new shark;
   */
  public void setShark(final LaserShark shark) {
    this.shark = shark;
  }

  /**
   * Method to get the laserShark.
   *
   * @return the laserShark
   */
  public LaserShark getShark() {
    return this.shark;
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
   * @param frameTime the time between frames in seconds
   */
  private void updatePositions(final double frameTime) {
    this.displayableList.removeAll(this.displayableList.stream().filter(v -> !v.move(frameTime))
        .collect(Collectors.toList()));
    if (this.shark != null) {
      this.shark.move(frameTime);
    }
  }

  /**
   *
   * @param frameTime the time between frames in seconds
   * @return List of fish and their positions.
   */
  private List<Displayable> getNewFishPositions(final double frameTime) {
    this.updatePositions(frameTime);
    return this.displayableList;
  }

  /**
   * Add new fish with chance of SELF::fishSpawnChance, then update fish positions and delete
   * off screen fish.
   *
   * @param frameTime
   *          the time between frames in seconds
   * 
   * @return List<Swimmer> list of fishes at there current position.
   */
  public List<Displayable> getNextCycleInformation(final double frameTime) {
    checkForCollisions();
    if (this.rng.nextFloat() <= fishSpawnChance / frameTime) {
      final AbstractSeaObject f = enemySpawner.generateFish();
      final AbstractSeaObject g = ammoSpawner.generateAmmo();
      this.addDisplayable(f);

      if (this.rng.nextInt(ONE_HUNDRED) > AMMO_SPAWN_LIMITER) {
        this.addDisplayable(g);
      }

      Logger.getInstance().write(
          "Fish spawned",
          "Speed: " + f.getSpeed() + ", " + "Size: " + f.getSize() + ", " + "Direction: "
              + f.getDirection() + ", " + "Position: " + f.getPosition());
    }
    return this.getNewFishPositions(frameTime);
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
    displayableList.stream().filter(Displayable::collisionActor).forEach(
        v -> displayableList.stream().filter(v::checkForCollision).filter(Displayable::isAlive)
            .forEach(v::collideWith)
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
