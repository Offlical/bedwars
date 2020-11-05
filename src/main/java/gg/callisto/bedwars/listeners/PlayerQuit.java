package gg.callisto.bedwars.listeners;

import gg.callisto.bedwars.Bedwars;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    private Bedwars bedwars;

    public PlayerQuit(Bedwars bedwars){
        this.bedwars = bedwars;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if(Bukkit.getOnlinePlayers().size() < bedwars.getGameManager().getTeamType().getMinPlayers()){
            bedwars.getGameManager().getGameTime().stopCountdown();
        }
    }

}
