package net.kigawa.util.plugin.game.onigo.command.game;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.all.command.SubCommand;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.onigo.command.OnigoCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class End extends OnigoCommand {

    KigawaPlugin plugin;

    public End(KigawaPlugin onigoPlugin, GameManager manager) {
        super(onigoPlugin,manager);
        plugin = onigoPlugin;
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 2) {
            getManager().end(strings[1], commandSender);
            return true;
        } else {
            commandSender.sendMessage("/onigo end <game name>");
        }
        return false;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }


    @Override
    public String getName() {
        return "end";
    }
}
