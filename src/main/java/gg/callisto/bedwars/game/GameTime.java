package gg.callisto.bedwars.game;

import gg.callisto.bedwars.Bedwars;
import gg.callisto.bedwars.generators.Generator;
import gg.callisto.bedwars.generators.GeneratorManager;
import gg.callisto.bedwars.util.MessageType;
import gg.callisto.bedwars.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
    private BukkitRunnable countdownRunnable;
    private Integer countdown;

    public GameTime(Bedwars bedwars) {
        gameManager = bedwars.getGameManager();
        generatorManager = bedwars.getGeneratorManager();
        this.bedwars = bedwars;
    }

    public void start(){
        timeRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                secondsInGame++;
                for(Generator generator: generatorManager.getGens()) {
                    if(secondsInGame >= generator.getNextUpgradeTime()) {
                        generator.upgrade();
                    }
                    if(secondsInGame % generator.getCurrentSpawnRate() == 0 && secondsInGame > generator.getCurrentSpawnRate()) {
                        // when secondsInGame % generator.getCurrentSpawnRate() is 0,
                        // we know it's time to spawn an item because if it's any higher than 0, it means we already spawned an item, and we need to wait to wait to spawn again:
                        // for example: if it takes a generator 30 seconds to spawn an item, at 30 seconds it would spawn, (it equals to 0), and then it would be 31 seconds into the game,
                        // and because 31 is not dividable by 30, we know 30 seconds haven't passed yet, when there's nothing left, aka it can be divided by 30, we know the "cooldown" to spawn
                        // an item ended.
                        generator.spawnItem();
                    }
                }
                /**
                 *  add some more logic here for bed break, dragons or other events, etc.
                 */
            }
        };
        timeRunnable.runTaskTimerAsynchronously(bedwars,20,0);

    }

    public void startCountdown(){
        countdown = 21;
        this.countdownRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                countdown--;
                if(countdown == 0){
                    countdownRunnable.cancel();
                    bedwars.getGameManager().start();
                    countdownRunnable = null;
                } else {
                    boolean check = countdown == 20 || countdown <= 10;
                    if(check){
                        MessageUtil.broadcastMessage(MessageType.INFO, "%" + countdown + " seconds remaining...");
                    }
                }
            }
        };
        this.countdownRunnable.runTaskTimerAsynchronously(bedwars,20,0);
    }

    public void stopCountdown(){
        this.countdownRunnable.cancel();
        this.countdownRunnable = null;
        bedwars.getGameManager().setGameType(GameType.PRE_GAME);
    }

    /**
     * isn't needed yet
     */
    public void forceStop() {
        timeRunnable.cancel();
    }
}
