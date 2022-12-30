package com.smartnet.mozzarttest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.smartnet.mozzarttest.db.LocalDbHelper;
import com.smartnet.mozzarttest.generator.RandomGenerator;
import com.smartnet.mozzarttest.reciever.NumberSelectedReciever;
import com.smartnet.mozzarttest.reciever.helper.DateFormater;
import com.smartnet.mozzarttest.reciever.helper.UnregisterRecieversHelper;
import com.smartnet.mozzarttest.ui.TableNumbers;

import java.util.ArrayList;

public class TalonActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Integer> randomNumberList;
    private TextView txt_bk_count;
    private NumberSelectedReciever numberSelectedReciever;
    private RandomGenerator randomGenerator;
    private Runnable runnable;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talon_layout);
        updateUiComponentWithInformationEveryOneSec();
        LinearLayoutCompat linearLayoutCompat = findViewById(R.id.talon_layout);
        linearLayoutCompat.addView(new TableNumbers(this, 10, 8, provideRandomNumberList()));
        findViewById(R.id.btn_random_generator).setOnClickListener(this);
        findViewById(R.id.btn_submit).setOnClickListener(this);
        findViewById(R.id.btn_check_results).setOnClickListener(this);
        txt_bk_count = provideTextViewBKcount();
        initHelperClass();

    }

    private void initHelperClass() {
        numberSelectedReciever = NumberSelectedReciever.getInstance(provideRandomNumberList(), txt_bk_count);
        numberSelectedReciever.registerReciever(this);
        randomGenerator = RandomGenerator.getInstance(provideRandomNumberList(), this);
    }


    private TextView provideTextViewBKcount() {
        TextView textView = findViewById(R.id.txt_bk_count);
        return textView;
    }

    /**
     * Method to create random number list if its null create if its not return only
     *
     * @return instance of random number list
     */
    private ArrayList<Integer> provideRandomNumberList() {
        if (randomNumberList == null) {
            randomNumberList = new ArrayList<>();
        }
        return randomNumberList;
    }


    /**
     * Helper to update text view with information every 1 sec
     */
    private void updateUiComponentWithInformationEveryOneSec() {
        TextView textView = findViewById(R.id.txt_drawTime);
        handler = new Handler();
        runnable = () -> updateUieveryOneSecAction(textView);
        handler.postDelayed(runnable, 0);
    }

    private void updateUieveryOneSecAction(TextView textView) {
        if (!provideFormatedReamingDrawTimeDate().equals("Times out")) {
            textView.setText(convertTextForTextViewWithIntentInformations());
            handler.postDelayed(runnable, 1000);
        } else {
            textView.setText(convertTextForTextViewWithIntentInformations());
            handler.removeCallbacks(runnable);
            findViewById(R.id.btn_submit).setVisibility(View.GONE);
        }
    }


    /**
     * Helper method to convert message with intent informations
     *
     * @return message for text view
     */
    private String convertTextForTextViewWithIntentInformations() {
        return "Vreme izvlacenja: " + getIntent().getStringExtra("drawTime") + " | Kolo: " + getIntent().getIntExtra("drawId", 0)
                + " | Preostalo vreme: " + provideFormatedReamingDrawTimeDate();
    }


    /**
     * Helper to get reaming time for draw
     *
     * @return formated reaming time
     */
    public String provideFormatedReamingDrawTimeDate() {
        return DateFormater.getInstance().provideFormatedReamingDrawTimeDate((getIntent().getLongExtra("drawTimeInMills", 0)));
    }

    @Override
    protected void onDestroy() {
        numberSelectedReciever.unregister(this);
        unregisterTextRecievers();
        super.onDestroy();
        finishAndRemoveTask();

    }

    private void unregisterTextRecievers() {
        for (int i = 0; i < UnregisterRecieversHelper.getInstance().getReceiversList().size(); i++) {
            unregisterReceiver(UnregisterRecieversHelper.getInstance().getReceiversList().get(i));
        }
        UnregisterRecieversHelper.getInstance().getReceiversList().clear();
    }

    @Override
    public void onBackPressed() {
        finishAndRemoveTask();
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_random_generator:
                buttonRandomGeneratorAction();
                break;

            case R.id.btn_submit: {
                buttonSubmitAction();
                break;
            }

            case R.id.btn_check_results:
                openResultsScreen();
        }
    }


    /**
     * Helper method to open new screen with results when button check results is clicked
     */
    private void openResultsScreen() {
        Intent intent = new Intent(this, ResultsOfDrawActivity.class);
        startActivity(intent);
    }

    /**
     * Helper method to do some work for button random generator
     */
    private void buttonRandomGeneratorAction() {
        if (!provideTextViewBKcount().getText().equals("0")) {
            randomGenerator.generateRandomNumbers(Integer.parseInt(txt_bk_count.getText().toString()));
            txt_bk_count.setText(String.valueOf(8 - provideRandomNumberList().size()));
        } else {
            Toast.makeText(this, "8 numbers are already selected", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Helper method to do some work for button submit
     */
    private void buttonSubmitAction() {
        if (provideRandomNumberList().size() == 8) {
            LocalDbHelper.getInstance(this).addDrawIdToListAndSave(getIntent().getIntExtra("drawId", 0));
            Intent intent = new Intent(this, WebViewActivity.class);
            startActivity(intent);
            finishAndRemoveTask();
        } else {
            Toast.makeText(this, "Please select 8 numbers before you submit combination", Toast.LENGTH_SHORT).show();
        }
    }
}
