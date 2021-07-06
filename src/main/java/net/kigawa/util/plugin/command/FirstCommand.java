package net.kigawa.util.plugin.command;

import net.kigawa.util.plugin.list.EqualsCommand;
import net.kigawa.util.plugin.KigawaPlugin;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public abstract class FirstCommand extends TabList implements CommandExecutor {
    KigawaPlugin plugin;
    List<SubCommand> commandList;

    public FirstCommand(KigawaPlugin plugin) {
        super(plugin);
        this.plugin=plugin;

        plugin.getCommand(getCommandStr()).setExecutor(this);
        plugin.getCommand(getCommandStr()).setTabCompleter(new TabCompleter() {
            @Override
            public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
                return tabComplete(commandSender,command,s,strings);
            }
        }
        );
    }


    @Override
    public boolean onCommand(
            CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings){
        plugin.logger(getCommandStr()+" onAlways");
        if(!onAlways(commandSender,command,s,strings)) {
         return false;
        }
        if (strings.length>0) {

            plugin.logger(getCommandStr()+" onIsContain");

            if (getCommandList()!=null) {
                if (getCommandList().contains(new EqualsCommand(strings[0]))) {
                    plugin.logger(getCommandStr() + " onGetSubCommand");
                    SubCommand subCommand = getCommandList().get(getCommandList().indexOf(new EqualsCommand(strings[0])));
                    return subCommand.onCommand(commandSender, command, s, strings);
                }
            }
        }
        plugin.logger(getCommandStr()+" onNotFound");
        return onNotFound(commandSender,command,s,strings);
    }


    @Override
    public void addTabLists(java.util.List<TabList> tabLists) {
        commandList=new ArrayList<>();;
        addSubcommands(commandList);
        tabLists.addAll(commandList);
    }

    @Override
    public int getWordNumber() {
        return 0;
    }


    public abstract void addSubcommands(List<SubCommand> subCommands);

    public abstract boolean onAlways(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings);
    public abstract boolean onNotFound(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings);

    public abstract List<SubCommand> getCommandList();
}
