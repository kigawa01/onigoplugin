package net.kigawa.utilplugin.api.recorder;

import net.kigawa.utilplugin.api.plugin.KigawaPlugin;

import java.io.*;

public class Yaml {
    KigawaPlugin plugin;
    File dataFolder;
    File dataFile;
    Data data;
    org.yaml.snakeyaml.Yaml yaml;
    Class<Data> type;
    public Yaml(KigawaPlugin kigawaPlugin,Data data,Class<Data> type){
        plugin=kigawaPlugin;
        this.data=data;
        this.type=type;
        dataFolder= new File(plugin.getDataFolder(),"data");
        dataFolder.mkdirs();
        dataFile=new File(dataFolder,data.getName()+".yml");

        if (!dataFile.exists()){
            try {
                dataFile.createNewFile();
                FileWriter fileWriter=new FileWriter(dataFile);
                fileWriter.write(yaml.dump(data));
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileReader fileReader = new FileReader(dataFile);
            data=yaml.loadAs(fileReader,type);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void save(Data data){
        if (data.getName().equals(this.data.getName())){
            try {
                FileWriter fileWriter = new FileWriter(dataFile);
                fileWriter.write(yaml.dump(data));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public Data load(){
        try {
            FileReader fileReader = new FileReader(dataFile);
            data=yaml.loadAs(fileReader,type);
        }catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }

}
