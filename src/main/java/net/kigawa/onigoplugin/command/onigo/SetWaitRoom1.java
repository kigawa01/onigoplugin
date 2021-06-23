package net.kigawa.onigoplugin.command.onigo;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.command.SubCommand;
import net.kigawa.util.plugin.plugin.KigawaPlugin;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetWaitRoom1 extends SubCommand {
    OnigoPlugin plugin;
    public SetWaitRoom1(OnigoPlugin plugin) {
        super(plugin);
        this.plugin=plugin;
    }

    @Override
    public String getCommandStr() {
        return "setwaitroom1";
    }

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length!=5){
            commandSender.sendMessage("/onigocreate setwaitroom2 <game name> <x> <y> <z>");
        }
        if (commandSender instanceof Player |commandSender instanceof BlockCommandSender) {
            World world=null;
            if (commandSender instanceof Player){
                world=((Player)commandSender).getWorld();
            }if (commandSender instanceof BlockCommandSender){
                world=((BlockCommandSender)commandSender).getBlock().getWorld();
            }
            plugin.getOnigoManager().setWaitRoom1(strings[1],world.getName(),Integer.valueOf(strings[2]),Integer.valueOf(strings[3]),Integer.valueOf(strings[4]),commandSender);
        }else {
            commandSender.sendMessage("this command can use by player or commandBlock");
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }

    @Override
    public List<SubCommand> getCommandList() {
        return null;
    }
}
