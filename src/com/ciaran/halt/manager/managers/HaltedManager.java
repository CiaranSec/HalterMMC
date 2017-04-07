package com.ciaran.halt.manager.managers;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.ciaran.halt.Halt;
import com.ciaran.halt.manager.Manager;

public class HaltedManager extends Manager {

    private Set<UUID> haltedUUIDs;

    public HaltedManager(Halt plugin) {
        super(plugin);
        haltedUUIDs = new HashSet<>();
    }

    public void haltUUID(UUID uuid) {
        haltedUUIDs.add(uuid);
    }

    public void unhaltUUID(UUID uuid) {
    	haltedUUIDs.remove(uuid);
    }

    public boolean isHalted(UUID uuid) {
        return haltedUUIDs.contains(uuid);
    }
}