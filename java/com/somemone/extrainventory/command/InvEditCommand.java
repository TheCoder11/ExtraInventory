package com.somemone.extrainventory.command;

import com.somemone.extrainventory.ExtraInventory;
import com.somemone.extrainventory.ExtraInventoryInstance;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvEditCommand implements CommandExecutor {

    public ExtraInventory plugin;

    public InvEditCommand (ExtraInventory plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.isOp()) {

            Player player = Bukkit.getPlayer(args[0]);

            if (player != null && this.plugin.inventories.size() > 0) {

                if (this.plugin.inventories.containsKey(player)) {
                    ExtraInventoryInstance eii = this.plugin.inventories.get(player);
                    eii.addRows(Integer.parseInt(args[1]));
                    sender.sendMessage(ChatColor.GOLD + args[1] + " row was added to " + args[0] + "'s account");
                } else {
                    this.plugin.inventories.put(player, new ExtraInventoryInstance(player, Integer.parseInt(args[1])));
                    sender.sendMessage(ChatColor.GOLD + args[0] + "'s inventory was created with " + args[1] + " rows.");
                    sender.sendMessage(player.getName());
                }


            } else {
                return false;
            }
        }

        return true;

    }
}
