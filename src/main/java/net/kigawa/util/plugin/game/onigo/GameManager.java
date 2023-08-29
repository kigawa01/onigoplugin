package net.kigawa.util.plugin.game.onigo;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.all.EqualsNamed;
import net.kigawa.util.all.Named;
import net.kigawa.util.plugin.game.onigo.list.EqualsOnigoManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class GameManager implements Onigo, Named
{
  protected List<Game> games = new ArrayList<>();
  protected OnigoPlugin plugin;
  String name;

  public GameManager(OnigoPlugin OnigoPlugin, String name) {
    plugin = OnigoPlugin;
    this.name = name;

    File folder = new File(plugin.getDataFolder(), getName());
    folder.mkdir();
    String[] files = folder.list();
    games = initializeGame(plugin.recorder.loadAll(GameData.class, getName()));
  }

  public abstract List<Game> initializeGame(List<GameData> data);

  public String getName() {
    return name;
  }


  public OnigoPlugin getPlugin() {
    return plugin;
  }

  public List<Game> getGames() {
    return games;
  }

  @Override
  public boolean contain(HumanEntity player) {
    if (games != null) return games.contains(new EqualsOnigoManager(player));
    return false;
  }


  public boolean changeOni(Player oni, Player runner) {
    if (games != null) {
      return games.contains(new EqualsOnigoManager(oni, runner));
    }
    return false;
  }

  public void end(String gameName, CommandSender sender) {
    Game game = getGame(gameName);
    if (game != null) {
      game.end();
    }
  }

  public void endAll() {
    for (Game game : games) {
      game.end();
    }
  }

  public void setEndLoc(String gameName, CommandSender sender, String worldName, int x, int y, int z) {
    Game game = getGame(gameName);
    if (game != null) {
      game.setEndLoc(worldName, x, y, z);
    }
  }

  public void setGameTime(String gameName, CommandSender sender, int gameTime) {
    Game game = getGame(gameName);
    if (game != null) {
      game.setGameTime(gameTime);
    }
  }

  public void setGameType(String gameName, CommandSender sender, String gameType) {
    Game game = getGame(gameName);
    if (game != null) {
      game.setGameType(gameType);
    }
  }

  public void setWaitTime(String gameName, CommandSender sender, int waitTime) {
    Game game = getGame(gameName);
    if (game != null) {
      game.setWaitTime(waitTime);
    }
  }

  public void setOniCount(String gameName, CommandSender sender, int oniCount) {
    Game game = getGame(gameName);
    if (game != null) {
      game.setOniCount(oniCount);
    }
  }


  public Game getGame(String gameName) {
    if (games.contains(new EqualsNamed(gameName))) {
      return games.get(games.indexOf(new EqualsNamed(gameName)));
    } else {
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

  public void setOniWait1(String gameName, String worldName, int x, int y, int z, CommandSender sender) {
    if (games.contains(new EqualsNamed(gameName))) {
      games.get(games.indexOf(new EqualsNamed(gameName))).setOniWait1(worldName, x, y, z);
    } else {
      sender.sendMessage(gameName + " is not exit");
    }
  }

  public void setOniWait2(String gameName, int x, int y, int z, CommandSender sender) {
    if (games.contains(new EqualsNamed(gameName))) {
      games.get(games.indexOf(new EqualsNamed(gameName))).setOniWait2(x, y, z);
    } else {
      sender.sendMessage(gameName + " is not exit");
    }
  }

  public abstract void createGame(CommandSender sender, String name);


  public void start(String gameName, CommandSender sender, String stageName) {
    Game game = getGame(gameName);
    if (game != null) {
      game.start(sender, stageName);
    }
  }
}
