package net.kigawa.onigoplugin.command.change.create;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.all.command.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SetOniCount extends SubCommand {
    OnigoPlugin plugin;

    public SetOniCount(OnigoPlugin onigoPlugin) {
        super(onigoPlugin);
        plugin = onigoPlugin;
    }

    @Override
    public String getCommandStr() {
        return "setonicount";
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 3) {
            plugin.getChangeGame().setOniCount(strings[1], commandSender, Integer.valueOf(strings[2]));
            commandSender.sendMessage("oni count is set");
        } else {
            commandSender.sendMessage("/onigocreate setonicount <game name> <count>");
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }


}
