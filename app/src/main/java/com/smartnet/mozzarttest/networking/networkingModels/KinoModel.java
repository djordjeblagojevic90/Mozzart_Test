package com.smartnet.mozzarttest.networking.networkingModels;

import com.smartnet.mozzarttest.reciever.helper.DateFormater;

import java.util.ArrayList;


/**
 * Model class what we will get from server with all data for game
 */
public class KinoModel {

    private int gameId;
    private int drawId;
    private long drawTime;
    private String status;
    private int drawBreak;
    private int visualDraw;
    private PricePoints pricePoints;
    private ArrayList<PrizeCategories> prizeCategories;
    private WagerStatistics wagerStatistics;


    /**
     * Helper to format date from log value what we got from server
     *
     * @return formated date
     */
    public String provideFormatedDrawTimeDate() {
        return DateFormater.getInstance().provideFormatedDrawTimeDate(getDrawTime());
    }

    /**
     * Helper to get reaming time for draw
     *
     * @return formated reaming time
     */
    public String provideFormatedReamingDrawTimeDate() {
        return DateFormater.getInstance().provideFormatedReamingDrawTimeDate(getDrawTime());
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

    public PricePoints getPricePoints() {
        return pricePoints;
    }

    public void setPricePoints(PricePoints pricePoints) {
        this.pricePoints = pricePoints;
    }

    public ArrayList<PrizeCategories> getPrizeCategories() {
        return prizeCategories;
    }

    public void setPrizeCategories(ArrayList<PrizeCategories> prizeCategories) {
        this.prizeCategories = prizeCategories;
    }

    public WagerStatistics getWagerStatistics() {
        return wagerStatistics;
    }

    public void setWagerStatistics(WagerStatistics wagerStatistics) {
        this.wagerStatistics = wagerStatistics;
    }
}
