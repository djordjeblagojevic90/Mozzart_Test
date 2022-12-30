package com.smartnet.mozzarttest.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LocalDbHelper {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private final String TAG = "LocalDbHelper";
    public ArrayList<Integer> integerList;
    private Context context;
    private final String DRAW_ID = "drawId";
    private static LocalDbHelper instance;


    public LocalDbHelper(Context context) {
        this.context = context;
        instance = this;
        initLocalDb();
        getIntegerList();
    }

    public static LocalDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new LocalDbHelper(context);
        }
        return instance;
    }

    public void initLocalDb() {
        sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public void addDrawIdToListAndSave(int drawId) {
        if (!integerList.contains(drawId)) {
            integerList.add(drawId);
        }
        editor.putString(DRAW_ID, new Gson().toJson(integerList));
        editor.commit();
    }


    public ArrayList<Integer> getIntegerList() {
        String list = sharedPreferences.getString(DRAW_ID, "");
        if (list.equals("")) {
            integerList = new ArrayList<>();
        } else {
            Type type = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            integerList = new Gson().fromJson(list, type);
        }
        return integerList;
    }
}
