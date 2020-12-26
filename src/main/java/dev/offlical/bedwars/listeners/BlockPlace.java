package dev.offlical.bedwars.listeners;

import dev.offlical.bedwars.Bedwars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {


    private Bedwars bedwars;

    public BlockPlace(Bedwars bedwars) {
        this.bedwars = bedwars;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(!event.getPlayer().isOp()) {
            bedwars.getPlacedBlocks().add(event.getBlock().getLocation());
            event.getPlayer().sendMessage(bedwars.getPlacedBlocks().toString() + " size: " + bedwars.getPlacedBlocks().size());
        }
    }
}
