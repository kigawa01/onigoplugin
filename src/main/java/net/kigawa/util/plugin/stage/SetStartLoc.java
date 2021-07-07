package net.kigawa.util.plugin.stage;

import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.command.SubCommand;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetStartLoc extends SubCommand {
    KigawaPlugin plugin;

    public SetStartLoc(KigawaPlugin kigawaPlugin) {
        super(kigawaPlugin);
        plugin = kigawaPlugin;
    }

    @Override
    public String getCommandStr() {
        return "setstartloc";
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        //check args
        if (strings.length == 5) {
            //set start loc
            plugin.getStageManager().setStartLoc(strings[1], Integer.valueOf(strings[2]), Integer.valueOf(strings[3]), Integer.valueOf(strings[4]),
                    commandSender);
            //send message
            commandSender.sendMessage("set start loc");
        } else {
            commandSender.sendMessage("/stage setstartloc <game name> <x> <y> <z>");
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }
}
