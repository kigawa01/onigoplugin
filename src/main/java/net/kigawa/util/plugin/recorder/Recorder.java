package net.kigawa.util.plugin.recorder;

import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.yaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

import java.io.File;

public class Recorder {
    KigawaPlugin plugin;
    Yaml yaml;
    boolean useDB;
    public Recorder(KigawaPlugin kigawaPlugin){
        plugin=kigawaPlugin;
        useDB=plugin.getConfig().getBoolean("useDB");
        if(!useDB){
            yaml=new Yaml(new CustomClassLoaderConstructor(KigawaPlugin.class.getClassLoader()));
        }
    }
    public void save(RecorderData data){
        if (!useDB){
            File folder=new File(plugin.getDataFolder(),data.getFolder());
            folder.mkdir();
            File file=new File(folder,data.getName()+".yml");
            yaml.save(data,file);
        }
    }
    public RecorderData load(Class<? extends RecorderData> type,String folderName,String name){
        RecorderData data = null;
        if (!useDB) {
            File folder = new File(plugin.getDataFolder(), folderName);
            File file = new File(folder, name+".yml");
            data = (RecorderData) yaml.load(type, file);
        }
        return data;
    }
}
