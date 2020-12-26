package dev.offlical.bedwars.generators;


import dev.offlical.bedwars.Bedwars;
import dev.offlical.bedwars.game.GameManager;
import lombok.Getter;

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
