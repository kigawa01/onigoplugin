package net.kigawa.onigoplugin.command;

import net.kigawa.utilplugin.api.command.SubCommands;
import net.kigawa.utilplugin.api.command.LastCommand;
import net.kigawa.utilplugin.api.plugin.KigawaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class OnigoEnd extends SubCommands {


    public OnigoEnd(KigawaPlugin kigawaPlugin) {
        super(kigawaPlugin);
    }

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }

    @Override
    public List<LastCommand> getCommandList() {
        return null;
    }

    @Override
    public String getCommandStr() {
        return "end";
    }
}
