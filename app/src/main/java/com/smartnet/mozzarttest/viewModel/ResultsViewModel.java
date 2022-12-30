package com.smartnet.mozzarttest.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.smartnet.mozzarttest.adapter.ResultsAdapter;
import com.smartnet.mozzarttest.networking.HttpRequestHelper;
import com.smartnet.mozzarttest.networking.networkingModels.Results;

public class ResultsViewModel extends AndroidViewModel {

    private final String TAG = "ResultsViewModel";
    private LiveData<Results> liveDataResults;


    public ResultsViewModel(@NonNull Application application) {
        super(application);
        Log.i(TAG, TAG + " is created");
    }


    /**
     * Helper method to colect results data
     */
    public void colectResultsData() {
        liveDataResults = HttpRequestHelper.getInstance(getApplication()).getResultsList();
        HttpRequestHelper.getInstance(getApplication()).provideResults();
    }


    /**
     * Observe results data and do action when something changed
     * @param appCompatActivity owner of observer
     */
    public void observeResultsData(AppCompatActivity appCompatActivity, RecyclerView recyclerView){
        liveDataResults.observe(appCompatActivity, results -> {
               recyclerView.setAdapter(ResultsAdapter.getInstance(getApplication(), results));
        });
    }
}
