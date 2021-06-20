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

    }
}
