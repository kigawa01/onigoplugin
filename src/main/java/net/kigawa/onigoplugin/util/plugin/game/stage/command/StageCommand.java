package net.kigawa.onigoplugin.util.plugin.game.stage.command;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.util.plugin.all.command.FirstCommand;
import net.kigawa.onigoplugin.util.plugin.game.stage.StageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class StageCommand extends FirstCommand
{
  OnigoPlugin plugin;


  public StageCommand(OnigoPlugin plugin, StageManager stageManager) {
    super(plugin);
    this.plugin = plugin;
    addSubcommands(new CreateCommand(plugin, stageManager));
    addSubcommands(new SetStage1(plugin, stageManager));
    addSubcommands(new SetStage2(plugin, stageManager));
    addSubcommands(new SetStartLoc(plugin, stageManager));
  }


  @Override
  public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
    return true;
  }

  @Override
  public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
    return false;
  }
}
