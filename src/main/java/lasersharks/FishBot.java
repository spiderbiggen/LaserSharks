package lasersharks;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import lasersharks.enemies.Enemy1;
import lasersharks.enemies.Enemy10;
import lasersharks.enemies.Enemy12;
import lasersharks.enemies.Enemy2;
import lasersharks.enemies.Enemy4;
import lasersharks.enemies.Enemy5;
import lasersharks.enemies.Enemy6;
import lasersharks.enemies.Enemy7;
import lasersharks.enemies.Enemy8;

/**
 * This class represent the fishes on the screen that are not player controllable.
 * 
 * @author Sytze, Youri
 *
 */
public abstract class FishBot extends Fish {

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
  public FishBot(Position position, float size, double speed, Direction direction) {
    super(position, size, speed, direction);
  }
}