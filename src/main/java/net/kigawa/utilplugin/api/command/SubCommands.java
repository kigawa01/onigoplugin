package net.kigawa.utilplugin.api.command;

import net.kigawa.utilplugin.api.list.ForEquals;
import net.kigawa.utilplugin.api.plugin.KigawaPlugin;
import org.bukkit.command.CommandSender;

public abstract class SubCommands extends LastCommand {
    KigawaPlugin plugin;
    public SubCommands(KigawaPlugin kigawaPlugin){
        super(kigawaPlugin);
        plugin=kigawaPlugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings){
        plugin.logger(getCommandStr()+" onAlways");
        if(!onAlways(commandSender,command,s,strings)) {
            return false;
        }
        if(getCommandList().contains(new ForEquals("command",strings[getWordNumber()]))){
            LastCommand subCommand=getCommandList().get(getCommandList().indexOf(new ForEquals("command",strings[getWordNumber()])));
            return subCommand.onCommand(commandSender,command,s,strings);
        }
        plugin.logger(getCommandStr()+" onNotFound");
        return onNotFound(commandSender,command,s,strings);
    }
    public abstract boolean onAlways(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings);
    public abstract boolean onNotFound(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings);
}
