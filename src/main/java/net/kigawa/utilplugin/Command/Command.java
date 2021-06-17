package net.kigawa.utilplugin.Command;

import net.kigawa.utilplugin.list.ForEquals;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.List;

public abstract class Command implements CommandExecutor {
    List<SubCommand> subCommands;
    JavaPlugin plugin;
    public Command(JavaPlugin plugin){
        this.plugin=plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (onMainCommand(sender,command,label,args)) {
            if (subCommands.contains(new ForEquals(label))) {
                return subCommands.get(subCommands.indexOf(new ForEquals(label))).onSubCommand(sender,command,label,args);
            }else {
                sendMessage(sender);
                return false;
            }
        }
        return false;
    }
    public abstract boolean onMainCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args);
    public List<SubCommand> getSubCommands(){
        return subCommands;
    }
    public void sendMessage(CommandSender sender){
        new BukkitRunnable(){

            @Override
            public void run() {
                for (SubCommand subCommand : subCommands) {
                    sender.sendMessage(subCommand.message);
                }
            }
        }.runTaskLater(plugin,1);
    }
}
