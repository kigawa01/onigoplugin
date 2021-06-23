package net.kigawa.util.yaml;


import net.kigawa.util.plugin.plugin.KigawaPlugin;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class Yaml {

    org.yaml.snakeyaml.Yaml yaml;
    public Yaml(){
        yaml=new org.yaml.snakeyaml.Yaml();
    }
    public Yaml(CustomClassLoaderConstructor constructor){
        yaml=new org.yaml.snakeyaml.Yaml(constructor);
    }
    public void save(YamlData data, File file){
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter=new FileWriter(file);
            fileWriter.write(yaml.dump(data));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public YamlData load(Class<? extends YamlData> type,File file){
        YamlData data=null;
        try {
            data=type.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            FileReader reader=new FileReader(file);
            data=yaml.loadAs(reader,type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

}
