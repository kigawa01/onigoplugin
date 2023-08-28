package net.kigawa.util.plugin.game.stage.command;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.all.command.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CreateCommand extends SubCommand {
    public CreateCommand(OnigoPlugin OnigoPlugin) {
        super(OnigoPlugin);
        plugin = OnigoPlugin;
    }

    OnigoPlugin plugin;

    @Override
    public String getName() {
        return "create";
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 2) {
            //set stage
            plugin.stageManager.setStage(strings[1], commandSender);
        } else {
            //send error
            commandSender.sendMessage("/stage create <name>");
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }
}
