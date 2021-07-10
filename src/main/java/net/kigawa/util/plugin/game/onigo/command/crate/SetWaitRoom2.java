package net.kigawa.util.plugin.game.onigo.command.crate;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.onigo.command.OnigoCommand;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWaitRoom2 extends OnigoCommand {
    KigawaPlugin plugin;

    public SetWaitRoom2(KigawaPlugin plugin, GameManager manager) {
        super(plugin, manager);
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "setwaitroom2";
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 5) {
            commandSender.sendMessage("/onigocreate setwaitroom2 <game name> <x> <y> <z>");
        } else {
            if (commandSender instanceof Player | commandSender instanceof BlockCommandSender) {
                getManager().setWaitRoom2(strings[1], Integer.valueOf(strings[2]), Integer.valueOf(strings[3]), Integer.valueOf(strings[4]), commandSender);
                commandSender.sendMessage("end point of wait room is set");
            } else {
                commandSender.sendMessage("this command can use by player or commandBlock");
            }
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }

}
