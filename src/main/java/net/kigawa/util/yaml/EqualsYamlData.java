package net.kigawa.util.yaml;

public class EqualsYamlData {
    String name;
    public EqualsYamlData(String name){
        this.name=name;
    }

    public EqualsYamlData() {

    }

    @Override
    public boolean equals(Object o){
        return  ((YamlData)o).getName().equals(name);
    }
}
