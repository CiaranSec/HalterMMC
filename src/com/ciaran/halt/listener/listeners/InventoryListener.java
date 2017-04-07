package com.ciaran.halt.listener.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.ciaran.halt.listener.ListenerHandler;

public class InventoryListener implements Listener {

    private ListenerHandler handler;

    public InventoryListener(ListenerHandler handler) {
        this.handler = handler;
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {

        Player player = (Player) e.getPlayer();

        if(handler.getPlugin().getManagerHandler().getHaltedManager().isHalted(player.getUniqueId())) {

            new BukkitRunnable() {
                @Override
                public void run() {
                    player.openInventory(handler.getPlugin().getManagerHandler().getInventoryManager().getHaltedInv());
                }
            }.runTaskLater(handler.getPlugin(), 1);

        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if(handler.getPlugin().getManagerHandler().getHaltedManager().isHalted(player.getUniqueId())) {

            e.setCancelled(true);

        }
    }
}
