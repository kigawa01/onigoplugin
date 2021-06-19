package net.kigawa.onigoplugin.command;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.utilplugin.api.command.MainCommand;
import net.kigawa.utilplugin.api.command.SubCommand;
import net.kigawa.utilplugin.api.list.EqualsCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Test extends MainCommand {
    OnigoPlugin plugin;
    public Test(OnigoPlugin plugin) {
        super(plugin);
        this.plugin=plugin;
    }

    @Override
    public String getCommandStr() {
        return "test";
    }

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        plugin.logger(plugin.getOnigo().getCommandList().size());
        plugin.logger(plugin.getOnigo().getCommandList().get(0).getCommandStr());
        plugin.logger(plugin.getOnigo().getCommandList().contains(new EqualsCommand("start")));
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

    @Override
    public List<SubCommand> getCommandList() {
        return null;
    }
}
