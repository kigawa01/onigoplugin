package net.kigawa.utilplugin.api.recorder;

public abstract class Data {
    String name;
    Class<Data> type;
    public Data(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
}
