package gg.callisto.bedwars.game;

import gg.callisto.bedwars.Bedwars;
import gg.callisto.bedwars.generators.GeneratorManager;
import gg.callisto.bedwars.teams.TeamColor;
import gg.callisto.bedwars.teams.TeamType;
import gg.callisto.bedwars.teams.TeamsManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;


import java.util.HashMap;
import java.util.HashSet;


@Getter
public class GameManager {


    private TeamsManager teamsManager;
    private GeneratorManager generatorManager;
    private HashMap<Location, TeamColor> spawnLocs;
    private GameTime gameTime;
    private GameType gameType;
    private TeamType teamType;

    public GameManager(Bedwars bedwars) {
        this.teamsManager = bedwars.getTeamsManager();
        this.generatorManager = bedwars.getGeneratorManager();
        this.gameTime = bedwars.getGameTime();
        this.teamType = TeamType.SOLO; // later on add an option to change the mode by command
        gameType = GameType.PRE_GAME;
    }

    public void start() {
        this.genereateSpawnLocs();
        HashSet<TeamColor> usedColors = new HashSet<>(); // to not accidently send a team to an already taken color
        this.gameType = GameType.COUNTDOWN;
        gameTime.start();
        this.markAsStarting();
        /**
         * game start logc
         */
        this.gameType = GameType.INGAME;
    }

    public void genereateSpawnLocs() {
        spawnLocs = new HashMap<>();
        if(teamType == TeamType.FOURS || teamType == TeamType.TRIOS) {
            /**
             *  logic to add only 4 spawn locs and team colors
             */
        }
    }

    public void markAsStarting() {
        // some marking system to mark the server is already in game when the game starts.
    }

    public void stop() {
        gameTime.forceStop();

    }

    public void forceStop() {}


}
