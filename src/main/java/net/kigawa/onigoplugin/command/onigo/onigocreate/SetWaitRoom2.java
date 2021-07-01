package net.kigawa.onigoplugin.command.onigo.onigocreate;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.command.SubCommand;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetWaitRoom2 extends SubCommand {
    OnigoPlugin plugin;
    public SetWaitRoom2(OnigoPlugin plugin) {
        super(plugin);
        this.plugin=plugin;
    }

    @Override
    public String getCommandStr() {
        return "setwaitroom2";
    }

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length!=5){
            commandSender.sendMessage("/onigocreate setwaitroom2 <game name> <x> <y> <z>");
        }else {
            if (commandSender instanceof Player | commandSender instanceof BlockCommandSender) {
                plugin.getOnigoManager().setWaitRoom2(strings[1], Integer.valueOf(strings[2]), Integer.valueOf(strings[3]), Integer.valueOf(strings[4]), commandSender);
                commandSender.sendMessage("end point of wait room is set");
            } else {
                commandSender.sendMessage("this command can use by player or commandBlock");
            }
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }

    @Override
    public List<SubCommand> getCommandList() {
        return null;
    }
}
