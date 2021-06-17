package net.kigawa.utilplugin.api.command;

import net.kigawa.utilplugin.api.list.ForEquals;
import net.kigawa.utilplugin.api.plugin.KigawaPlugin;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class MiddleCommand extends SubCommand{
    @Override
    public boolean onCommand(
            CommandSender commandSender, org.bukkit.command.Command command,
            String s, String[] strings){
        if(!onAlways(commandSender,command,s,strings)) {
            return false;
        }
        if(getCommandList().contains(new ForEquals("command",strings[getWordNumber()]))){
            SubCommand subCommand=getCommandList().get(getCommandList().indexOf(new ForEquals("command",strings[getWordNumber()])));
            return subCommand.onCommand(commandSender,command,s,strings);
        }
        return onNotFound(commandSender,command,s,strings);
    }
    public abstract boolean onAlways(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings);
    public abstract boolean onNotFound(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings);
}
