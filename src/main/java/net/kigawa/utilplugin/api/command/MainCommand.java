package net.kigawa.utilplugin.api.command;

import net.kigawa.utilplugin.api.list.ForEquals;
import net.kigawa.utilplugin.api.plugin.KigawaPlugin;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public abstract class MainCommand implements CommandExecutor,Command  {
    KigawaPlugin plugin;
    public MainCommand(KigawaPlugin plugin) {
        this.plugin=plugin;

        plugin.getCommand(getCommandStr()).setExecutor(this);
        plugin.getCommand(getCommandStr()).setTabCompleter(new TabCompleter() {
            @Override
            public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
                List<String> forSend = new ArrayList<>();
                if (strings.length==0){
                    for(int i=0;i<getCommandList().size();i++){
                        forSend.add(getCommandList().get(i).getCommandStr());
                    }
                    return forSend;
                }
                if (strings.length>0){
                    if(getCommandList().contains(new ForEquals("command",strings[0]))){
                        LastCommand subCommand= getCommandList().get(getCommandList().indexOf(new ForEquals("command",strings[0])));
                        return subCommand.getCommandsStr(strings);
                    }
                }
                return null;
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
            if (getCommandList().contains(new ForEquals("command", strings[0]))) {
                LastCommand subCommand = getCommandList().get(getCommandList().indexOf(new ForEquals("command", strings[0])));
                return subCommand.onCommand(commandSender, command, s, strings);
            }
        }
        plugin.logger(getCommandStr()+" onNotFound");
        return onNotFound(commandSender,command,s,strings);
    }
    public abstract boolean onAlways(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings);
    public abstract boolean onNotFound(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings);
    public abstract List<LastCommand> getCommandList();

}
