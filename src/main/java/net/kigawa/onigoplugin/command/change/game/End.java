package net.kigawa.onigoplugin.command.change.game;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.command.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class End extends SubCommand {

    OnigoPlugin plugin;
    public End(OnigoPlugin onigoPlugin) {
        super(onigoPlugin);
        plugin=onigoPlugin;
    }

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length==2){
            plugin.getOnigoManager().end(strings[1],commandSender);
            return true;
        }else {
            commandSender.sendMessage("/onigo end <game name>");
        }
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
        return "end";
    }
}
