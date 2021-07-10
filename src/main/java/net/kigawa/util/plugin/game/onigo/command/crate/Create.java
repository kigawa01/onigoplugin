package net.kigawa.util.plugin.game.onigo.command.crate;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.onigo.command.OnigoCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Create extends OnigoCommand {
    KigawaPlugin plugin;
    GameManager manager;

    public Create(KigawaPlugin onigoPlugin, GameManager manager) {
        super(onigoPlugin, manager);
        this.plugin = onigoPlugin;
    }


    @Override
    public String getCommandStr() {
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
