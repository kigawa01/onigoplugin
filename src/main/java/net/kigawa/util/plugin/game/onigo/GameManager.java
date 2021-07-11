package net.kigawa.util.plugin.game.onigo;

import net.kigawa.util.all.EqualsNamed;
import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.game.onigo.list.EqualsOniChange;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class GameManager implements Onigo {
    List<Game> games = new ArrayList<>();
    KigawaPlugin plugin;
    String Name;

    public GameManager(KigawaPlugin kigawaPlugin, String name) {
        plugin = kigawaPlugin;
        this.Name = name;

        File folder = new File(plugin.getDataFolder(), getName());
        folder.mkdir();
        String[] files = folder.list();
        games = initializeGame(plugin.getRecorder().loadAll(GameData.class, getName()));
    }

    @Override
    public String getName() {
        return Name;
    }

    public abstract List<Game> initializeGame(List<GameData> data);

    public KigawaPlugin getPlugin() {
        return plugin;
    }


    public boolean changeOni(Player oni, Player runner) {
        if (games!=null) {
            return games.contains(new EqualsOniChange(oni, runner));
        }
        return false;
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
        if (games.contains(new EqualsNamed(gameName))) {
            return games.get(games.indexOf(new EqualsNamed(gameName)));
        } else {
            sender.sendMessage(gameName + " is not exit");
            return null;
        }
    }

    public void setWaitRoom2(String gameName, int x, int y, int z, CommandSender sender) {
        if (games.contains(new EqualsNamed(gameName))) {
            games.get(games.indexOf(new EqualsNamed(gameName))).setWaitingRoom2(x, y, z);
        } else {
            sender.sendMessage(gameName + " is not exit");
        }
    }

    public void setWaitRoom1(String gameName, String worldName, int x, int y, int z, CommandSender sender) {
        if (games.contains(new EqualsNamed(gameName))) {
            games.get(games.indexOf(new EqualsNamed(gameName))).setWaitingRoom1(worldName, x, y, z);
        } else {
            sender.sendMessage(gameName + " is not exit");
        }
    }

    public abstract void createGame(CommandSender sender, String name);

    public List<Game> getGames() {
        return games;
    }
}
