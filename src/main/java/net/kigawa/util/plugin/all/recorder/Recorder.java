package net.kigawa.util.plugin.all.recorder;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.yaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

import java.io.File;
import java.util.List;

public class Recorder {
    OnigoPlugin plugin;
    Yaml yaml;
    boolean useDB;
    public Recorder(OnigoPlugin OnigoPlugin){
        plugin=OnigoPlugin;
        useDB=plugin.getConfig().getBoolean("useDB");
        if(!useDB){
            yaml=new Yaml(new CustomClassLoaderConstructor(OnigoPlugin.class.getClassLoader()),plugin);
        }
    }
    public void save(RecorderData data,String dir){
        if (!useDB){
            File folder=new File(plugin.getDataFolder(),dir);
            folder.mkdir();
            File file=new File(folder,data.getName()+".yml");
            yaml.save(data,file);
        }
    }
    public <T> T load(Class<T> type,String dir,String name){
        T data = null;
        if (!useDB) {
            File folder = new File(plugin.getDataFolder(), dir);
            File file = new File(folder, name+".yml");
            data =yaml.load(type, file);
        }
        return data;
    }
    public <T> List<T> loadAll(Class<T> type,String dir){
        return yaml.loadAll(type,new File(plugin.getDataFolder(),dir));
    }
}
