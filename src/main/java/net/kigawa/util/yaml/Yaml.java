package net.kigawa.util.yaml;


import net.kigawa.onigoplugin.game.onigo.OnigoData;
import net.kigawa.onigoplugin.game.onigo.OnigoGame;
import net.kigawa.util.message.Logger;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Yaml {
    Logger logger=new Logger() {
        @Override
        public void logger(String message) {
            System.out.println(message);
        }
    };
    org.yaml.snakeyaml.Yaml yaml;

    public Yaml() {
        yaml = new org.yaml.snakeyaml.Yaml();
    }

    public Yaml(CustomClassLoaderConstructor constructor) {
        yaml = new org.yaml.snakeyaml.Yaml(constructor);
    }

    public Yaml(CustomClassLoaderConstructor constructor, Logger logger) {
        yaml = new org.yaml.snakeyaml.Yaml(constructor);
        this.logger = logger;
    }

    public void save(YamlData data, File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            String dump = yaml.dump(data);
            logger.logger(dump);
            fileWriter.write(dump);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T load(Class<T> type, File file) {
        T data = null;

        try {
            data = type.getDeclaredConstructor().newInstance();
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
            FileReader reader = new FileReader(file);
            data = yaml.loadAs(reader, type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public <T> List<T> loadAll(Class<T> type, File dir){
        List<T> yamlData=new ArrayList<>();


        dir.mkdir();
        String[] files=dir.list();
        for (int i=0;i<files.length;i++){
            File file=new File(dir,files[i]);
            T data=load(type,file);
            yamlData.add(data);
        }


        return yamlData;
    }

}
