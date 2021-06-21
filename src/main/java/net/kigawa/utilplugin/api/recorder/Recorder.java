package net.kigawa.utilplugin.api.recorder;

import net.kigawa.utilplugin.api.plugin.KigawaPlugin;

import java.io.File;

public class Recorder {
    KigawaPlugin plugin;
    File dataFolder;
    public Recorder(KigawaPlugin kigawaPlugin){
        plugin=kigawaPlugin;
        dataFolder= new File(plugin.getDataFolder(),"data");
        dataFolder.mkdirs();
    }
    public void save(Data data){
        if(!plugin.getConfig().getBoolean("useDB")){
            Yaml yaml=new Yaml(plugin,data);
            yaml.save(data);
        }

    }
    public Data load(Data data){
        Yaml yaml=new Yaml(plugin,data);
        return yaml.load();
    }
}
