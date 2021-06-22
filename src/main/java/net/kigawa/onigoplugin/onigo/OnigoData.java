package net.kigawa.onigoplugin.onigo;

import net.kigawa.util.plugin.data.RecorderData;

public class OnigoData extends RecorderData {

    int[] waitRoom=new int[6];
    String waitRoomWorld=null;
    public OnigoData() {
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
