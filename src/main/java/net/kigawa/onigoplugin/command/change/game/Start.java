package net.kigawa.onigoplugin.command.change.game;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.command.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Start extends SubCommand {
    OnigoPlugin plugin;

    public Start(OnigoPlugin onigoPlugin) {
        super(onigoPlugin);
        plugin = onigoPlugin;
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 2) {
            plugin.getOnigoManager().start(strings[1], commandSender);
        } else {
            commandSender.sendMessage("/onigo start <game name>");
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }


    @Override
    public String getCommandStr() {
        return "start";
    }

}
