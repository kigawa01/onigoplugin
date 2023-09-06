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

  public void endAll() {
    for (Game game : games) {
      game.end();
    }
  }


  public Game getGame(String gameName) {
    if (games.contains(new EqualsNamed(gameName))) {
      return games.get(games.indexOf(new EqualsNamed(gameName)));
    } else {
      return null;
    }
  }

  public abstract void createGame(CommandSender sender, String name);


  public boolean containGame(String name) {
    return !games.stream().filter((game)->game.getName().equals(name)).toList().isEmpty();
  }
}
