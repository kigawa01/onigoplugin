package net.kigawa.onigoplugin.util.plugin.all.command;

import net.kigawa.onigoplugin.OnigoPlugin;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class SubCommand extends TabList
{
  OnigoPlugin plugin;
  List<SubCommand> subCommands;


  public SubCommand(OnigoPlugin OnigoPlugin) {
    super(OnigoPlugin);
    plugin = OnigoPlugin;

    subCommands = new ArrayList<>();
  }

  public abstract boolean onAlways(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings);

  public abstract boolean onNotFound(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings);

  public abstract int getWordNumber();

  public void addSubcommands(SubCommand subCommand) {
    subCommands.add(subCommand);
    addTabLists(subCommand);
  }

  public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
    plugin.logger(getName() + " onAlways");
    if (!onAlways(commandSender, command, s, strings)) {
      return false;
    }


    if (subCommands != null) {
      if (subCommands.contains(new EqualsCommand(strings[getWordNumber()]))) {
        SubCommand subCommand = subCommands.get(subCommands.indexOf(new EqualsCommand(strings[getWordNumber()])));
        return subCommand.onCommand(commandSender, command, s, strings);
      }
    }


    plugin.logger(getName() + " onNotFound");
    return onNotFound(commandSender, command, s, strings);
  }


  public List<String> getCommandsStr(String[] strings) {
    List<String> forSend = new ArrayList<>();
    if (strings.length == getWordNumber() + 1) {
      if (subCommands != null) {
        for (int i = 0; i < subCommands.size(); i++) {
          forSend.add(subCommands.get(i).getName());
        }
      }
      return forSend;
    }
    if (strings.length > getWordNumber() + 1) {
      if (subCommands != null) {
        if (subCommands.contains(new EqualsCommand(strings[getWordNumber()]))) {
          SubCommand subCommand = subCommands.get(subCommands.indexOf(new EqualsCommand(strings[getWordNumber()])));

        }
      }
    }
    return null;
  }
}
