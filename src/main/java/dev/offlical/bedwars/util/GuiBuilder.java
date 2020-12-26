package dev.offlical.bedwars.util;

import dev.offlical.bedwars.Bedwars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.function.Consumer;


public class GuiBuilder implements Listener {

    private String name;
    private int rows;
    private final HashMap<Integer, ItemStack> items;
    private final HashMap<Integer, Consumer<InventoryClickEvent>> runnableHashMap;
    private int slot;


    public GuiBuilder() {
        Bukkit.getPluginManager().registerEvents(this, Bedwars.getPlugin(Bedwars.class));
        this.name = "Inventory";
        this.rows = 1;
        this.items = new HashMap<>();
        this.runnableHashMap = new HashMap<>();
    }

    public GuiBuilder rows(int newRows) {
        this.rows = newRows;
        return this;
    }

    public GuiBuilder name(String newName) {
        this.name = ChatColor.translateAlternateColorCodes('&', newName);
        return this;
    }

    public GuiBuilder item(int slot, ItemStack item) {
        items.put(slot, item);
        this.slot = slot;
        return this;
    }


    public GuiBuilder item(int slot, ItemStack item, Consumer<InventoryClickEvent> consumer) {
        items.put(slot, item);
        this.slot = slot;
        runnableHashMap.put(slot, consumer);
        return this;
    }



    public void onClick(Consumer<InventoryClickEvent> runnable) {
        runnableHashMap.put(slot, runnable);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if(event.getCurrentItem() != null) {

            if(event.getCurrentItem().getType() != Material.AIR) {

                if(ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase(ChatColor.stripColor(name))) {

                    int slot = event.getSlot();

                    if(runnableHashMap.get(slot) != null) {
                        runnableHashMap.get(slot).accept(event);
                        event.setCancelled(true);
                        ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.UI_BUTTON_CLICK, (float) 0.5, 1);
                    }

                }
            }
        }
    }

    @EventHandler
    public void onPlayerClose(InventoryCloseEvent event) {
        if(ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase(ChatColor.stripColor(name))) { HandlerList.unregisterAll(this); }
    }


    public Inventory make() {
        if(rows > 6) { throw new IllegalArgumentException("Too many rows in the created inventory!"); }
        Inventory inv = Bukkit.createInventory(null, rows * 9, name);
        for(int f : items.keySet()) { inv.setItem(f, items.get(f)); }
        return inv;
    }

}