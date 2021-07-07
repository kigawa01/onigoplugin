package net.kigawa.onigoplugin.command.change.game;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.command.FirstCommand;
import net.kigawa.util.plugin.command.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Onigo extends FirstCommand {
    OnigoPlugin plugin;

    public Onigo(OnigoPlugin plugin) {
        super(plugin);
        this.plugin = plugin;
        addSubcommands(new Start(plugin));
        addSubcommands(new End(plugin));
        addSubcommands(new net.kigawa.onigoplugin.command.change.game.List(plugin));
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
    public String getCommandStr() {
        return "onigo";
    }

    @Override
    public int getWordNumber() {
        return 0;
    }


}
