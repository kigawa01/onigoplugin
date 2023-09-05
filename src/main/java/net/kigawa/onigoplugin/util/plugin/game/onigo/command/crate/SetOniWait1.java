package net.kigawa.onigoplugin.util.plugin.game.onigo.command.crate;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.util.plugin.all.Util;
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameManager;
import net.kigawa.onigoplugin.util.plugin.game.onigo.command.OnigoCommand;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SetOniWait1 extends OnigoCommand {
    public SetOniWait1(OnigoPlugin OnigoPlugin, GameManager manager) {
        super(OnigoPlugin, manager);
    }

    @Override
    public String getName() {
        return "setoniwait1";
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
            World world= Util.getWorld(commandSender);
            if (world!=null){
                getManager().setOniWait1(strings[1], world.getName(), Integer.valueOf(strings[2]), Integer.valueOf(strings[3]), Integer.valueOf(strings[4]), commandSender);
                commandSender.sendMessage("start point of wait room is set");

            }
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }
}
