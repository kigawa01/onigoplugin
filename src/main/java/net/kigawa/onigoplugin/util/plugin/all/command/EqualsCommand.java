package net.kigawa.onigoplugin.util.plugin.all.command;

public class EqualsCommand {
    String command;
    public EqualsCommand(String command){
        this.command=command;
    }
    @Override
    public  boolean equals(Object o){
        return  ((SubCommand)o).getName().equals(command);
    }
}
