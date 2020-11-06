package gg.callisto.bedwars.generators;


import gg.callisto.bedwars.Bedwars;
import gg.callisto.bedwars.game.GameManager;
import lombok.Getter;
import org.bukkit.entity.Panda;

import java.util.HashSet;

@Getter
public class GeneratorManager {


    private HashSet<Generator> gens;
    private HashSet<GeneratorList> genLists;
    private GameManager gameManager;

    public GeneratorManager(Bedwars bedwars) {
        this.gameManager = bedwars.getGameManager();
        gens = new HashSet<>();
        genLists = new HashSet<>();
    }

    public void addGen(Generator gen) {
        gens.add(gen);
    }

    public void removeGen(Generator gen) {
        gens.remove(gen);
    }


    public void addGenList(GeneratorList list) {
        genLists.add(list);
    }

    public void removeGenList(GeneratorList list) {
        genLists.remove(list);
    }



}
