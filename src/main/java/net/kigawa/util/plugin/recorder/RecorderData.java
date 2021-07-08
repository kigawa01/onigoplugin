package net.kigawa.util.plugin.recorder;

import net.kigawa.util.yaml.YamlData;

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
