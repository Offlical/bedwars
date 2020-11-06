package gg.callisto.bedwars.generators;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@Getter
public class Generator {

    private GeneratorType type;
    private Location spawnLocation;
    /**
     * a spawn rate for each upgrade that happens just like in hypixel bedwars - diamond 1, diamond 2, etc.
     * spawnRates by every ___ seconds a diamond spawns, etc.
     */
    private List<Integer> spawnRates;
    /**
     * Time(s) since the game started for each upgrade, in seconds.
     */
    private List<Integer> upgradeTimes;
    private int currentSpawnRate;
    private int currentTier;


    public Generator(GeneratorType type, Location spawnLocation, List<Integer> spawnRates, List<Integer> upgradeTimes) {
        this.type = type;
        this.spawnLocation = spawnLocation;
        this.spawnRates = spawnRates;
        this.upgradeTimes = upgradeTimes;
        currentSpawnRate = spawnRates.get(0);
        currentTier = 1;
    }
    public Generator(GeneratorType type, Location spawnLocation, int currentSpawnRate) {
        this.type = type;
        this.spawnLocation = spawnLocation;
        this.spawnRates = new ArrayList<Integer>();
        this.upgradeTimes = new ArrayList<Integer>();
        this.currentSpawnRate = currentSpawnRate;
        currentTier = 1;
    }


    public Integer getNextUpgradeTime() {
        if(upgradeTimes.isEmpty() || spawnRates.isEmpty()) return 0;
        return upgradeTimes.get(currentTier - 1);
    }

    public void upgrade() {
        if(upgradeTimes.isEmpty() || spawnRates.isEmpty()) return;
        System.out.println("upgrading from " + currentTier + " to " + (currentTier+1));
        currentTier++;
        if(currentTier <= spawnRates.size()) {
            this.currentSpawnRate = spawnRates.get((currentTier - 1));
        }
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
