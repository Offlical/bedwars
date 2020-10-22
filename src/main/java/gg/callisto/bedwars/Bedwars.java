package gg.callisto.bedwars;

import gg.callisto.bedwars.game.GameManager;
import gg.callisto.bedwars.game.GameTime;
import gg.callisto.bedwars.generators.GeneratorManager;
import gg.callisto.bedwars.listeners.*;
import gg.callisto.bedwars.teams.TeamsManager;
import gg.callisto.bedwars.util.NBTManager;
import lombok.Getter;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Bedwars extends JavaPlugin {

    private GameManager gameManager;
    private TeamsManager teamsManager;
    private GeneratorManager generatorManager;
    private NBTManager nbtManager;
    private GameTime gameTime;

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
        this.getServer().getPluginManager().registerEvents(new PlayerInteract(),this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(),this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuit(),this);
        this.getServer().getPluginManager().registerEvents(new PlayerDeath(),this);
        this.getServer().getPluginManager().registerEvents(new EntityInteract(),this);
        this.getServer().getPluginManager().registerEvents(new EntityAttackEntity(),this);
        this.getServer().getPluginManager().registerEvents(new BlockBreak(),this);
        this.getServer().getPluginManager().registerEvents(new BlockPlace(),this);
    }

}
