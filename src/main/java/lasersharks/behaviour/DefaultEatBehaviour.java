package lasersharks.behaviour;

import lasersharks.Highscores;
import lasersharks.Logger;
import lasersharks.behaviour.interfaces.EatBehaviour;
import lasersharks.interfaces.Displayable;
import lasersharks.seaobjects.Ammo;
import lasersharks.seaobjects.Enemy;
import lasersharks.seaobjects.LaserShark;
import lasersharksgui.panes.StandardPane;

/**
 * The default eat behaviour.
 * 
 * @author SEMGroup27
 *
 */
public class DefaultEatBehaviour implements EatBehaviour {

  private Displayable swimmer;

  private static final float ENERGY_DISSERPATION_RATE = 7.5f;
  private static final String EAT_FISH_SOUND = "src/main/resources/soundEffect1.wav";

  /**
   * the constructor.
   * 
   * @param swimmer
   *          the swimmer it should apply to.
   */
  public DefaultEatBehaviour(Displayable swimmer) {
    this.swimmer = swimmer;
  }

  /**
   * eats an other fish. The fish grows, the score increases and a sound effect is played.
   * 
   * @param fish
   *          the fish that should be eaten.
   */
  @Override
  public void eat(Displayable fish) {
    if (fish instanceof Enemy) {
      eat((Enemy) fish);
    }
    if (fish instanceof Ammo) {
      eat((Ammo) fish);
    }
  }

  /**
   * eats an other FishBot. The fish grows, the score increases and a sound effect is played.
   * 
   * @param fish
   *          the fish that should be eaten.
   */
  public void eat(Enemy fish) {
    if (fish.isAlive()) {
      Logger.getInstance().write("Fish eaten",
          "Old sharksize: " + swimmer.getSize() + "," + "Fish size: " + fish.getSize() + ", "
              + "New sharksize: "
              + (swimmer.getSize() + (fish.getSize() / ENERGY_DISSERPATION_RATE)));
      swimmer.increaseSize(fish.getSize() / ENERGY_DISSERPATION_RATE);
      StandardPane.playSoundEffect(EAT_FISH_SOUND);
    }
    Highscores.getInstance().increaseScore(fish);
    fish.kill();
  }

  /**
   * Eats an ammo pack. The shark gets ammo.
   * 
   * @param ammo
   *          the ammo that was picked up
   */
  public void eat(Ammo ammo) {
    if (swimmer instanceof LaserShark && ammo.isAlive()) {
      LaserShark shark = (LaserShark) swimmer;
      if (shark.getAmmo() < shark.getMaxAmmo()) {
        shark.increaseAmmo(ammo.getPickupAmount());
        Logger.getInstance().write("Ammo", "Player has picked up some ammo");
        // TODO play a sound.
      }

    }
    ammo.kill();
  }

}
