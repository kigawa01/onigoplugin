package net.kigawa.onigoplugin.command;

import net.kigawa.kutil.unitapi.annotation.Kunit;
import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.util.plugin.game.stage.StageManager;
import net.kigawa.onigoplugin.util.plugin.game.stage.command.StageCommand;

@Kunit
public class Stage extends StageCommand
{
  public Stage(OnigoPlugin plugin, StageManager stageManager) {
    super(plugin, stageManager);
  }


  @Override
  public String getName() {
    return "stage";
  }
}
