package gg.callisto.bedwars.generators;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum GeneratorType {

    EMERALD(Collections.singletonList(Material.EMERALD)),DIAMOND(Collections.singletonList(Material.DIAMOND)),BASE_GEN(Arrays.asList(Material.IRON_AXE,Material.GOLD_INGOT));

    private List<Material> mat;


    GeneratorType(List<Material> mat) {
        this.mat = mat;
    }

    public List<Material> getList(){
        return mat;
    }
}
