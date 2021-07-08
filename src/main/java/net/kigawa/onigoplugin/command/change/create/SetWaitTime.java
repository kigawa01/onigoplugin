package net.kigawa.onigoplugin.command.change.create;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.command.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class SetWaitTime extends SubCommand {
    OnigoPlugin plugin;

    public SetWaitTime(OnigoPlugin onigoPlugin) {
        super(onigoPlugin);
        plugin = onigoPlugin;
    }

    @Override
    public String getCommandStr() {
        return "setwaittime";
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        //check length
        if (strings.length == 3) {
            plugin.getChangeGame().setWaitTime(strings[1], commandSender, Integer.valueOf(strings[2]));
            //send message
            commandSender.sendMessage("set wait time");
        }
        //send error message
        else {
            commandSender.sendMessage("/onigo setwaittime <game name> <time(sec)>");
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }


}
