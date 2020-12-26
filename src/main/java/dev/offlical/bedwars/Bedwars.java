package dev.offlical.bedwars;

import dev.offlical.bedwars.cmds.TestCmds;
import dev.offlical.bedwars.game.GameManager;
import dev.offlical.bedwars.game.GameTime;
import dev.offlical.bedwars.generators.GeneratorManager;
import dev.offlical.bedwars.listeners.*;
import dev.offlical.bedwars.teams.TeamsManager;
import dev.offlical.bedwars.util.NBTManager;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

@Getter
public class Bedwars extends JavaPlugin {

    private GameManager gameManager;
    private TeamsManager teamsManager;
    private GeneratorManager generatorManager;
    private NBTManager nbtManager;
    private GameTime gameTime;
    private HashSet<Location> placedBlocks;


    @Override
    public void onEnable() {
        setupManagers();
        setupListeners();
    }

    public void setupManagers() {
      this.teamsManager = new TeamsManager(this);
      this.gameManager = new GameManager(this);
      this.generatorManager = new GeneratorManager(this);
      this.gameTime = new GameTime(this);
      this.nbtManager = new NBTManager(this);
    }

    public void setupListeners() {
        placedBlocks = new HashSet<>();
        this.getServer().getPluginManager().registerEvents(new PlayerInteract(),this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(),this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuit(),this);
        this.getServer().getPluginManager().registerEvents(new PlayerDeath(),this);
        this.getServer().getPluginManager().registerEvents(new EntityInteract(),this);
        this.getServer().getPluginManager().registerEvents(new EntityAttackEntity(),this);
        this.getServer().getPluginManager().registerEvents(new BlockBreak(this),this);
        this.getServer().getPluginManager().registerEvents(new BlockPlace(this),this);
        this.getCommand("gentest").setExecutor(new TestCmds(this));
    }

}
