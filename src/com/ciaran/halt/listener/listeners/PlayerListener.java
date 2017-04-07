package com.ciaran.halt.listener.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.ciaran.halt.listener.ListenerHandler;

public class PlayerListener implements Listener {

    private ListenerHandler handler;
    public PlayerListener(ListenerHandler handler) {
        this.handler = handler;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        if (handler.getPlugin().getManagerHandler().getHaltedManager().isHalted(player.getUniqueId())) {
            handler.getPlugin().getServer().broadcast(ChatColor.GOLD + player.getName() + " logged out while halted", "halted.freeze");
            handler.getPlugin().getManagerHandler().getHaltedManager().unhaltUUID(player.getUniqueId());
            handler.getPlugin().getManagerHandler().getPlayerSnapshotManager().restorePlayer(player);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        if(handler.getPlugin().getManagerHandler().getHaltedManager().isHalted(e.getPlayer().getUniqueId()))
            e.setCancelled(true);
    }
}