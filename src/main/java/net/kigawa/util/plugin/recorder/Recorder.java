package net.kigawa.util.plugin.recorder;

import com.sun.tools.example.debug.tty.TTY;
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
            yaml=new Yaml(new CustomClassLoaderConstructor(KigawaPlugin.class.getClassLoader()),plugin);
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
    public <T> T load(Class<T> type,String folderName,String name){
        T data = null;
        if (!useDB) {
            File folder = new File(plugin.getDataFolder(), folderName);
            File file = new File(folder, name+".yml");
            data =yaml.load(type, file);
        }
        return data;
    }
}
