package net.kigawa.onigoplugin.command.change.game;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.game.change.OnigoGame;
import net.kigawa.util.plugin.command.SubCommand;
import net.kigawa.util.plugin.command.TabList;
import net.kigawa.util.plugin.game.onigo.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class List extends SubCommand {
    OnigoPlugin plugin;

    public List(OnigoPlugin onigoPlugin) {
        super(onigoPlugin);
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
        java.util.List<Game> games = plugin.getChangeGame().getGames();
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
