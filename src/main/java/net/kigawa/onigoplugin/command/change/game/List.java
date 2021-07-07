package net.kigawa.onigoplugin.command.change.game;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.game.change.OnigoGame;
import net.kigawa.util.plugin.command.SubCommand;
import net.kigawa.util.plugin.command.TabList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class List extends SubCommand {
    OnigoPlugin plugin;
    public List(OnigoPlugin onigoPlugin) {
        super(onigoPlugin);
        plugin=onigoPlugin;
    }



    @Override
    public String getCommandStr() {
        return "list";
    }

    @Override
    public boolean onAlways(CommandSender commandSender, Command command, String s, String[] strings) {
        return true;
    }

    @Override
    public boolean onNotFound(CommandSender commandSender, Command command, String s, String[] strings) {
        java.util.List<OnigoGame> onigoList=plugin.getOnigoManager().getOnigoList();
        for (int i=0;i<onigoList.size();i++){
            commandSender.sendMessage("name "+onigoList.get(i).getName());
            commandSender.sendMessage(" world "+onigoList.get(i).getD().getWaitRoomWorld());
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }

    @Override
    public java.util.List<SubCommand> getCommandList() {
        return null;
    }
}
