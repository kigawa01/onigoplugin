package net.kigawa.util.plugin.list;

import net.kigawa.util.plugin.command.SubCommand;

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
