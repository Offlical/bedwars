package gg.callisto.bedwars.generators;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum GeneratorType {

    EMERALD(Collections.singletonList(Material.EMERALD),"Emerald"),DIAMOND(Collections.singletonList(Material.DIAMOND),"Diamond"),
    BASE_GEN(Arrays.asList(Material.IRON_AXE,Material.GOLD_INGOT),"Base");

    private List<Material> mat;
    private String name;

    GeneratorType(List<Material> mat,String name) {
        this.mat = mat;
        this.name = name;
    }

    public List<Material> getList(){
        return mat;
    }

    public String getName() {
        return name;
    }
}
