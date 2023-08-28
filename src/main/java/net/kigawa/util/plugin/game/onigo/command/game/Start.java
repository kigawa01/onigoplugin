package net.kigawa.util.plugin.game.onigo.command.game;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.onigo.command.OnigoCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Start extends OnigoCommand {
    OnigoPlugin plugin;

    public Start(OnigoPlugin onigoPlugin, GameManager manager) {
        super(onigoPlugin, manager);
        plugin = onigoPlugin;
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 3 | strings.length == 2) {
            switch (strings.length) {
                case 3:
                    getManager().start(strings[1], commandSender, strings[2]);
                    break;
                case 2:
                    getManager().start(strings[1], commandSender, null);
            }
        } else {
            commandSender.sendMessage("/onigo start <game name> (stage name)");
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }


    @Override
    public String getName() {
        return "start";
    }

}
