package net.kigawa.util.plugin.game.onigo.command.crate;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.onigo.command.OnigoCommand;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetEndLoc extends OnigoCommand {
    OnigoPlugin plugin;

    public SetEndLoc(OnigoPlugin plugin, GameManager manager) {
        super(plugin, manager);
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "setendloc";
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 5) {
            commandSender.sendMessage("/onigocreate setendloc <game name> <x> <y> <z>");
        } else {
            if (commandSender instanceof Player | commandSender instanceof BlockCommandSender) {
                World world = null;
                if (commandSender instanceof Player) {
                    world = ((Player) commandSender).getWorld();
                }
                if (commandSender instanceof BlockCommandSender) {
                    world = ((BlockCommandSender) commandSender).getBlock().getWorld();
                }
                getManager().setEndLoc(strings[1], commandSender, world.getName(), Integer.valueOf(strings[2]), Integer.valueOf(strings[3]), Integer.valueOf(strings[4]));
                commandSender.sendMessage("set end end loc");
            } else {
                commandSender.sendMessage("this command can use by player or commandBlock");
            }
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }

}
