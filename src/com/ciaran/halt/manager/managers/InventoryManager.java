package com.ciaran.halt.manager.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.ciaran.halt.Halt;
import com.ciaran.halt.manager.Manager;

public class InventoryManager extends Manager {

    private Inventory haltedInv;

    public InventoryManager(Halt plugin) {
        super(plugin);
        initiateHaltedInv();
    }

    private void initiateHaltedInv() {
        haltedInv = plugin.getServer().createInventory(null, 9, "Screenshare Inventory");
        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = paper.getItemMeta();
        List<String> lores = new ArrayList<>();
        lores.add(0, "DO NOT LOG OUT YOU HAVE 5 MINUTES");
        lores.add(1, "https://www.teamspeak.com/downloads");
        itemMeta.setLore(lores);
        itemMeta.setDisplayName("ts.munchymc.com");
        paper.setItemMeta(itemMeta);

        haltedInv.setItem(4, paper);
    }

    public Inventory getHaltedInv() {
        return haltedInv;
    }


}