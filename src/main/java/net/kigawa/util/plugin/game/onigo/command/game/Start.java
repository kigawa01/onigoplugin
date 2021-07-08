package net.kigawa.util.plugin.game.onigo.command.game;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.all.command.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

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
            plugin.getChangeGame().start(strings[1], commandSender);
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
