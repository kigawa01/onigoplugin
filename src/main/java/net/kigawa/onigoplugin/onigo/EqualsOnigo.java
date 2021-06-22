package net.kigawa.onigoplugin.onigo;

public class EqualsOnigo {
    String name;
    public EqualsOnigo(String name){
        this.name=name;
    }
    @Override
    public boolean equals(Object o){
        return  ((Onigo)o).getName().equals(name);
    }
}
