package com.ciaran.halt.listener.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import com.ciaran.halt.listener.ListenerHandler;

public class EntityListener implements Listener {

    private ListenerHandler handler;
    public EntityListener(ListenerHandler handler) {
        this.handler = handler;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();

            if(handler.getPlugin().getManagerHandler().getHaltedManager().isHalted(player.getUniqueId())) {
                e.setCancelled(true);
            }

        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {

            Player player = (Player) e.getDamager();

            if(handler.getPlugin().getManagerHandler().getHaltedManager().isHalted(player.getUniqueId())) {
                e.setCancelled(true);
            }
        }
    }
}