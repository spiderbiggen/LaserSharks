package lasersharks.enemies;

import java.util.Random;

import lasersharks.FishBot;

public interface FishSpawner {

  public FishBot generateFish(Random rng);
  
  public FishBot generateFish();  
}
