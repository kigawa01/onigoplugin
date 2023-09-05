package net.kigawa.onigoplugin.util.plugin.all.recorder;

import net.kigawa.onigoplugin.util.yaml.YamlData;

public class RecorderData implements YamlData {
    String name;

    public RecorderData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
