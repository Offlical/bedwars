package gg.callisto.bedwars.listeners;

import gg.callisto.bedwars.Bedwars;
import gg.callisto.bedwars.util.MessageType;
import gg.callisto.bedwars.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EntityAttackEntity implements Listener {

    private Bedwars bedwars;
    public static Integer respawnCountdown = 6;
    private Integer countdown = 0;

    public EntityAttackEntity(Bedwars bedwars){
        this.bedwars = bedwars;
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            if(((Player) event.getEntity()).getHealth() - event.getFinalDamage() <= 0){
                ((Player) event.getEntity()).setHealth(20);
                ((Player) event.getEntity()).setGameMode(GameMode.SPECTATOR);
                Player player = (Player) event.getEntity();
                if(hasBed(player)) {
                    countdown = respawnCountdown;
                    BukkitRunnable respawnCountdownRunnable = new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (countdown == 1) {
                                player.teleport(player.getWorld().getSpawnLocation());
                                // TODO: when we create the maps this will change
                                player.setGameMode(GameMode.SURVIVAL);
                                this.cancel();
                            }
                            countdown--;
                            MessageUtil.sendMessage(MessageType.INFO, player, "Respawning in %" + countdown);
                        }
                    };
                    respawnCountdownRunnable.runTaskTimerAsynchronously(bedwars, 20, 0);
                }
            }
        }
    }

    private Boolean hasBed(Player player){
        // TODO: check to see if bed exists
        return true;
    }

}
