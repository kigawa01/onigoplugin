package net.kigawa.onigoplugin.game;

import net.kigawa.util.all.EqualsNamed;
import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.Game;
import net.kigawa.util.plugin.game.onigo.GameData;
import net.kigawa.util.plugin.game.onigo.GameManager;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class ChangeManager extends GameManager {

    public ChangeManager(KigawaPlugin kigawaPlugin, String name) {
        super(kigawaPlugin, name);
    }

    @Override
    public List<Game> initializeGame(List<GameData> data) {
        List<Game> games = new ArrayList<>();

        //take out data list
        for (GameData gameData : data) {
            games.add(new OnigoGame(getPlugin(), gameData, this));
        }
        return games;
    }

    @Override
    public void createGame(CommandSender sender, String name) {
        if (!getGames().contains(new EqualsNamed(name))) {
            OnigoData data = new OnigoData();
            data.setName(name);
            getGames().add(new OnigoGame(getPlugin(), data, this));
            getPlugin().getRecorder().save(data, getName());
        } else {
            sender.sendMessage("this name can't use");
        }
    }
}
