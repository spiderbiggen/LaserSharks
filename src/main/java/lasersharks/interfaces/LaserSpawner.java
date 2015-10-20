/**
 * 
 */
package lasersharks.interfaces;

import lasersharks.seaobjects.LaserBullet;
import lasersharks.seaobjects.LaserShark;

/**
 * @author SEMGroup27
 *
 */
public interface LaserSpawner {
  /**
   * This function creates a laser.
   * 
   * @param origin
   *          the shark that shoots a laser.
   * @return a laserBullet object.
   */
  LaserBullet createLaser(LaserShark origin);
}
