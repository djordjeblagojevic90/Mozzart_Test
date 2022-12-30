package com.smartnet.mozzarttest.reciever.helper;

import android.content.BroadcastReceiver;

import java.util.ArrayList;

/**
 * This class help us to unregister all reciervers what are created dinamically
 */
public class UnregisterRecieversHelper {

    private static UnregisterRecieversHelper instance;
    private ArrayList<BroadcastReceiver> receivers;


    public static UnregisterRecieversHelper getInstance() {
        if (instance == null) {
            instance = new UnregisterRecieversHelper();
        }
        return instance;
    }


    public ArrayList<BroadcastReceiver> getReceiversList() {
        if (receivers == null) {
            receivers = new ArrayList<>();
        }
        return receivers;
    }
}
