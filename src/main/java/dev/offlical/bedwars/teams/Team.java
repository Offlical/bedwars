package dev.offlical.bedwars.teams;

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
    private TeamUpgrades teamUpgrades;
    private Location spawnLocation;
    private Location bedLocation;


    public Team(TeamType type, HashSet<UUID> players,Location spawnLocation, Location bedLocation) {
        this.type = type;
        this.players = players;
        this.spawnLocation = spawnLocation;
        this.teamUpgrades = new TeamUpgrades(this);
        this.bedLocation = bedLocation;
    }

    public void removePlayer(UUID uuid) {
      players.remove(uuid);
    }

    public void addPlayer(UUID uuid) {
      players.add(uuid);
    }
}
