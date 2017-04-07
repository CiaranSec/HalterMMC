package com.ciaran.halt.manager;

import com.ciaran.halt.Halt;
import com.ciaran.halt.manager.managers.HaltedManager;
import com.ciaran.halt.manager.managers.InventoryManager;
import com.ciaran.halt.manager.managers.PlayerSnapshotManager;

public class ManagerHandler {

    private Halt plugin;

    private InventoryManager inventoryManager;
    private HaltedManager haltedManager;
    private PlayerSnapshotManager playerSnapshotManager;

    public ManagerHandler(Halt plugin) {
        this.plugin = plugin;
        loadManagers();
    }

    private void loadManagers() {
        inventoryManager = new InventoryManager(plugin);
        haltedManager = new HaltedManager(plugin);
        playerSnapshotManager = new PlayerSnapshotManager(plugin);
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public HaltedManager getHaltedManager() {
        return haltedManager;
    }

    public PlayerSnapshotManager getPlayerSnapshotManager() {
        return playerSnapshotManager;
    }
}
