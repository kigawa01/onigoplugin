package net.kigawa.onigoplugin.util.plugin.game.stage.command;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.util.plugin.all.command.SubCommand;
import net.kigawa.onigoplugin.util.plugin.game.stage.StageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SetStartLoc extends SubCommand
{
  OnigoPlugin plugin;
  private StageManager stageManager;

  public SetStartLoc(OnigoPlugin OnigoPlugin, StageManager stageManager) {
    super(OnigoPlugin);
    plugin = OnigoPlugin;
    this.stageManager = stageManager;
  }

  @Override
  public String getName() {
    return "setstartloc";
  }


  @Override
  public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
    return true;
  }

  @Override
  public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
    //check args
    if (strings.length == 5) {
      //set start loc
      stageManager.setStartLoc(strings[1], Integer.valueOf(strings[2]), Integer.valueOf(strings[3]), Integer.valueOf(strings[4]),
          commandSender);
      //send message
      commandSender.sendMessage("set start loc");
    } else {
      commandSender.sendMessage("/stage setstartloc <game name> <x> <y> <z>");
    }
    return true;
  }

  @Override
  public int getWordNumber() {
    return 0;
  }
}
