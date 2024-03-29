package net.kigawa.onigoplugin.util.plugin.all.command;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.util.plugin.all.message.PluginLogger;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class TabList extends PluginLogger
{
  List<TabList> tabLists;


  public TabList(OnigoPlugin OnigoPlugin) {
    super(OnigoPlugin);
    List<TabList> tabLists;
    tabLists = new ArrayList<>();
    this.tabLists = tabLists;
  }

  public abstract int getWordNumber();

  public abstract String getName();

  public void addTabLists(TabList tabList) {
    tabLists.add(tabList);
  }

  public List<String> tabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
    //new instance
    List<String> tabListStr = new ArrayList<>();
    //check null
    logger("check null");
    if (tabLists != null) {
      //check null
      if (tabLists != null) {
        //when send here
        if (strings.length == getWordNumber() + 1) {
          logger("when send here");
          for (TabList tabList : tabLists) {
            tabListStr.add(tabList.getName());
          }
        }


        //when do not send here
        if (strings.length > getWordNumber() + 1) {
          logger("when do not send here");
          //check contain tabList
          if (tabLists.contains(new EqualsCommand(strings[getWordNumber() + 1]))) {
            TabList tabList = tabLists.get(tabLists.indexOf(new EqualsCommand(strings[getWordNumber() + 1])));
            tabListStr = tabList.tabComplete(commandSender, command, s, strings);
          }
        }
      }
    }
    return tabListStr;
  }

}
