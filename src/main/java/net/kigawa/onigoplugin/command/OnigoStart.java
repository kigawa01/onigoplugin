package net.kigawa.onigoplugin.command;

import net.kigawa.utilplugin.api.command.SubCommand;
import net.kigawa.utilplugin.api.plugin.KigawaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class OnigoStart extends SubCommand {
    KigawaPlugin plugin;

    public OnigoStart(KigawaPlugin kigawaPlugin) {
        super(kigawaPlugin);
        plugin=kigawaPlugin;
    }

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
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
    public List<SubCommand> getCommandList() {
        return null;
    }

    @Override
    public String getCommandStr() {
        return "start";
    }

}
