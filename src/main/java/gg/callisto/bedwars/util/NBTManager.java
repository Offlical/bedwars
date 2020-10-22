package gg.callisto.bedwars.util;

import gg.callisto.bedwars.Bedwars;
import net.minecraft.server.v1_16_R2.NBTTagList;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class NBTManager {

    private Bedwars bedwars;

    public NBTManager(Bedwars bedwars) {
        this.bedwars = bedwars;
    }

    public ItemStack addNBT(ItemStack i, String tag, String value) {
        NamespacedKey key = new NamespacedKey(bedwars,tag);
        ItemMeta m = i.getItemMeta();
        m.getPersistentDataContainer().set(key, PersistentDataType.STRING,value);
        i.setItemMeta(m);
        return i;
    }


    public boolean hasTag(ItemStack i, String tag) {
        NamespacedKey key = new NamespacedKey(bedwars,tag);
        ItemMeta m = i.getItemMeta();
        PersistentDataContainer data = m.getPersistentDataContainer();
        if(data.has(key, PersistentDataType.STRING)) {
            String s = data.get(key, PersistentDataType.STRING);
            return s != "";
        }
        return false;
    }


    public String getValue(ItemStack i, String tag) {
        NamespacedKey key = new NamespacedKey(bedwars,tag);
        ItemMeta m = i.getItemMeta();
        PersistentDataContainer data = m.getPersistentDataContainer();
        if(data.has(key, PersistentDataType.STRING)) {
            String s = data.get(key, PersistentDataType.STRING);
            return s;
        }
        return null;
    }


    public boolean hasMobNBT(Entity e, String tag) {
        net.minecraft.server.v1_16_R2.Entity entity = ((CraftEntity)e).getHandle();
        net.minecraft.server.v1_16_R2.NBTTagCompound compound = new net.minecraft.server.v1_16_R2.NBTTagCompound();
        entity.a_(compound);
        NBTTagList list = compound.getList("Tags",8);
        return list.toString().contains(tag);
    }
}
