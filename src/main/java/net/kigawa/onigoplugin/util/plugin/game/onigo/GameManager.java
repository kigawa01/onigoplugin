package net.kigawa.onigoplugin.util.plugin.game.onigo;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.util.all.EqualsNamed;
import net.kigawa.onigoplugin.util.all.Named;
import net.kigawa.onigoplugin.util.plugin.all.recorder.Recorder;
import net.kigawa.onigoplugin.util.plugin.game.onigo.list.EqualsOnigoManager;
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
  protected Recorder recorder;
  String name;

  public GameManager(OnigoPlugin OnigoPlugin, String name, Recorder recorder) {
    plugin = OnigoPlugin;
    this.recorder = recorder;
    this.name = name;

    File folder = new File(plugin.getDataFolder(), getName());
    folder.mkdir();
    games = initializeGame(recorder.loadAll(GameData.class, getName()));
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

  public void end(String gameName) {
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

  public void setGameType(String gameName, String gameType) {
    Game game = getGame(gameName);
    if (game != null) {
      game.setGameType(gameType);
    }
  }


  public Game getGame(String gameName) {
    if (games.contains(new EqualsNamed(gameName))) {
      return games.get(games.indexOf(new EqualsNamed(gameName)));
    } else {
      return null;
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

  public boolean containGame(String name) {
    return !games.stream().filter((game)->game.getName().equals(name)).toList().isEmpty();
  }
}
