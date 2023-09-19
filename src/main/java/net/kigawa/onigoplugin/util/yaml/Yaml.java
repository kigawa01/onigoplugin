package net.kigawa.onigoplugin.util.yaml;


import net.kigawa.onigoplugin.util.message.Logger;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Yaml
{
  Logger logger;
  org.yaml.snakeyaml.Yaml yaml;

  public Yaml(CustomClassLoaderConstructor constructor, Logger logger) {
    yaml = new org.yaml.snakeyaml.Yaml(constructor);
    this.logger = logger;
  }

  public void save(YamlData data, File file) {
    try {
      if (!file.exists() && !file.createNewFile()) {
        throw new IOException("could not create file");
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
    //check file exists
    if (file.exists()) {
      try {
        data = type.getDeclaredConstructor().newInstance();
      } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
        e.printStackTrace();
      }
      try {
        FileReader reader = new FileReader(file);
        data = yaml.loadAs(reader, type);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
    return data;
  }

  public <T> List<T> loadAll(Class<T> type, File dir) {
    List<T> yamlData = new ArrayList<>();
    //make dir
    dir.mkdirs();
    //get files name
    String[] files = dir.list();
    if (files == null) return yamlData;
    //load and add data
    for (String s : files) {
      File file = new File(dir, s);
      T data = load(type, file);
      yamlData.add(data);
    }


    return yamlData;
  }

}
