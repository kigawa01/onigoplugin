package net.kigawa.util.plugin.game.stage;

import net.kigawa.util.all.EqualsNamed;
import net.kigawa.util.plugin.all.KigawaPlugin;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StageManager {
    List<StageData> allStage = new ArrayList<>();
    List<StageData> canUse = new ArrayList<>();
    List<StageData> notUse = new ArrayList<>();
    KigawaPlugin plugin;

    public StageManager(KigawaPlugin kigawaPlugin) {
        plugin = kigawaPlugin;

        File folder = new File(plugin.getDataFolder(), "stage");
        folder.mkdir();
        String[] files = folder.list();
        for (int i = 0; i < files.length; i++) {
            File file = new File(folder, files[i]);
            plugin.logger(files[i]);
            StageData data = (StageData) plugin.getRecorder().load(StageData.class, "stage", files[i].substring(0, files[i].length() - 4));
            canUse.add(data);
            allStage.add(data);
        }
    }

    public void setStartLoc(String name, int x, int y, int z, CommandSender sender) {
        StageData stageData = getStage(name, sender);
        if (stageData != null) {
            int[] i = stageData.getStartLoc();
            i[0] = x;
            i[1] = y;
            i[2] = z;
            //logger
            plugin.logger("stage manager startLoc " + stageData.getStartLoc()[0]);
            plugin.logger("length " + stageData.getStartLoc().length);
            plugin.getRecorder().save(stageData, "stage");
        }
    }

    public void setStage2(String name, String world, int x, int y, int z, CommandSender sender) {
        StageData stageData = getStage(name, sender);
        if (stageData != null) {
            stageData.setStageWorld(world);
            int[] i = stageData.getStageLoc();
            i[3] = x;
            i[4] = y;
            i[5] = z;
            plugin.getRecorder().save(stageData, "stage");
        }
    }

    public void setStage1(String name, String world, int x, int y, int z, CommandSender sender) {
        StageData stageData = getStage(name, sender);
        if (stageData != null) {
            stageData.setStageWorld(world);
            int[] i = stageData.getStageLoc();
            i[0] = x;
            i[1] = y;
            i[2] = z;
            plugin.getRecorder().save(stageData, "stage");
        }
    }

    public StageData getStage(String name, CommandSender sender) {
        StageData stageData = null;
        if (allStage.contains(new EqualsNamed(name))) {
            stageData = allStage.get(allStage.indexOf(new EqualsNamed(name)));
        } else {
            sender.sendMessage(name + " is not exit");
        }
        return stageData;
    }

    public void setStage(String name, CommandSender sender) {
        //check can use this name
        if (plugin.getStageManager().getStage(name) == null) {
            //create Stage
            StageData stageData = new StageData();
            stageData.setName(name);
            //put in list
            canUse.add(stageData);
            allStage.add(stageData);
            //save
            plugin.getRecorder().save(stageData, "stage");
            //send message
            sender.sendMessage("create " + name);
        } else {
            sender.sendMessage("this name can't use");
        }
    }

    public StageData getStage(String name) {
        StageData stageData = null;
        if (name != null) {
            if (allStage.contains(new EqualsNamed(name))) {
                stageData = allStage.get(allStage.indexOf(new EqualsNamed(name)));
            }
        }
        if (name==null){
            stageData=getRandomStage();
        }
        return stageData;
    }

    public StageData getRandomStage() {
        StageData stageData = null;
        if (canUse.size() > 0) {
            Random random = new Random();
            int randomNumber = random.nextInt(canUse.size());
            stageData = canUse.get(randomNumber);
            notUse.add(stageData);
            canUse.remove(randomNumber);
        }
        return stageData;
    }

    public void returnStage(StageData stageData) {
        if (notUse.contains(new EqualsNamed(stageData.getName()))) {
            canUse.add(stageData);
            notUse.remove(notUse.indexOf(new EqualsNamed(stageData.getName())));
        }
    }
}
