package com.smartnet.mozzarttest.ui;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.smartnet.mozzarttest.R;
import com.smartnet.mozzarttest.reciever.helper.UnregisterRecieversHelper;

import java.util.ArrayList;

public class TextViewNumbers extends androidx.appcompat.widget.AppCompatTextView implements View.OnClickListener {

    private boolean circleDrawable = false;
    private final String ACTION_RANDOM = "randomNumbers";
    private final String INTENT_EXTRA = "random";
    private final String ADD = "Add";
    private final String REMOVE = "Remove";
    private TextViewNumbers instance;
    private ArrayList<Integer> listOfNumbers;

    public TextViewNumbers(Context context, String number, ArrayList<Integer> listOfNumbers) {
        super(context);
        instance = this;
        instance.listOfNumbers = listOfNumbers;
        instance.setOnClickListener(this);
        instance.setText(number);
        instance.setTextSize(18);
        instance.setGravity(Gravity.CENTER);
        instance.setTextColor(getResources().getColor(R.color.black, context.getTheme()));
        registerBroadCastReciever();
    }


    /**
     * This method help us to round number when user click on random generator of numbers
     */
    private void registerBroadCastReciever() {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                broadcastIntentAction(intent);
            }
        };
        IntentFilter intentFilter = new IntentFilter(ACTION_RANDOM);
        getContext().registerReceiver(broadcastReceiver, intentFilter);
        UnregisterRecieversHelper.getInstance().getReceiversList().add(broadcastReceiver);
    }


    /**
     * Helper method to handle broadcast reciever intent and do some work after that
     *
     * @param intent
     */
    private void broadcastIntentAction(Intent intent) {
        if (String.valueOf(intent.getIntExtra(INTENT_EXTRA, 0)).equals(instance.getText())) {
            instance.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_drawable, getContext().getTheme()));
            circleDrawable = true;
        }
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View view) {
        if (listOfNumbers.size() == 8 && this.getDrawableState().equals(getResources().getDrawable(R.drawable.circle_drawable, getContext().getTheme()))) {
            Toast.makeText(getContext(), provideTextWhen8NumbersAreSelected(), Toast.LENGTH_SHORT).show();
        } else if (!circleDrawable && listOfNumbers.size() < 8) {
            actionAddNumber();
        } else if (circleDrawable) {
            actionRemoveNumber();
        } else if (!circleDrawable && listOfNumbers.size() == 8) {
            Toast.makeText(getContext(), provideTextWhen8NumbersAreSelected(), Toast.LENGTH_SHORT).show();
        }
    }


    private String provideTextWhen8NumbersAreSelected() {
        return "8 numbers are already selected";
    }

    private void actionAddNumber() {
        this.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_drawable, getContext().getTheme()));
        circleDrawable = true;
        Intent intent = new Intent(ADD);
        intent.putExtra("number", this.getText().toString());
        getContext().sendBroadcast(intent);
    }

    private void actionRemoveNumber() {
        this.setBackgroundResource(0);
        circleDrawable = false;
        Intent intent = new Intent(REMOVE);
        intent.putExtra("number", this.getText().toString());
        getContext().sendBroadcast(intent);
    }
}
