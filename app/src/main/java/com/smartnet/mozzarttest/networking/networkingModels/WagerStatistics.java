package com.smartnet.mozzarttest.networking.networkingModels;

import java.util.ArrayList;

public class WagerStatistics {

    private int columns;
    private int wagers;
    private ArrayList<Addon> addOn;


    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getWagers() {
        return wagers;
    }

    public void setWagers(int wagers) {
        this.wagers = wagers;
    }

    public ArrayList<Addon> getAddOn() {
        return addOn;
    }

    public void setAddOn(ArrayList<Addon> addOn) {
        this.addOn = addOn;
    }
}
