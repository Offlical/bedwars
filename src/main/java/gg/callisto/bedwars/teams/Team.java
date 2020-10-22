package gg.callisto.bedwars.teams;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.HashSet;
import java.util.UUID;

@Getter
@Setter
public class Team {


    public TeamType type;
    public HashSet<UUID> players;
    private TeamColor teamColor;
    private Location spawnLocation;


    public Team(TeamType type, HashSet<UUID> players,Location spawnLocation) {
        this.type = type;
        this.players = players;
        this.spawnLocation = spawnLocation;
    }



    public void removePlayer(UUID uuid) {
      players.remove(uuid);
    }

    public void addPlayer(UUID uuid) {
      players.add(uuid);
    }
}
