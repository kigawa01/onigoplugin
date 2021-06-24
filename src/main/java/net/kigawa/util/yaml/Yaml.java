package net.kigawa.util.yaml;


import net.kigawa.util.message.Logger;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class Yaml {
    Logger logger;
    org.yaml.snakeyaml.Yaml yaml;
    public Yaml(){
        yaml=new org.yaml.snakeyaml.Yaml();
    }
    public Yaml(CustomClassLoaderConstructor constructor){
        yaml=new org.yaml.snakeyaml.Yaml(constructor);
    }
    public Yaml(CustomClassLoaderConstructor constructor, Logger logger){
        yaml=new org.yaml.snakeyaml.Yaml(constructor);
        this.logger=logger;
    }
    public void save(YamlData data, File file){
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter=new FileWriter(file);
            String dump=yaml.dump(data);
            logger.logger(dump);
            fileWriter.write(dump);
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
