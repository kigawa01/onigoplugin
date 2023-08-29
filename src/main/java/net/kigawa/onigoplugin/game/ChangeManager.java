package net.kigawa.onigoplugin.game;

import net.kigawa.kutil.unitapi.annotation.Kunit;
import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.all.EqualsNamed;
import net.kigawa.util.plugin.game.onigo.Game;
import net.kigawa.util.plugin.game.onigo.GameData;
import net.kigawa.util.plugin.game.onigo.GameManager;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

@Kunit
public class ChangeManager extends GameManager
{

  public ChangeManager(OnigoPlugin OnigoPlugin, String name) {
    super(OnigoPlugin, name);
  }

  @Override
  public List<Game> initializeGame(List<GameData> data) {
    List<Game> games = new ArrayList<>();

    //take out data list
    for (GameData gameData : data) {
      games.add(new OnigoGame(plugin, gameData, this));
    }
    return games;
  }

  @Override
  public void createGame(CommandSender sender, String name) {
    if (!games.contains(new EqualsNamed(name))) {
      OnigoData data = new OnigoData();
      data.setName(name);
      games.add(new OnigoGame(plugin, data, this));
      plugin.recorder.save(data, getName());
    } else {
      sender.sendMessage("this name can't use");
    }
  }
}
