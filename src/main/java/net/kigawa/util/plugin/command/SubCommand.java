package net.kigawa.util.plugin.command;

import net.kigawa.util.plugin.list.EqualsCommand;
import net.kigawa.util.plugin.plugin.KigawaPlugin;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class SubCommand implements Command {
    KigawaPlugin plugin;
    List<SubCommand> subCommands;
    public SubCommand(KigawaPlugin kigawaPlugin){
        plugin=kigawaPlugin;
    }
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings){
        plugin.logger(getCommandStr()+" onAlways");
        if(!onAlways(commandSender,command,s,strings)) {
            return false;
        }
        if (getCommandList()!=null) {
            if (getCommandList().contains(new EqualsCommand(strings[getWordNumber()]))) {
                SubCommand subCommand = getCommandList().get(getCommandList().indexOf(new EqualsCommand(strings[getWordNumber()])));
                return subCommand.onCommand(commandSender, command, s, strings);
            }
        }
        plugin.logger(getCommandStr()+" onNotFound");
        return onNotFound(commandSender,command,s,strings);
    }
    public abstract boolean onAlways(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings);
    public abstract boolean onNotFound(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings);


    public abstract int getWordNumber();
    public List<String> getCommandsStr(String[] strings){
        List<String> forSend = new ArrayList<>();
        if (strings.length==getWordNumber()+1){
            if (getCommandList()!=null) {
                for (int i = 0; i < getCommandList().size(); i++) {
                    forSend.add(getCommandList().get(i).getCommandStr());
                }
            }
            return forSend;
        }
        if (strings.length>getWordNumber()+1){
            if (getCommandList()!=null) {
                if (getCommandList().contains(new EqualsCommand(strings[getWordNumber()]))) {
                    SubCommand subCommand = getCommandList().get(getCommandList().indexOf(new EqualsCommand(strings[getWordNumber()])));

                }
            }
        }
        return null;
    }
    public abstract List<SubCommand> getCommandList();
}
