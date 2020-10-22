package gg.callisto.bedwars.generators;


import gg.callisto.bedwars.Bedwars;
import gg.callisto.bedwars.game.GameManager;
import lombok.Getter;
import org.bukkit.entity.Panda;

import java.util.HashSet;

@Getter
public class GeneratorManager {


    private HashSet<Generator> gens;
    private GameManager gameManager;

    public GeneratorManager(Bedwars bedwars) {
        this.gameManager = bedwars.getGameManager();
        gens = new HashSet<>();
    }

    public void addGen(Generator gen) {
        gens.add(gen);
    }

    public void removeGen(Generator gen) {
        gens.remove(gen);
    }




}
