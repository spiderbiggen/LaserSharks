package lasersharks;

import lasersharksgui.StandardPane;

/**
 * The default eat behaviour.
 * 
 * @author sytze
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
    if (fish instanceof FishBot) {
      eat((FishBot) fish);
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
  public void eat(FishBot fish) {
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
        shark.increaseAmmo(ammo.getAmount());
        Logger.getInstance().write("Ammo", "Player has picked up some ammo");
        // TODO play a sound.
      }

    }
    ammo.kill();
  }

}
