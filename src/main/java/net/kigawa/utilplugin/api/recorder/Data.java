package net.kigawa.utilplugin.api.recorder;

import java.lang.reflect.Type;

public abstract class Data {
    String name;
    String type;
    public Data(String name,String type){
        this.name=name;
        this.type=getType();
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
