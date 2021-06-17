package net.kigawa.utilplugin.gate;

import net.kigawa.utilplugin.data.DataTask;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Gate {
    JavaPlugin plugin;
    public List<DataTask> gateTaskList;
    public Gate(JavaPlugin plugin){
        this.plugin=plugin;
    }
    public boolean setGate(GateTimerSendCommand timer){
        DataTask dataTask=new DataTask(timer.name);
        dataTask.id=timer.getTaskId();
        gateTaskList.add(dataTask);
        return false;
    }
    public boolean startGate(){
        return false;
    }
    public boolean removeGate(){
        return  false;
    }
}
