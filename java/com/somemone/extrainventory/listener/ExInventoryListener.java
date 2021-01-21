package com.somemone.extrainventory.listener;

import com.somemone.extrainventory.ExtraInventory;
import com.somemone.extrainventory.ExtraInventoryInstance;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ExInventoryListener implements Listener {

    public ExtraInventory plugin;

    public ExInventoryListener (ExtraInventory plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract (PlayerInteractEvent e) {
        if (e.getPlayer().hasPermission("extrainventory.showinv")) {
            if (e.getItem() != null) {
                if (e.getItem().getType() == Material.COMPASS) {
                    ExtraInventoryInstance eii = this.plugin.inventories.get(e.getPlayer());

                    if (eii != null) {
                        e.getPlayer().openInventory(eii.inventory);
                    }
                }
            }
        }
    }
}
