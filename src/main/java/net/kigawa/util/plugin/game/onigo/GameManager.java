package net.kigawa.util.plugin.game.onigo;

import net.kigawa.onigoplugin.game.change.OnigoData;
import net.kigawa.onigoplugin.game.change.OnigoGame;
import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.ist.EqualsOniChange;
import net.kigawa.util.yaml.EqualsYamlData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class GameManager implements Onigo {
    List<Game> games = new ArrayList<>();
    KigawaPlugin plugin;
    String managerName;

    public GameManager(KigawaPlugin kigawaPlugin,String name) {
        plugin = kigawaPlugin;

        File folder = new File(plugin.getDataFolder(), name);
        folder.mkdir();
        String[] files = folder.list();
        for (int i = 0; i < files.length; i++) {
            File file = new File(folder, files[i]);
            plugin.logger(files[i]);
            OnigoData data = plugin.getRecorder().load(OnigoData.class, name, files[i].substring(0, files[i].length() - 4));
            games.add(new OnigoGame(plugin, data,this));
        }
    }

    public KigawaPlugin getPlugin() {
        return plugin;
    }

    public String getManagerName() {
        return managerName;
    }

    public boolean changeOni(Player oni, Player runner) {
        return games.contains(new EqualsOniChange(oni, runner));
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

    public abstract void createGame(CommandSender sender, String name) ;

    public List<Game> getGames() {
        return games;
    }
}
