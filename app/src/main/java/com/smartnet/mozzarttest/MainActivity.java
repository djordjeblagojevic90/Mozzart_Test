package com.smartnet.mozzarttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.smartnet.mozzarttest.db.LocalDbHelper;
import com.smartnet.mozzarttest.networking.HttpRequestHelper;
import com.smartnet.mozzarttest.viewModel.KinoViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        initKinoViewModelAndShowData();
        super.onResume();
    }

    /**
     * Helper method to init KinoViewModel and show colected data from server
     */
    private void initKinoViewModelAndShowData() {
        KinoViewModel kinoViewModel = new ViewModelProvider(this).get(KinoViewModel.class);
        kinoViewModel.colectKinoDataFromServer();
        kinoViewModel.oberveKinoLiveData(this, provideTimerRecycler());
    }


    /**
     * Helper method to provide recycler view
     *
     * @return recycler view
     */
    private RecyclerView provideTimerRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recycler_timer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.getItemAnimator().setChangeDuration(0);
        return recyclerView;
    }
}