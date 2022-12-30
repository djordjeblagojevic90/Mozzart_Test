package com.smartnet.mozzarttest;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartnet.mozzarttest.viewModel.ResultsViewModel;

public class ResultsOfDrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_layout);

        initResultsViewModel();
    }


    @Override
    public void onBackPressed() {
        finishAndRemoveTask();
        super.onBackPressed();
    }

    /**
     * Helper to init Results view model
     */
    public void initResultsViewModel() {
        ResultsViewModel resultsViewModel = new ViewModelProvider(this).get(ResultsViewModel.class);
        resultsViewModel.colectResultsData();
        resultsViewModel.observeResultsData(this, provideResultsRecycler());
    }

    /**
     * Helper method to provide recycler view
     *
     * @return recycler view
     */
    private RecyclerView provideResultsRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recycler_results);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.getItemAnimator().setChangeDuration(0);
        return recyclerView;
    }
}
