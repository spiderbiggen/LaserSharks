package lasersharks;

import lasersharksgui.StandardPane;

public class DefaultEatBehaviour implements EatBehaviour{

  Swimmer swimmer;

  private static final float ENERGY_DISSERPATION_RATE = 7.5f;
  private static final String EAT_FISH_SOUND = "src/main/resources/soundEffect1.wav";
  
  
  public DefaultEatBehaviour(Swimmer swimmer) {
    this.swimmer = swimmer;
  }
  
  @Override
  public void eat(Swimmer fish) {
    if (fish.isAlive()) {
      Logger.getInstance().write("Fish eaten",
          "Old sharksize: " + swimmer.getSize() + "," + "Fish size: " + fish.getSize() + ", "
              + "New sharksize: " + (swimmer.getSize() + (fish.getSize() / ENERGY_DISSERPATION_RATE)));
      swimmer.increaseSize(fish.getSize() / ENERGY_DISSERPATION_RATE);
      StandardPane.playSoundEffect(EAT_FISH_SOUND);
    }
    Highscores.getInstance().increaseScore(fish);
    fish.kill();
  }
  
}
