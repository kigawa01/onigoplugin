package net.kigawa.onigoplugin.onigo;

import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.yaml.EqualsYamlData;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OnigoManager {
    List<Onigo> list=new ArrayList<>();
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
            list.add(new Onigo(plugin,data));
        }
    }
    public void setGameTime(String gameName,CommandSender sender,int gameTime){
        Onigo onigo=getOnigo(gameName,sender);
            if (onigo!=null){
                onigo.setGameTime(gameTime);
            }
        }
    public void setWaitTime(String gameName,CommandSender sender,int waitTime){
        Onigo onigo=getOnigo(gameName,sender);
        if (onigo!=null){
            onigo.setWaitTime(waitTime);
        }
    }
    public void setOniCount(String gameName,CommandSender sender,int oniCount){
        Onigo onigo=getOnigo(gameName,sender);
        if (onigo!=null){
            onigo.setOniCount(oniCount);
        }
    }
    public void start(String gameName,CommandSender sender){
        Onigo onigo=getOnigo(gameName,sender);
        if (onigo!=null){
            onigo.start(sender);
        }
    }
    public Onigo getOnigo(String gameName,CommandSender sender){
        if (list.contains(new EqualsYamlData(gameName))){
            return list.get(list.indexOf(new EqualsYamlData(gameName)));
        }else{
            sender.sendMessage(gameName+" is not exit");
            return null;
        }
    }
    public void setWaitRoom2(String gameName,int x,int y,int z,CommandSender sender){
        if (list.contains(new EqualsYamlData(gameName))){
            list.get(list.indexOf(new EqualsYamlData(gameName))).setWaitingRoom2(x,y,z);
        }else{
            sender.sendMessage(gameName+" is not exit");
        }
    }
    public void setWaitRoom1(String gameName,String worldName,int x,int y,int z,CommandSender sender){
        if (list.contains(new EqualsYamlData(gameName))){
            list.get(list.indexOf(new EqualsYamlData(gameName))).setWaitingRoom1(worldName,x,y,z);
        }else{
            sender.sendMessage(gameName+" is not exit");
        }
    }
    public void createOnigo(String name){
        createOnigo(plugin.getServer().getConsoleSender(),name);
    }
    public void createOnigo(CommandSender sender,String name){
        if (!list.contains(new EqualsYamlData(name))){
            OnigoData data=new OnigoData();
            data.setFolder("onigo");
            data.setName(name);
            getOnigoList().add(new Onigo(plugin,data));
            plugin.getRecorder().save(data);
        }else {
            sender.sendMessage("this name can't use");
        }

    }

    public List<Onigo> getOnigoList() {
        return list;
    }
}
