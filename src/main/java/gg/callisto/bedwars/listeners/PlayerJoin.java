package gg.callisto.bedwars.listeners;

import gg.callisto.bedwars.Bedwars;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private Bedwars bedwars;

    public PlayerJoin(Bedwars bedwars){
        this.bedwars = bedwars;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(Bukkit.getOnlinePlayers().size() >= bedwars.getGameManager().getTeamType().getMinPlayers()){
            bedwars.getGameManager().getGameTime().startCountdown();
        }
    }
}
