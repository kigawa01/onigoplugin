package net.kigawa.util.plugin.stage;

import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.recorder.EqualsRecorderData;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StageManager {
    List<StageData> allStage=new ArrayList<>();
    List<StageData> canUse=new ArrayList<>();
    List<StageData> notUse=new ArrayList<>();
    KigawaPlugin plugin;
    public StageManager(KigawaPlugin kigawaPlugin){
        plugin=kigawaPlugin;

        File folder=new File( plugin.getDataFolder(),"stage");
        folder.mkdir();
        String[] files=folder.list();
        for (int i=0;i<files.length;i++){
            File file=new File(folder,files[i]);
            plugin.logger(files[i]);
            StageData data=(StageData) plugin.getRecorder().load(StageData.class,"stage",files[i].substring(0, files[i].length()-4));
            canUse.add(data);
            allStage.add(data);
        }
    }
    public void setStage(String name, CommandSender sender){
        //check can use this name
        if (plugin.getStageManager().getStage(name)!=null){
            //create Stage
            StageData stageData=new StageData();
            stageData.setName(name);
            //put in list
            canUse.add(stageData);
            allStage.add(stageData);
            //save
            plugin.getRecorder().save(stageData);
        }else {
            sender.sendMessage("this name can't use");
        }
    }
    public StageData getStage(String name){
        StageData stageData=null;
        if (allStage.contains(new EqualsRecorderData(name))){
            stageData= allStage.get(allStage.indexOf(new EqualsRecorderData(name)));
        }
        return stageData;
    }
    public StageData getRandomStage(){
        StageData stageData=null;
         if (canUse.size()>0){
             Random random=new Random();
             int randomNumber= random.nextInt(canUse.size());
             stageData=canUse.get(randomNumber);
             notUse.add(stageData);
             canUse.remove(randomNumber);
         }
         return stageData;
    }
}
