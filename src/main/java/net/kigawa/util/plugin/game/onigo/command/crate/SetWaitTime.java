package net.kigawa.util.plugin.game.onigo.command.crate;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.onigo.command.OnigoCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SetWaitTime extends OnigoCommand {
    OnigoPlugin plugin;

    public SetWaitTime(OnigoPlugin onigoPlugin, GameManager manager) {
        super(onigoPlugin, manager);
        plugin = onigoPlugin;
    }

    @Override
    public String getName() {
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
            getManager().setWaitTime(strings[1], commandSender, Integer.valueOf(strings[2]));
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
