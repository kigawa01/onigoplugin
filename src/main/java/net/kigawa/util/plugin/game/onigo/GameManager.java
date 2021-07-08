package net.kigawa.util.plugin.game.onigo;

import net.kigawa.onigoplugin.game.change.EqualsOniChange;
import net.kigawa.onigoplugin.game.change.OnigoData;
import net.kigawa.onigoplugin.game.change.OnigoGame;
import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.yaml.EqualsYamlData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    public boolean changeOni(Player oni, Player runner) {
        return games.contains(new EqualsOniChange(oni, runner));
    }

    List<Game> games = new ArrayList<>();
    KigawaPlugin plugin;

    public GameManager(KigawaPlugin kigawaPlugin) {
        plugin = kigawaPlugin;

        File folder = new File(plugin.getDataFolder(), "onigo");
        folder.mkdir();
        String[] files = folder.list();
        for (int i = 0; i < files.length; i++) {
            File file = new File(folder, files[i]);
            plugin.logger(files[i]);
            OnigoData data = plugin.getRecorder().load(OnigoData.class, "onigo", files[i].substring(0, files[i].length() - 4));
            games.add(new OnigoGame(plugin, data));
        }
    }

    public void end(String gameName, CommandSender sender) {
        Game game = getGame(gameName, sender);
        if (game != null) {
            game.end();
        }
    }

    public void setEndLoc(String gameName, CommandSender sender, String worldName, int x, int y, int z) {
        Game game = getGame(gameName, sender);
        if (game != null) {
            game.setEndLoc(worldName, x, y, z);
        }
    }

    public void setGameTime(String gameName, CommandSender sender, int gameTime) {
        Game game = getGame(gameName, sender);
        if (game != null) {
            game.setGameTime(gameTime);
        }
    }

    public void setWaitTime(String gameName, CommandSender sender, int waitTime) {
        Game game = getGame(gameName, sender);
        if (game != null) {
            game.setWaitTime(waitTime);
        }
    }

    public void setOniCount(String gameName, CommandSender sender, int oniCount) {
        Game game = getGame(gameName, sender);
        if (game != null) {
            game.setOniCount(oniCount);
        }
    }

    public void start(String gameName, CommandSender sender) {
        Game game = getGame(gameName, sender);
        if (game != null) {
            game.start(sender);
        }
    }

    public Game getGame(String gameName, CommandSender sender) {
        if (games.contains(new EqualsYamlData(gameName))) {
            return games.get(games.indexOf(new EqualsYamlData(gameName)));
        } else {
            sender.sendMessage(gameName + " is not exit");
            return null;
        }
    }

    public void setWaitRoom2(String gameName, int x, int y, int z, CommandSender sender) {
        if (games.contains(new EqualsYamlData(gameName))) {
            games.get(games.indexOf(new EqualsYamlData(gameName))).setWaitingRoom2(x, y, z);
        } else {
            sender.sendMessage(gameName + " is not exit");
        }
    }

    public void setWaitRoom1(String gameName, String worldName, int x, int y, int z, CommandSender sender) {
        if (games.contains(new EqualsYamlData(gameName))) {
            games.get(games.indexOf(new EqualsYamlData(gameName))).setWaitingRoom1(worldName, x, y, z);
        } else {
            sender.sendMessage(gameName + " is not exit");
        }
    }

    public void createGame(CommandSender sender, String name) {
        if (!games.contains(new EqualsYamlData(name))) {
            OnigoData data = new OnigoData();
            data.setFolder("onigo");
            data.setName(name);
            getGames().add(new OnigoGame(plugin, data));
            plugin.getRecorder().save(data);
        } else {
            sender.sendMessage("this name can't use");
        }

    }

    public List<Game> getGames() {
        return games;
    }
}
