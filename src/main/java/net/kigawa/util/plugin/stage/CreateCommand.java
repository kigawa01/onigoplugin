package net.kigawa.util.plugin.stage;

import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.command.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CreateCommand extends SubCommand {
    public CreateCommand(KigawaPlugin kigawaPlugin) {
        super(kigawaPlugin);
        plugin=kigawaPlugin;
    }
KigawaPlugin plugin;
    @Override
    public String getCommandStr() {
        return "create";
    }

    @Override
    public void addSubcommands(List<SubCommand> subCommands) {

    }

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length==2){
            //set stage
            plugin.getStageManager().setStage(strings[1],commandSender);
        }else {
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
