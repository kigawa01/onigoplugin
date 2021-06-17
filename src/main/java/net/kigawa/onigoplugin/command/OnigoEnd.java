package net.kigawa.onigoplugin.command;

import net.kigawa.utilplugin.api.command.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class OnigoEnd extends SubCommand {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }

    @Override
    public List<SubCommand> getCommandList() {
        return null;
    }

    @Override
    public String getCommandStr() {
        return null;
    }
}
