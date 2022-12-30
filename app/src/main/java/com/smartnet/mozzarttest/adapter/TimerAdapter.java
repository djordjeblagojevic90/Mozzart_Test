package com.smartnet.mozzarttest.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartnet.mozzarttest.R;
import com.smartnet.mozzarttest.TalonActivity;
import com.smartnet.mozzarttest.db.LocalDbHelper;
import com.smartnet.mozzarttest.networking.networkingModels.KinoModel;

import java.util.ArrayList;

public class TimerAdapter extends RecyclerView.Adapter<TimerAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<KinoModel> kinoList;
    private static TimerAdapter instance;
    private final String TAG = "TimerAdapter";
    private boolean update = false;
    private Handler handler;
    private Runnable runnable;

    public TimerAdapter(Context context, ArrayList<KinoModel> kinoList) {
        this.context = context;
        this.kinoList = kinoList;
    }


    public static TimerAdapter getInstance(Context context, ArrayList<KinoModel> kinoList) {
        if (instance == null) {
            instance = new TimerAdapter(context, kinoList);
        }
        return instance;
    }

    @NonNull
    @Override
    public TimerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.timer_recycler_layout, parent, false);
        return new TimerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimerAdapter.MyViewHolder holder, int position) {
        holder.txt_time.setText(kinoList.get(position).provideFormatedDrawTimeDate());
        holder.txt_remaining_time.setText(kinoList.get(position).provideFormatedReamingDrawTimeDate());
        updateListRemaingTime(position);

    }

    private void updateListRemaingTime(int position) {
        handler = new Handler();
        runnable = () -> notifyItemChanged(position);
        handler.postDelayed(runnable, 1000);
    }

    @Override
    public int getItemCount() {
        return kinoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt_time;
        public TextView txt_remaining_time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txt_time = itemView.findViewById(R.id.txt_time);
            txt_remaining_time = itemView.findViewById(R.id.txt_remaining_time);
        }

        public TextView getTxt_remaining_time() {
            return txt_remaining_time;
        }

        @Override
        public void onClick(View view) {
            if (getTxt_remaining_time().getText().equals("Times out")) {
                Toast.makeText(context, "This draw is out, please select another one", Toast.LENGTH_SHORT).show();
            } else {
                LocalDbHelper localDbHelper = LocalDbHelper.getInstance(context);
                if (localDbHelper.integerList.contains(kinoList.get(getAdapterPosition()).getDrawId())) {
                    Toast.makeText(context, "You already submited combination for this draw", Toast.LENGTH_SHORT).show();
                } else {
                    changeScreen();
                }
            }
        }

        private void changeScreen(){
            Intent intent = new Intent(context, TalonActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("drawTime", kinoList.get(getAdapterPosition()).provideFormatedDrawTimeDate());
            intent.putExtra("drawTimeInMills", kinoList.get(getAdapterPosition()).getDrawTime());
            intent.putExtra("drawId", kinoList.get(getAdapterPosition()).getDrawId());
            context.startActivity(intent);
            Log.e(TAG, "itemView on position " + getAdapterPosition() + " is clicked");
        }
    }
}
