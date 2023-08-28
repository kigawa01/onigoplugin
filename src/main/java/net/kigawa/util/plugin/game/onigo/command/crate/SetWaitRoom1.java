package net.kigawa.util.plugin.game.onigo.command.crate;


import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.onigo.command.OnigoCommand;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWaitRoom1 extends OnigoCommand {
    OnigoPlugin plugin;

    public SetWaitRoom1(OnigoPlugin plugin, GameManager manager) {
        super(plugin,manager);
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "setwaitroom1";
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 5) {
            commandSender.sendMessage("/onigocreate setwaitroom2 <game name> <x> <y> <z>");
        } else {
            if (commandSender instanceof Player | commandSender instanceof BlockCommandSender) {
                World world = null;
                if (commandSender instanceof Player) {
                    world = ((Player) commandSender).getWorld();
                }
                if (commandSender instanceof BlockCommandSender) {
                    world = ((BlockCommandSender) commandSender).getBlock().getWorld();
                }
                getManager().setWaitRoom1(strings[1], world.getName(), Integer.valueOf(strings[2]), Integer.valueOf(strings[3]), Integer.valueOf(strings[4]), commandSender);
                commandSender.sendMessage("start point of wait room is set");
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
