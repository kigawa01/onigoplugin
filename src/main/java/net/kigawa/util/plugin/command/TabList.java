package net.kigawa.util.plugin.command;

import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.list.EqualsCommand;
import net.kigawa.util.plugin.message.PluginLogger;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class TabList extends PluginLogger {
    List<TabList> tabLists;

    public abstract void addTabLists(List<TabList> tabLists);

    public abstract String getCommandStr();
    public abstract int getWordNumber();

    public TabList(KigawaPlugin kigawaPlugin){
        super(kigawaPlugin);
        List<TabList> tabLists;
        tabLists= new ArrayList<>();
        addTabLists(tabLists);
        this.tabLists=tabLists;
    }

    public List<String> tabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        //new instance
        List<String> tabListStr = new ArrayList<>();
        //check null
        logger("check null");
        if (tabLists!=null) {
            //check null
            if (tabLists!=null) {
                //when send here
                if (strings.length == getWordNumber() + 1) {
                    logger("when send here");
                    for (TabList tabList : tabLists) {
                        tabListStr.add(tabList.getCommandStr());
                    }
                }


                //when do not send here
                if (strings.length > getWordNumber() + 1) {
                    logger("when do not send here");
                    //check contain tabList
                    if (tabLists.contains(new EqualsCommand(strings[getWordNumber()+1]))) {
                        TabList tabList = tabLists.get(tabLists.indexOf(new EqualsCommand(strings[getWordNumber() + 1])));
                        tabListStr = tabList.tabComplete(commandSender, command, s, strings);
                    }
                }
            }
        }
        return tabListStr;
    }

}
