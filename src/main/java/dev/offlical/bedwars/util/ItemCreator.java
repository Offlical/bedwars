package dev.offlical.bedwars.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemCreator {

    private ItemStack itemStack;

    public ItemCreator(Material material) {
        this.itemStack = new ItemStack(material);
    }

    public ItemCreator(ItemStack itemStack) {
        this.itemStack = itemStack.clone();
    }

    public ItemCreator(Material material, short damage) {
        this.itemStack = new ItemStack(material, 1, damage);
    }

    public ItemCreator(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
    }

    public ItemCreator(Material material, int amount, short damage) {
        this.itemStack = new ItemStack(material, amount, damage);
    }

    public ItemCreator setName(String name) {
        if (name != null) {
            name = ChatColor.translateAlternateColorCodes('&', name);
            ItemMeta meta = this.itemStack.getItemMeta();
            meta.setDisplayName(name);
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemCreator setLore(List<String> lore) {
        if (lore != null) {
            List<String> list = new ArrayList<>();
            lore.forEach(line -> list.add(ChatColor.translateAlternateColorCodes('&', line)));
            ItemMeta meta = itemStack.getItemMeta();
            meta.setLore(list);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemCreator addDigSpeed() {
        itemStack.addUnsafeEnchantment(Enchantment.DIG_SPEED, 4);
        return this;
    }

    public ItemCreator addItemFlag(ItemFlag itemFlag) {
        if(itemStack.getItemMeta() != null) { itemStack.getItemMeta().addItemFlags(itemFlag); }
        return this;
    }

    public ItemCreator addStoredEnchant(Enchantment enchantment, int level) {

        if(this.itemStack.getType() == Material.ENCHANTED_BOOK) {

            if(itemStack.getItemMeta() != null) {
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) itemStack.getItemMeta();
                meta.addStoredEnchant(enchantment, level, true);
            }

            return this;

        }

        throw new IllegalArgumentException("setOwner() only applicable for Skull Item");

    }

    public ItemCreator addEnchant(Enchantment enchantment, int level) {
        if(enchantment != null && level != 0) { itemStack.addUnsafeEnchantment(enchantment, level); }
        return this;
    }

    public ItemCreator addEnchants(List<String> enchants) {
        if (enchants != null) {
            enchants.forEach(enchant -> {
                String[] arr = enchant.replace(" ", "").split(",");
                Enchantment enchantment = Enchantment.getByName(arr[0]);
                Integer level = Integer.valueOf(arr[1]);
                itemStack.addUnsafeEnchantment(enchantment, level);
            });
        }
        return this;
    }

    public ItemCreator setUnbreakable(boolean unbreakable) {
        itemStack.getItemMeta().setUnbreakable(true);
        itemStack.getItemMeta().addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        return this;
    }

    public ItemCreator setOwner(OfflinePlayer owner) {

        if(this.itemStack.getType() == Material.PLAYER_HEAD) {
            SkullMeta meta = (SkullMeta) this.itemStack.getItemMeta();
            meta.setOwningPlayer(owner);
            this.itemStack.setItemMeta(meta);
            return this;
        }

        throw new IllegalArgumentException("setOwner() only applicable for Skull Item");

    }

    public ItemCreator setTexture(String texture) {

        if(this.itemStack.getType() == Material.PLAYER_HEAD) {

            SkullMeta meta = (SkullMeta) this.itemStack.getItemMeta();

            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", texture));

            try {
                Field profileField = meta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(meta, profile);

            }catch(IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }

            this.itemStack.setItemMeta(meta);
            return this;

        }

        throw new IllegalArgumentException("setTexture() only applicable for Skull Item");

    }

    public ItemStack get() {
        return itemStack;
    }

}