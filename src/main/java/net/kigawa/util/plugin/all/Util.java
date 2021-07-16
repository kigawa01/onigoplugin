package net.kigawa.util.plugin.all;

import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Util {
    public static World getWorld(CommandSender sender) {
        World world = null;
        if (sender instanceof Player | sender instanceof BlockCommandSender) {

            if (sender instanceof Player) {
                world = ((Player) sender).getWorld();
            }
            if (sender instanceof BlockCommandSender) {
                world = ((BlockCommandSender) sender).getBlock().getWorld();
            }
        } else {
            sender.sendMessage("this command can use by player or commandBlock");
        }
        return world;
    }
}
