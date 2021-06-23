package net.kigawa.onigoplugin.onigo;

import net.kigawa.util.plugin.plugin.KigawaPlugin;
import org.bukkit.command.CommandSender;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OnigoManager {
    List<Onigo> onigoList=new ArrayList<>();
    KigawaPlugin plugin;
    public OnigoManager(KigawaPlugin kigawaPlugin){
        plugin=kigawaPlugin;

        File folder=new File( plugin.getDataFolder(),"onigo");
        folder.mkdir();
        String[] files=folder.list();
        for (int i=0;i<files.length;i++){
            File file=new File(folder,files[i]);
            plugin.logger(files[i]);
            OnigoData data=(OnigoData) plugin.getRecorder().load(OnigoData.class,"onigo",files[i].substring(0, files[i].length()-4));
            onigoList.add(new Onigo(plugin,data));
        }
    }
    public void createOnigo(String name){
        createOnigo(plugin.getServer().getConsoleSender(),name);
    }
    public void createOnigo(CommandSender sender,String name){
        if (!onigoList.contains(new EqualsOnigo(name))){
            OnigoData data=new OnigoData();
            data.setFolder("onigo");
            data.setName(name);
            onigoList.add(new Onigo(plugin,data));
            plugin.getRecorder().save(data);
        }else {
            sender.sendMessage("this name can't use");
        }

    }

    public List<Onigo> getOnigoList() {
        return onigoList;
    }
}
