package net.kigawa.util.plugin.game.onigo.command.game;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.all.command.SubCommand;
import net.kigawa.util.plugin.game.onigo.Game;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.onigo.command.OnigoCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class List extends OnigoCommand {
    KigawaPlugin plugin;

    public List(KigawaPlugin onigoPlugin, GameManager manager) {
        super(onigoPlugin,manager);
        plugin = onigoPlugin;
    }


    @Override
    public String getCommandStr() {
        return "list";
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        java.util.List<Game> games = getManager().getGames();
        for (int i = 0; i < games.size(); i++) {
            commandSender.sendMessage("name " + games.get(i).getName());
            commandSender.sendMessage(" world " + games.get(i).getD().getWaitRoomWorld());
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }


}
