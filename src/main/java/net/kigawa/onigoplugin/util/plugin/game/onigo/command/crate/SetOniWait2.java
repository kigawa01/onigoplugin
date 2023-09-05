package net.kigawa.onigoplugin.util.plugin.game.onigo.command.crate;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameManager;
import net.kigawa.onigoplugin.util.plugin.game.onigo.command.OnigoCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SetOniWait2 extends OnigoCommand {
    public SetOniWait2(OnigoPlugin OnigoPlugin, GameManager manager) {
        super(OnigoPlugin, manager);
    }

    @Override
    public String getName() {
        return "setoniwait2";
    }

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 5) {
            commandSender.sendMessage("/onigocreate setoniwait <game name> <x> <y> <z>");
        } else {
                getManager().setOniWait2(strings[1], Integer.valueOf(strings[2]), Integer.valueOf(strings[3]), Integer.valueOf(strings[4]), commandSender);
                commandSender.sendMessage("start point of wait room is set");

        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }
}
