package net.kigawa.util.plugin.data;

import net.kigawa.util.yaml.YamlData;

public  class RecorderData implements YamlData {
    String name;
    String folder;
    public RecorderData(){
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getFolder(){
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
