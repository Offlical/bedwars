package gg.callisto.bedwars.generators;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter
public class Generator {

    private GeneratorType type;
    private Location spawnLocation;
    /**
     * a spawn rate for each upgrade that happens just like in hypixel bedwars - diamond 1, diamond 2, etc.
     * spawnRates by every ___ seconds a diamond spawns, etc.
     */
    private List<Double> spawnRates;
    /**
     * Time(s) since the game started for each upgrade, in seconds.
     */
    private List<Integer> upgradeTimes;
    private double currentSpawnRate;
    private int currentTier;


    public Generator(GeneratorType type, Location spawnLocation, List<Double> spawnRates, List<Integer> upgradeTimes) {
        this.type = type;
        this.spawnLocation = spawnLocation;
        this.spawnRates = spawnRates;
        this.upgradeTimes = upgradeTimes;
        currentSpawnRate = spawnRates.get(0);
        currentTier = 1;
    }

    public Integer getNextUpgradeTime() { return upgradeTimes.get(currentTier - 1); }

    public void upgrade() {
        currentTier++;
        if(currentTier > spawnRates.size())
            this.currentSpawnRate = spawnRates.get((currentTier - 1));
    }

    public void spawnItem() {
      Item item = (Item) spawnLocation.getWorld().spawn(spawnLocation,Item.class);
      item.setItemStack(new ItemStack(getItem(),1));
    }

    public Material getItem() {
        if(type.getList().size() == 1) return type.getList().get(0);
        else {

            /**
             *  base gen logic
             */

        }
        return null;
    }

}
