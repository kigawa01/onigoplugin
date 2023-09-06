package net.kigawa.onigoplugin.util.plugin.game.onigo;

import net.kigawa.onigoplugin.util.plugin.all.recorder.RecorderData;

public class GameData extends RecorderData {
    int[] endLoc = new int[3];
    String endWorld;
    int gameTime;
    int waitTime;
    String waitRoomWorld = null;
    int oniCount;
    int[] waitRoom = new int[6];
    String gameType;
    int[] oniWait = new int[6];
    String oniWaitWorld;

    public String getOniWaitWorld() {
        return oniWaitWorld;
    }

    public void setOniWaitWorld(String oniWaitWorld) {
        this.oniWaitWorld = oniWaitWorld;
    }

    public int[] getOniWait() {
        return oniWait;
    }

    public void setOniWait(int[] oniWait) {
        this.oniWait = oniWait;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public int[] getEndLoc() {
        return endLoc;
    }

    public void setEndLoc(int[] endLoc) {
        this.endLoc = endLoc;
    }

    public String getEndWorld() {
        return endWorld;
    }

    public void setEndWorld(String endWorld) {
        this.endWorld = endWorld;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getOniCount() {
        return oniCount;
    }

    public void setOniCount(int oniCount) {
        this.oniCount = oniCount;
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
