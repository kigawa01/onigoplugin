package net.kigawa.onigoplugin.command.onigo.create;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.command.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CreateGame extends SubCommand {
    OnigoPlugin plugin;
    public CreateGame(OnigoPlugin onigoPlugin) {
        super(onigoPlugin);
        this.plugin=onigoPlugin;
    }

    @Override
    public String getCommandStr() {
        return "create";
    }

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length==2){
            plugin.getOnigoManager().createOnigo(commandSender,strings[1]);
            return true;
        }else {
            commandSender.sendMessage("/onigocreate cretate <name>");
            return true;
        }
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
