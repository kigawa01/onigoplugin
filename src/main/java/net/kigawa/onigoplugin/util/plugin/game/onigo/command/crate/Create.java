package net.kigawa.onigoplugin.util.plugin.game.onigo.command.crate;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameManager;
import net.kigawa.onigoplugin.util.plugin.game.onigo.command.OnigoCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Create extends OnigoCommand {
    OnigoPlugin plugin;
    GameManager manager;

    public Create(OnigoPlugin onigoPlugin, GameManager manager) {
        super(onigoPlugin, manager);
        this.plugin = onigoPlugin;
    }


    @Override
    public String getName() {
        return "create";
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 2) {
            getManager().createGame(commandSender, strings[1]);
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
