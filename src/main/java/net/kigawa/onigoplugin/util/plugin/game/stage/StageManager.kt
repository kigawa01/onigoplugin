package net.kigawa.onigoplugin.util.plugin.game.stage;

import net.kigawa.kutil.unitapi.annotation.Kunit;
import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.onigoplugin.util.all.EqualsNamed;
import net.kigawa.onigoplugin.util.plugin.all.recorder.Recorder;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Kunit
public class StageManager
{
  List<StageData> allStage = new ArrayList<>();
  List<StageData> canUse = new ArrayList<>();
  List<StageData> notUse = new ArrayList<>();
  OnigoPlugin plugin;
  private Recorder recorder;

  public StageManager(OnigoPlugin OnigoPlugin, Recorder recorder) {
    plugin = OnigoPlugin;
    this.recorder = recorder;

    File folder = new File(plugin.getDataFolder(), "stage");
    folder.mkdir();
    String[] files = folder.list();
    for (int i = 0; i < files.length; i++) {
      File file = new File(folder, files[i]);
      plugin.logger(files[i]);
      StageData data = (StageData) recorder.load(StageData.class, "stage", files[i].substring(0, files[i].length() - 4));
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
      recorder.save(stageData, "stage");
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
      recorder.save(stageData, "stage");
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
      recorder.save(stageData, "stage");
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
    if (getStage(name) == null) {
      //create Stage
      StageData stageData = new StageData();
      stageData.setName(name);
      //put in list
      canUse.add(stageData);
      allStage.add(stageData);
      //save
      recorder.save(stageData, "stage");
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
    if (name == null) {
      stageData = getRandomStage();
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
