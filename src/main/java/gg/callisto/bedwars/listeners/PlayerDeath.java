package gg.callisto.bedwars.listeners;

import gg.callisto.bedwars.util.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {


    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = player.getKiller();
        event.setDeathMessage(MessageUtil.prefix + " " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " was killed by " + ChatColor.WHITE + killer.getName());
    }
}
