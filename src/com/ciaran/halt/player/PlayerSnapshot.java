package com.ciaran.halt.player;

import java.util.Collection;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class PlayerSnapshot {

    private ItemStack[] mainContent;
    private ItemStack[] armorContent;
    private Collection<PotionEffect> potionEffects;
    private float walkSpeed;

    public PlayerSnapshot(Player player) {
        this.mainContent = player.getInventory().getContents();
        this.armorContent = player.getInventory().getArmorContents();
        this.potionEffects = player.getActivePotionEffects();
        this.walkSpeed = player.getWalkSpeed();
    }

    public ItemStack[] getMainContent() {
        return mainContent;
    }

    public ItemStack[] getArmorContent() {
        return armorContent;
    }

    public Collection<PotionEffect> getPotionEffects() {
        return potionEffects;
    }

    public float getWalkSpeed() {
        return walkSpeed;
    }
}