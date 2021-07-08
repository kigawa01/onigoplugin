package net.kigawa.util.plugin.game.onigo.command.crate;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.all.command.SubCommand;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.onigo.command.SecondCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SetOniCount extends SecondCommand {
    OnigoPlugin plugin;

    public SetOniCount(OnigoPlugin onigoPlugin, GameManager manager) {
        super(onigoPlugin,manager);
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
            getManager().setOniCount(strings[1], commandSender, Integer.valueOf(strings[2]));
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
