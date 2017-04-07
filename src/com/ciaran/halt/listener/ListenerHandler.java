package com.ciaran.halt.listener;

import org.bukkit.event.Listener;

import com.ciaran.halt.Halt;
import com.ciaran.halt.listener.listeners.EntityListener;
import com.ciaran.halt.listener.listeners.InventoryListener;
import com.ciaran.halt.listener.listeners.PlayerListener;

public class ListenerHandler {

    private Halt plugin;
    public ListenerHandler(Halt plugin) {
        this.plugin = plugin;
        loadListeners();
    }

    private Listener[] listeners = {
            new EntityListener(this),
            new InventoryListener(this),
            new PlayerListener(this)
    };

    private void loadListeners() {
        for(Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    public Halt getPlugin() {
        return plugin;
    }

}
