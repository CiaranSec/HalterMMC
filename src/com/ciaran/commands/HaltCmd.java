package com.ciaran.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import com.ciaran.halt.Halt;

public class HaltCmd implements CommandExecutor {

    private Halt plugin;
    public HaltCmd(Halt plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!sender.hasPermission("halt.freeze")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
            return true;
        }

        if(args.length != 1)
            return false;

        Player target = plugin.getServer().getPlayer(args[0]);

        if(target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        if(plugin.getManagerHandler().getHaltedManager().isHalted(target.getUniqueId())) {
            sender.sendMessage(ChatColor.GREEN + "You unhalted " + target.getName());

            plugin.getServer().broadcast(ChatColor.RED + target.getName() + ChatColor.GOLD + " has been unhalted by " + ChatColor.RED +
                    (sender instanceof Player ? sender.getName() : "Console"), "halt.freeze");

            unhaltPlayer(target);
            return true;
        }

        sender.sendMessage(ChatColor.GREEN + "You froze " + target.getName());

        plugin.getServer().broadcast(ChatColor.RED + target.getName() + ChatColor.GOLD + " has been frozen by " + ChatColor.RED +
                (sender instanceof Player ? sender.getName() : "Console"), "freeze.freeze");

        haltPlayer(target);
        return true;
    }

    private void haltPlayer(Player player) {
        plugin.getManagerHandler().getPlayerSnapshotManager().takeSnapshot(player);
        plugin.getManagerHandler().getHaltedManager().haltUUID(player.getUniqueId());
        player.sendMessage(ChatColor.GREEN + "You have been halted by a staff member ! Do not log out ! (Do not even try to use SelfDestruct)");
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setWalkSpeed(0.0F);
        clearPotionEffects(player);
        player.updateInventory();
        player.openInventory(plugin.getManagerHandler().getInventoryManager().getHaltedInv());
    }

    private void unhaltPlayer(Player player) {
        plugin.getManagerHandler().getHaltedManager().unhaltUUID(player.getUniqueId());
        player.sendMessage(ChatColor.GREEN + "You have been unhalted by a staff member.");
        player.closeInventory();
        plugin.getManagerHandler().getPlayerSnapshotManager().restorePlayer(player);
    }

    private void clearPotionEffects(Player player) {
        for(PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }
}
