package gg.callisto.bedwars.game;

import gg.callisto.bedwars.Bedwars;
import gg.callisto.bedwars.generators.Generator;
import gg.callisto.bedwars.generators.GeneratorList;
import gg.callisto.bedwars.generators.GeneratorManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTime {

    /**
     *  Manager for Ingame time, after the game started.
     */

    private GameManager gameManager;
    private GeneratorManager generatorManager;
    private BukkitRunnable timeRunnable;
    private Bedwars bedwars;
    private Long secondsInGame;
    private boolean started;


    public GameTime(Bedwars bedwars) {
        secondsInGame = 0L;
        started = false;
        gameManager = bedwars.getGameManager();
        generatorManager = bedwars.getGeneratorManager();
        this.bedwars = bedwars;
    }

    public void start(){
        if(started) return;
        timeRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                started = true;
                secondsInGame++;
                System.out.println("ingame time: " + secondsInGame);
                for(Generator generator: generatorManager.getGens()) {
                    if(secondsInGame >= generator.getNextUpgradeTime() && (generator.getCurrentTier() + 1) <= generator.getUpgradeTimes().size()) {
                        generator.upgrade();
                    }
                    if(secondsInGame % generator.getCurrentSpawnRate() == 0) {
                        // when secondsInGame % generator.getCurrentSpawnRate() is 0,
                        // we know it's time to spawn an item because if it's any higher than 0, it means we already spawned an item, and we need to wait to wait to spawn again:
                        // for example: if it takes a generator 30 seconds to spawn an item, at 30 seconds it would spawn, (it equals to 0), and then it would be 31 seconds into the game,
                        // and because 31 is not dividable by 30, we know 30 seconds haven't passed yet, when there's nothing left, aka it can be divided by 30, we know the "cooldown" to spawn
                        // an item ended.
                        generator.spawnItem();
                    }
                }
                for(GeneratorList list: generatorManager.getGenLists()) {
                    if(secondsInGame >= list.getNextUpgradeTime() && (list.getCurrentTier() + 1) <= list.getUpgradeTimes().size()) {
                        list.upgrade();
                    }
                    if(secondsInGame % list.getCurrentSpawnRate() == 0) {
                        list.spawnItem();
                    }
                }
                /**
                 *  add some more logic here for bed break, dragons or other events, etc.
                 */
            }
        };
        timeRunnable.runTaskTimer(bedwars,0,20);

    }


    /**
     * isn't needed yet
     */
    public void forceStop() {
        timeRunnable.cancel();
    }
}
