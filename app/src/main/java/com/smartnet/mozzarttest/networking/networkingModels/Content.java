package com.smartnet.mozzarttest.networking.networkingModels;

import com.smartnet.mozzarttest.reciever.helper.DateFormater;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Content {

    private int gameId;
    private int drawId;
    private long drawTime;
    private String status;
    private int drawBreak;
    private int visualDraw;
    private WinningNumbers winningNumbers;



    public String provideFormatedTime(){
        return DateFormater.getInstance().provideFormatedResultTime(getDrawTime());
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getDrawId() {
        return drawId;
    }

    public void setDrawId(int drawId) {
        this.drawId = drawId;
    }

    public long getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(long drawTime) {
        this.drawTime = drawTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDrawBreak() {
        return drawBreak;
    }

    public void setDrawBreak(int drawBreak) {
        this.drawBreak = drawBreak;
    }

    public int getVisualDraw() {
        return visualDraw;
    }

    public void setVisualDraw(int visualDraw) {
        this.visualDraw = visualDraw;
    }

    public WinningNumbers getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(WinningNumbers winningNumbers) {
        this.winningNumbers = winningNumbers;
    }
}
