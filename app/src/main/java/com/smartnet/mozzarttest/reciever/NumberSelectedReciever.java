package com.smartnet.mozzarttest.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.TextView;

import java.util.ArrayList;

public class NumberSelectedReciever extends BroadcastReceiver {

    public static NumberSelectedReciever instance;
    private final String ADD = "Add";
    private final String REMOVE = "Remove";
    private ArrayList<Integer> randomNumberList;
    private TextView txt_bk_count;


    public NumberSelectedReciever(ArrayList<Integer> randomNumberList, TextView txt_bk_count) {
        this.randomNumberList = randomNumberList;
        this.txt_bk_count = txt_bk_count;
    }


    public static NumberSelectedReciever getInstance(ArrayList<Integer> randomNumberList, TextView txt_bk_count) {
        instance = new NumberSelectedReciever(randomNumberList, txt_bk_count);
        return instance;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        handleBroadCastManuallyAction(intent);
    }


    public void registerReciever(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ADD);
        intentFilter.addAction(REMOVE);
        context.registerReceiver(this, intentFilter);
    }

    public void unregister(Context context) {
        context.unregisterReceiver(this);
    }


    /**
     * Handle action when numbers are selected manually and update list with numbers and text view with text
     *
     * @param intent
     */
    private void handleBroadCastManuallyAction(Intent intent) {
        if (intent.getAction().equals(ADD)) {
            randomNumberList.add(Integer.parseInt(intent.getStringExtra("number")));
            txt_bk_count.setText(String.valueOf(8 - randomNumberList.size()));
        } else if (intent.getAction().equals(REMOVE)) {
            int numberToRemove = Integer.parseInt(intent.getStringExtra("number"));
            randomNumberList.remove(randomNumberList.indexOf(numberToRemove));
            txt_bk_count.setText(String.valueOf(8 - randomNumberList.size()));
        }
    }
}
