package com.smartnet.mozzarttest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.smartnet.mozzarttest.R;
import com.smartnet.mozzarttest.networking.networkingModels.Results;
import com.smartnet.mozzarttest.ui.TableResultsNumber;

import java.util.ArrayList;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.MyViewHolder> {

    private Context context;
    private Results results;
    private static ResultsAdapter instance;
    private boolean populateUi = false;

    public ResultsAdapter(Context context, Results results) {
        this.context = context;
        this.results = results;
    }

    public static ResultsAdapter getInstance(Context context, Results results) {
        instance = new ResultsAdapter(context, results);
        return instance;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.results_adapter, parent, false);
        return new ResultsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.linearLayout.removeAllViews();
        holder.linearLayout.addView(holder.result_time_info);
        holder.linearLayout.addView(provideTableResultsNumber(results.getContent().get(position).getWinningNumbers().getList()));
        holder.result_time_info.setText(provideText(position));

    }

    private String provideText(int position) {
        return "Vreme izvlacenja: " + results.getContent().get(position).provideFormatedTime() + " | Kolo: " + results.getContent().get(position).getDrawId();
    }

    public TableResultsNumber provideTableResultsNumber(ArrayList<Integer> winingNumber) {
        return new TableResultsNumber(context, 5, 4, winingNumber);
    }


    @Override
    public int getItemCount() {
        return results.getContent().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayoutCompat linearLayout;
        private TextView result_time_info;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.results_adapter_root);
            result_time_info = itemView.findViewById(R.id.result_time_info);
        }
    }
}
