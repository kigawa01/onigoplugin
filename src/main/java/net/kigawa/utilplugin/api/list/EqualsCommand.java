package net.kigawa.utilplugin.api.list;

import net.kigawa.utilplugin.api.command.SubCommand;

public class EqualsCommand {
    String command;
    public EqualsCommand(String command){
        this.command=command;
    }
    @Override
    public  boolean equals(Object o){
        return  ((SubCommand)o).getCommandStr().equals(command);
    }
}
