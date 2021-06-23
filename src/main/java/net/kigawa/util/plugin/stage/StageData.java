package net.kigawa.util.plugin.stage;

import net.kigawa.util.plugin.recorder.RecorderData;

public class StageData extends RecorderData {

    public StageData(){
        setFolder("stage");
    }

    public int[] getStageLoc() {
        return stageLoc;
    }

    public void setStageLoc(int[] stageLoc) {
        this.stageLoc = stageLoc;
    }

    int[] stageLoc=new int[6];
    String stageWorld;

    public String getStageWorld() {
        return stageWorld;
    }

    public void setStageWorld(String stageWorld) {
        this.stageWorld = stageWorld;
    }
}
