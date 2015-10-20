/**
 * 
 */
package lasersharks.interfaces;

import lasersharks.seaObjects.LaserBullet;
import lasersharks.seaObjects.LaserShark;

/**
 * @author Stefan Breetveld
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
