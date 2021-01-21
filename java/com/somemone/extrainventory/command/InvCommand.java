package com.somemone.extrainventory.command;

import com.somemone.extrainventory.ExtraInventory;
import com.somemone.extrainventory.ExtraInventoryInstance;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvCommand implements CommandExecutor {

    public ExtraInventory plugin;

    public InvCommand (ExtraInventory plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (this.plugin.inventories.containsKey(player)) {
            ExtraInventoryInstance eii = this.plugin.inventories.get(player);

            player.openInventory(eii.inventory);
        } else {
            sender.sendMessage(ChatColor.DARK_RED + "You do not have an extra inventory");
        }

        return true;
    }
}
