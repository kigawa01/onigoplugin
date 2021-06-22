package net.kigawa.onigoplugin.command.onigo;

import net.kigawa.util.plugin.command.SubCommand;
import net.kigawa.util.plugin.plugin.KigawaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Start extends SubCommand {
    KigawaPlugin plugin;

    public Start(KigawaPlugin kigawaPlugin) {
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
