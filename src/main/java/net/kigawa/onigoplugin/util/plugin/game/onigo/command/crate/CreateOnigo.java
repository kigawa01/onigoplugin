package net.kigawa.onigoplugin.util.plugin.game.onigo.command.crate;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.util.plugin.all.command.FirstCommand;
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class CreateOnigo extends FirstCommand {
    OnigoPlugin plugin;

    public CreateOnigo(OnigoPlugin plugin, GameManager manager) {
        super(plugin);
        this.plugin = plugin;
        addSubcommands(new SetGameTime(plugin, manager));
        addSubcommands(new SetEndLoc(plugin, manager));
        addSubcommands(getSetGameTypeCommand(plugin,manager));
        addSubcommands(new SetOniWait1(plugin,manager));
        addSubcommands(new SetOniWait2(plugin,manager));
    }


    public abstract SetGameType getSetGameTypeCommand(OnigoPlugin plugin,GameManager manager);

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }
}
