package com.somemone.extrainventory;

import com.somemone.extrainventory.command.InvCommand;
import com.somemone.extrainventory.command.InvEditCommand;
import com.somemone.extrainventory.listener.ExInventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public final class ExtraInventory extends JavaPlugin {

    public HashMap<Player, ExtraInventoryInstance> inventories;

    @Override
    public void onEnable() {

        this.inventories = new HashMap<Player, ExtraInventoryInstance>();

        // Loads inventories from file
        YamlConfiguration c = new YamlConfiguration();

        File dumpFile = new File(getDataFolder(), "inventories.yml");

        if (Files.exists( dumpFile.toPath() )) {
            try {
                c.load( new File( getDataFolder(), "inventories.yml" ));
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }

            Set<String> playerList = c.getKeys(false);

            for (String player : playerList) {

                try {
                    Player playerObj = Bukkit.getPlayer(UUID.fromString(player));

                    ItemStack[] items = ((List<ItemStack>) c.get(player)).toArray(new ItemStack[0]);

                    int rows = c.getInt("rows." + player);

                    ExtraInventoryInstance eii = new ExtraInventoryInstance(playerObj, rows);

                    for (ItemStack item : items) {
                        if (item != null) {
                            eii.inventory.addItem(item);
                        }
                    }

                    this.inventories.put(playerObj, eii);
                } catch (IllegalArgumentException e) {

                }
            }
        }

        // Loads the Commands and Listeners
        getServer().getPluginManager().registerEvents(new ExInventoryListener(this), this);

        this.getCommand("inv").setExecutor(new InvCommand(this));
        this.getCommand("invadd").setExecutor(new InvEditCommand(this));

    }

    @Override
    public void onDisable() {


        YamlConfiguration c = new YamlConfiguration();

        for (Player key : inventories.keySet()) {

            if (key != null) {

                ExtraInventoryInstance eii = inventories.get(key);

                c.set(key.getUniqueId().toString(), eii.inventory.getContents());

                c.set("rows." + key.getUniqueId().toString(), eii.rows);

            }
        }

        try {
            c.save(new File( getDataFolder(), "inventories.yml" ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
