package net.kigawa.util.plugin.game.stage.command;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.all.command.FirstCommand;
import net.kigawa.util.plugin.game.stage.command.CreateCommand;
import net.kigawa.util.plugin.game.stage.command.SetStage1;
import net.kigawa.util.plugin.game.stage.command.SetStage2;
import net.kigawa.util.plugin.game.stage.command.SetStartLoc;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class StageCommand extends FirstCommand {
    KigawaPlugin plugin;


    public StageCommand(KigawaPlugin plugin) {
        super(plugin);
        this.plugin=plugin;
        addSubcommands(new CreateCommand(plugin));
        addSubcommands(new SetStage1(plugin));
        addSubcommands(new SetStage2(plugin));
        addSubcommands(new SetStartLoc(plugin));
    }


    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }
}
