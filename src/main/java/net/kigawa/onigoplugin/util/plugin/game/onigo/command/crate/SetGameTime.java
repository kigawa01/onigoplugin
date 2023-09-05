package net.kigawa.onigoplugin.util.plugin.game.onigo.command.crate;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameManager;
import net.kigawa.onigoplugin.util.plugin.game.onigo.command.OnigoCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SetGameTime extends OnigoCommand {
    OnigoPlugin plugin;

    public SetGameTime(OnigoPlugin onigoPlugin, GameManager manager) {
        super(onigoPlugin, manager);
        plugin = onigoPlugin;
    }

    @Override
    public String getName() {
        return "setgametime";
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 3) {
            getManager().setGameTime(strings[1], commandSender, Integer.valueOf(strings[2]));
            commandSender.sendMessage("set game time");
        } else {
            commandSender.sendMessage("/onigocreate setgametime <game name> <count(minute)>");
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }


}
