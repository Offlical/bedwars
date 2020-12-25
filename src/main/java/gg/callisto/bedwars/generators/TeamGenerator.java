package gg.callisto.bedwars.generators;

import gg.callisto.bedwars.teams.Team;
import gg.callisto.bedwars.teams.TeamColor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.List;

@Getter
@Setter
public class TeamGenerator extends Generator {

    private TeamColor color;
    private Team team;

    public TeamGenerator(GeneratorType type, Location spawnLocation, List<Double> spawnRates, List<Integer> upgradeTimes, TeamColor color) {
        super(type, spawnLocation, spawnRates, upgradeTimes);
        this.color = color;
    }

}
