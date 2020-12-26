package dev.offlical.bedwars.generators;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter
public class GeneratorList  {

   private List<Location> spawnLocations;
   private int currentSpawnRate;
   private List<Integer> spawnRates;
   private List<Integer> upgradeTimes;
   private GeneratorType type;
   private int currentTier;
   private int baseGenIndi;

   public GeneratorList(GeneratorType type, List<Location> spawnLocations, List<Integer> spawnRates, List<Integer> upgradeTimes) {
      this.spawnLocations = spawnLocations;
      this.spawnRates = spawnRates;
      this.type = type;
      this.upgradeTimes = upgradeTimes;
      this.currentSpawnRate = spawnRates.get(0);
      currentTier = 1;
      this.baseGenIndi = 0;
   }

   public Integer getNextUpgradeTime() {
      if(upgradeTimes.isEmpty() || spawnRates.isEmpty()) return 0;
      return upgradeTimes.get(currentTier - 1);
   }

   public void upgrade() {
      if(upgradeTimes.isEmpty() || spawnRates.isEmpty()) return;
      System.out.println("upgrading from " + currentTier + " to " + (currentTier+1));
      Bukkit.getServer().broadcastMessage(type.getName() + " Generators has upgraded from tier " + currentTier + " to tier " + (currentTier+1) + ".");
      currentTier++;
      if(currentTier <= spawnRates.size()) {
         this.currentSpawnRate = spawnRates.get((currentTier - 1));
      }
   }

   public void spawnItem() {
      for(Location spawnLocation : spawnLocations.toArray(new Location[spawnLocations.size()])) {
         Item item = spawnLocation.getWorld().spawn(spawnLocation, Item.class);
         item.setItemStack(new ItemStack(getItem(), 1));
      }
      if(type == GeneratorType.BASE_GEN)
         baseGenIndi++;
   }

   public Material getItem() {
      if(type.getList().size() == 1) return type.getList().get(0);
      else if(type.equals(GeneratorType.BASE_GEN)){
         /**
          *  base gen logic
          */
         if(baseGenIndi == 4) {
            baseGenIndi = 0;
            return Material.GOLD_INGOT;
         }
         else {
            baseGenIndi++;
            return Material.IRON_INGOT;
         }
      }
      return null;
   }
}
