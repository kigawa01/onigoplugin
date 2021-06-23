package net.kigawa.util.plugin.recorder;

import net.kigawa.util.plugin.recorder.RecorderData;

public class EqualsRecorderData {
    String name;
    public EqualsRecorderData(String name){
        this.name=name;
    }
    @Override
    public boolean equals(Object o){
        return  ((RecorderData)o).getName().equals(name);
    }
}
