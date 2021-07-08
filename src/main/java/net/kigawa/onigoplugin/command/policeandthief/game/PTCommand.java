package net.kigawa.onigoplugin.command.policeandthief.game;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.all.command.FirstCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class PTCommand extends FirstCommand {
    OnigoPlugin plugin;

    public PTCommand(OnigoPlugin plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @Override
    public String getCommandStr() {
        return "pandt";
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

}
