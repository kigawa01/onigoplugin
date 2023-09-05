package net.kigawa.onigoplugin.util.plugin.game.stage.command;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.util.plugin.all.command.SubCommand;
import net.kigawa.onigoplugin.util.plugin.game.stage.StageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CreateCommand extends SubCommand
{
  OnigoPlugin plugin;
  private StageManager stageManager;

  public CreateCommand(OnigoPlugin OnigoPlugin, StageManager stageManager) {
    super(OnigoPlugin);
    plugin = OnigoPlugin;
    this.stageManager = stageManager;
  }

  @Override
  public String getName() {
    return "create";
  }


  @Override
  public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
    return true;
  }

  @Override
  public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
    if (strings.length == 2) {
      //set stage
      stageManager.setStage(strings[1], commandSender);
    } else {
      //send error
      commandSender.sendMessage("/stage create <name>");
    }
    return true;
  }

  @Override
  public int getWordNumber() {
    return 0;
  }
}
