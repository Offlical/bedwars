package gg.callisto.bedwars.teams;

import gg.callisto.bedwars.teams.Team;
import lombok.Getter;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class TeamUpgrades {

    private HashMap<String,Integer> upgrades;
    private Team team;


    public TeamUpgrades(Team team) {
        this.upgrades = new HashMap<>();
        this.team = team;
    }

    public void applyUpgrades() {
        for(UUID uuid : team.getPlayers())
        {

        }
    }

    public void addUpgrade(String upgrade, int level) {
        this.upgrades.put(upgrade, level);
        this.applyUpgrades();
    }

    public void removeUpgrade(String upgrade) {
        this.upgrades.remove(upgrade);
        this.applyUpgrades();
    }

    public void upgradeTeamUpgrade(String upgrade) {
        this.upgrades.put(upgrade,(upgrades.get(upgrade) + 1));
        this.applyUpgrades();
    }

    public int getTeamUpgradeTier(String upgrade) {
        return upgrades.get(upgrade);
    }

}
