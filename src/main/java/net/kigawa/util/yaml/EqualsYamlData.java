package net.kigawa.util.yaml;

import net.kigawa.util.plugin.recorder.RecorderData;

public class EqualsYamlData {
    String name;
    public EqualsYamlData(String name){
        this.name=name;
    }
    @Override
    public boolean equals(Object o){
        return  ((YamlData)o).getName().equals(name);
    }
}
