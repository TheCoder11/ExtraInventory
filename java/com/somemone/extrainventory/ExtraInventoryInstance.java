package com.somemone.extrainventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ExtraInventoryInstance {

    public Inventory inventory;
    public Player owner;
    public int rows;

    public ExtraInventoryInstance (Player owner, int rows) {
        this.rows = rows;
        this.owner = owner;
        this.inventory = Bukkit.createInventory(owner, rows * 9);
    }

    public void addRows (int rows) {
        this.rows = this.rows + rows;
        Inventory newInventory = Bukkit.createInventory(owner, this.rows * 9);

        ItemStack[] inventoryContents = this.inventory.getContents();
        ArrayList<ItemStack> input = new ArrayList<ItemStack>();


        for (ItemStack item : inventoryContents) {
            if (item != null) {
                input.add(item);
                newInventory.addItem(item);
            }
        }

        this.inventory = newInventory;
    }
}
