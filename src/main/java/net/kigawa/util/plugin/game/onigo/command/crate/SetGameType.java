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
        return "setgametime";
    }

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }
}
