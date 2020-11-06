package gg.callisto.bedwars.cmds;

import gg.callisto.bedwars.Bedwars;
import gg.callisto.bedwars.generators.Generator;
import gg.callisto.bedwars.generators.GeneratorList;
import gg.callisto.bedwars.generators.GeneratorType;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class TestCmds implements CommandExecutor {

    private Bedwars bedwars;

    public TestCmds(Bedwars bedwars) {
        this.bedwars = bedwars;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("gentest")) {
            bedwars.getGeneratorManager().addGen(new Generator(GeneratorType.EMERALD,p.getLocation(),Arrays.asList(15,10,5),Arrays.asList(30, 60, 90)));

            List<Location> locs = Arrays.asList(new Location(p.getLocation().getWorld(), p.getLocation().getX() - 1,p.getLocation().getY(), p.getLocation().getZ()),
                    new Location(p.getLocation().getWorld(), p.getLocation().getX(),p.getLocation().getY(), p.getLocation().getZ() + 1),
                    new Location(p.getLocation().getWorld(), p.getLocation().getX(),p.getLocation().getY(), p.getLocation().getZ() - 1),
                    new Location(p.getLocation().getWorld(), p.getLocation().getX() + 1,p.getLocation().getY(), p.getLocation().getZ()));

            bedwars.getGeneratorManager().addGenList(new GeneratorList(GeneratorType.DIAMOND,locs,Arrays.asList(15,10,5),Arrays.asList(30,60,90)));

            bedwars.getGameTime().start();
        }


        return true;
    }
}
