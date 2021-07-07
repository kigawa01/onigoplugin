package net.kigawa.onigoplugin.command.change.create;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.command.SubCommand;
import net.kigawa.util.plugin.command.TabList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CreateGame extends SubCommand {
    OnigoPlugin plugin;

    public CreateGame(OnigoPlugin onigoPlugin) {
        super(onigoPlugin);
        this.plugin = onigoPlugin;
    }

    @Override
    public void addTabLists(List<TabList> tabLists) {

    }

    @Override
    public String getCommandStr() {
        return "create";
    }

    @Override
    public void addSubcommands(List<SubCommand> subCommands) {

    }

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 2) {
            plugin.getOnigoManager().createOnigo(commandSender, strings[1]);
            return true;
        } else {
            commandSender.sendMessage("/onigocreate cretate <name>");
            return true;
        }
    }

    @Override
    public int getWordNumber() {
        return 0;
    }


}
