package net.kigawa.utilplugin.api.list;

public class ForEquals {
    String type;
    Object object;
    public ForEquals(String type,Object object){
        this.type=type;
        this.object=object;
    }

    public String getType() {
        return type;
    }


    public Object getObject() {
        return object;
    }
}
