package net.kigawa.onigoplugin.onigo;

import net.kigawa.util.plugin.recorder.RecorderData;

public class OnigoData extends RecorderData {


    String waitRoomWorld=null;
    int oniCount;

    public int getOniCount() {
        return oniCount;
    }

    public void setOniCount(int oniCount) {
        this.oniCount = oniCount;
    }
    int[] waitRoom=new int[5];

    public OnigoData() {
        setFolder("onigo");
    }
    public int[] getWaitRoom() {
        return waitRoom;
    }

    public void setWaitRoom(int[] waitRoom) {
        this.waitRoom = waitRoom;
    }

    public String getWaitRoomWorld() {
        return waitRoomWorld;
    }

    public void setWaitRoomWorld(String waitRoomWorld) {
        this.waitRoomWorld = waitRoomWorld;
    }


}
