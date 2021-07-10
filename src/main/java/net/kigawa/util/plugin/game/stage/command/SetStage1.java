package net.kigawa.util.plugin.game.stage.command;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.all.command.SubCommand;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetStage1 extends SubCommand {
    KigawaPlugin plugin;

    public SetStage1(KigawaPlugin kigawaPlugin) {
        super(kigawaPlugin);
        plugin = kigawaPlugin;
    }

    @Override
    public String getName() {
        return "setstage1";
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 5) {
            if (commandSender instanceof Player | commandSender instanceof BlockCommandSender) {
                World world;
                if (commandSender instanceof Player) {
                    world = ((Player) commandSender).getWorld();
                } else {
                    world = ((BlockCommandSender) commandSender).getBlock().getWorld();
                }
                plugin.getStageManager().setStage1(strings[1], world.getName(),
                        Integer.valueOf(strings[2]), Integer.valueOf(strings[3]), Integer.valueOf(strings[4]), commandSender);
                commandSender.sendMessage("set start point of stage");
            }

        } else {
            commandSender.sendMessage("/stage setstage1 <stage name> <x> <y> <z>");
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }
}
