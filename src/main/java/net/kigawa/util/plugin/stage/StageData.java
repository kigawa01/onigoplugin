package net.kigawa.util.plugin.stage;

import net.kigawa.util.plugin.recorder.RecorderData;

public class StageData extends RecorderData {

    public int[] getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(int[] startLoc) {
        this.startLoc = startLoc;
    }

    int[] startLoc = new int[3];

    public int[] getStageLoc() {
        return stageLoc;
    }

    public void setStageLoc(int[] stageLoc) {
        this.stageLoc = stageLoc;
    }

    int[] stageLoc = new int[6];
    String stageWorld;

    public String getStageWorld() {
        return stageWorld;
    }

    public void setStageWorld(String stageWorld) {
        this.stageWorld = stageWorld;
    }
}
