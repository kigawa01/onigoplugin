package net.kigawa.util.plugin.game.onigo.command.crate;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.onigo.command.OnigoCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SetGameType extends OnigoCommand {
    public SetGameType(KigawaPlugin kigawaPlugin, GameManager manager) {
        super(kigawaPlugin, manager);
    }

    @Override
    public String getName() {
        return "setgametype";
    }

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 3&&(strings[2].equals("change")|strings[2].equals("increase"))) {
            getManager().setGameType(strings[1], commandSender,strings[2]);
            commandSender.sendMessage("set game type");
        } else {
            commandSender.sendMessage("/onigocreate setgametime <game name> <change/increase>");
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }
}
