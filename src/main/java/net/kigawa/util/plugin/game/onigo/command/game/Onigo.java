package net.kigawa.util.plugin.game.onigo.command.game;


import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.all.command.FirstCommand;
import net.kigawa.util.plugin.game.onigo.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class Onigo extends FirstCommand {
    OnigoPlugin plugin;

    public Onigo(OnigoPlugin plugin, GameManager manager) {
        super(plugin);
        this.plugin = plugin;
        addSubcommands(new Start(plugin, manager));
        addSubcommands(new End(plugin, manager));
        addSubcommands(new List(plugin, manager));
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }


    @Override
    public int getWordNumber() {
        return 0;
    }
}
