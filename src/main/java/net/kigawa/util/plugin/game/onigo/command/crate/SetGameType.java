package net.kigawa.util.plugin.game.onigo.command.crate;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.GameManager;
import net.kigawa.util.plugin.game.onigo.command.OnigoCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SetGameType extends OnigoCommand {
    public SetGameType(KigawaPlugin kigawaPlugin, GameManager manager) {
        super(kigawaPlugin, manager);
    }

    public  abstract List<String> getGameTypes();
    public  boolean checkGameType(String s){
        for (String type:getGameTypes()){
            if (s.equals(type)) return true;
        }
        return false;
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
        if (strings.length == 3 && checkGameType(strings[2])) {
            getManager().setGameType(strings[1], commandSender, strings[2]);
            commandSender.sendMessage("set game type");
        } else {
            commandSender.sendMessage("/onigocreate setgametime <game name> <game type>");
            commandSender.sendMessage(ChatColor.GREEN+"ゲームタイプ");
            for (String type:getGameTypes()){
                commandSender.sendMessage(type);
            }
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }
}
