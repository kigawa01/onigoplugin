package net.kigawa.utilplugin.Command;

import net.kigawa.utilplugin.list.ForEquals;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import javax.swing.*;

public abstract class SubCommand {
    String name;
    String message;
    public SubCommand(String name,String message){
        this.name=name;
        this.message=message;
    }
    public abstract boolean onSubCommand(CommandSender sender,Command command,String label,String[] args);
    public boolean equals(Object o){
        if (o instanceof ForEquals){
            return name.equals(o);
        }
        return false;
    }
}
