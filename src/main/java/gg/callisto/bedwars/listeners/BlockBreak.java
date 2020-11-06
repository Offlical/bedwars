package gg.callisto.bedwars.listeners;

import gg.callisto.bedwars.Bedwars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    private Bedwars bedwars;

    public BlockBreak(Bedwars bedwars) {
        this.bedwars = bedwars;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(event.getPlayer().isOp()) return;

        if(bedwars.getPlacedBlocks().contains(event.getBlock().getLocation()))
            bedwars.getPlacedBlocks().remove(event.getBlock().getLocation());
        else
            event.setCancelled(true);
    }
}
