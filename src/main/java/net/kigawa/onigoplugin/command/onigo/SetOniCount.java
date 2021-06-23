package net.kigawa.onigoplugin.command.onigo;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.command.SubCommand;
import net.kigawa.util.plugin.plugin.KigawaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class SetOniCount extends SubCommand {
    OnigoPlugin plugin;
    public SetOniCount(OnigoPlugin onigoPlugin) {
        super(onigoPlugin);
        plugin=onigoPlugin;
    }

    @Override
    public String getCommandStr() {
        return "setonicount";
    }

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length==3){
            plugin.getOnigoManager().setOniCount(strings[1],commandSender,Integer.valueOf(strings[2]));
            commandSender.sendMessage("oni count is set");
        }else {
            commandSender.sendMessage("/onigocreate setonicount <game name> <count>");
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
