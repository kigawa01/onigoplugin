package net.kigawa.utilplugin.api.command;


import net.kigawa.utilplugin.api.list.ForEquals;
import net.kigawa.utilplugin.api.plugin.KigawaPlugin;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class LastCommand implements Command {
    KigawaPlugin plugin;
    public LastCommand(KigawaPlugin kigawaPlugin){
        plugin=kigawaPlugin;
    }
    public abstract boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings);

    @Override
    public boolean equals(Object o) {
        plugin.logger(getCommandStr()+" onEquals");
        if (o instanceof ForEquals) {
            ForEquals forEquals = (ForEquals) o;
            if (forEquals.getType().equals("command")) {
                return getCommandStr().equals(forEquals.getObject());
            }
        }
        return false;
    }

    public abstract int getWordNumber();
    public List<String> getCommandsStr(String[] strings){
            List<String> forSend = new ArrayList<>();
            if (strings.length==getWordNumber()){
                for(int i=0;i<getCommandList().size();i++){
                    forSend.add(getCommandList().get(i).getCommandStr());
                }
                return forSend;
            }
            if (strings.length>getWordNumber()){
                if(getCommandList().contains(new ForEquals("command",strings[getWordNumber()]))){
                    LastCommand subCommand= getCommandList().get(getCommandList().indexOf(new ForEquals("command",strings[getWordNumber()])));

                }
            }
            return null;
    }
    public abstract List<LastCommand> getCommandList();
}
