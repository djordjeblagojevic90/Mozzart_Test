package com.smartnet.mozzarttest.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.smartnet.mozzarttest.adapter.TimerAdapter;
import com.smartnet.mozzarttest.networking.HttpRequestHelper;
import com.smartnet.mozzarttest.networking.networkingModels.KinoModel;

import java.util.ArrayList;

public class KinoViewModel extends AndroidViewModel {

    private LiveData<ArrayList<KinoModel>> kinoLiveData;
    private final String TAG = "KinoViewModel";


    public KinoViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Helper method to init kino live data and prepare for holding information abaout kino models from server
     */
    public void colectKinoDataFromServer() {
        kinoLiveData = HttpRequestHelper.getInstance(getApplication()).getKinoList();
        HttpRequestHelper.getInstance(getApplication()).provideKinoModels();
    }

    /**
     * Helper method to observe kinoLiveData and changes and do some work when changes are happend
     */
    public void oberveKinoLiveData(AppCompatActivity appCompatActivity, RecyclerView recyclerView) {
        kinoLiveData.observe(appCompatActivity, kinoModels -> {
            Log.i(TAG, "Kino list is filled with informations");
            recyclerView.setAdapter(TimerAdapter.getInstance(getApplication(), kinoModels));
        });
    }
}
