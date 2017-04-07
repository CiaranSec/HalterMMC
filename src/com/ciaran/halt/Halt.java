package com.ciaran.halt;

import org.bukkit.plugin.java.JavaPlugin;

import com.ciaran.commands.HaltCmd;
import com.ciaran.halt.listener.ListenerHandler;
import com.ciaran.halt.manager.ManagerHandler;

public class Halt extends JavaPlugin {

	    private ManagerHandler managerHandler;

	    @Override
	    public void onEnable() {
	        new ListenerHandler(this);
	        managerHandler = new ManagerHandler(this);
	        getCommand("halt").setExecutor(new HaltCmd(this));
	    }

	    public ManagerHandler getManagerHandler() {
	        return managerHandler;
	    }


	}


