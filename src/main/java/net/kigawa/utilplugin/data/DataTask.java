package net.kigawa.utilplugin.data;

public class DataTask {
    String name;
    public int id;
    public DataTask(String name){
        this.name=name;
    }
    public boolean equals(Object o){
        boolean isMach=false;
        if(o instanceof DataTask){
            return ((DataTask) o).name.equals(name);
        }else
        return isMach;
    }
}
