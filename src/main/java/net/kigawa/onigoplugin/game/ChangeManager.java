package net.kigawa.onigoplugin.game;

import net.kigawa.kutil.unitapi.annotation.Kunit;
import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.util.all.EqualsNamed;
import net.kigawa.onigoplugin.util.plugin.all.player.PlayerGetter;
import net.kigawa.onigoplugin.util.plugin.all.recorder.Recorder;
import net.kigawa.onigoplugin.util.plugin.game.onigo.Game;
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameData;
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameManager;
import net.kigawa.onigoplugin.util.plugin.game.stage.StageManager;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

@Kunit
public class ChangeManager extends GameManager
{
  private final StageManager stageManager;
  private final PlayerGetter playerGetter;

  public ChangeManager(OnigoPlugin OnigoPlugin, Recorder recorder, StageManager stageManager, PlayerGetter playerGetter) {
    super(OnigoPlugin, "change", recorder);
    this.stageManager = stageManager;
    this.playerGetter = playerGetter;
    games = initializeGame(recorder.loadAll(GameData.class, getName()));
  }

  @Override
  public List<Game> initializeGame(List<GameData> data) {
    List<Game> games = new ArrayList<>();

    //take out data list
    for (GameData gameData : data) {
      games.add(new OnigoGame(plugin, gameData, this, recorder, stageManager, playerGetter));
    }
    return games;
  }

  @Override
  public void createGame(CommandSender sender, String name) {
    if (!games.contains(new EqualsNamed(name))) {
      OnigoData data = new OnigoData();
      data.setName(name);
      games.add(new OnigoGame(plugin, data, this, recorder, stageManager, playerGetter));
      recorder.save(data, getName());
    } else {
      sender.sendMessage("this name can't use");
    }
  }
}
