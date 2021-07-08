package net.kigawa.util.plugin.game.onigo;

import net.kigawa.util.plugin.all.recorder.RecorderData;

public class GameData extends RecorderData {
    public void setEndLoc(int[] endLoc) {
        this.endLoc = endLoc;
    }

    public int[] getEndLoc() {
        return endLoc;
    }

    int[] endLoc = new int[3];

    public void setEndWorld(String endWorld) {
        this.endWorld = endWorld;
    }

    public String getEndWorld() {
        return endWorld;
    }

    String endWorld;

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    int gameTime;

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    int waitTime;

    String waitRoomWorld = null;
    int oniCount;

    public int getOniCount() {
        return oniCount;
    }

    public void setOniCount(int oniCount) {
        this.oniCount = oniCount;
    }

    int[] waitRoom = new int[6];


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
