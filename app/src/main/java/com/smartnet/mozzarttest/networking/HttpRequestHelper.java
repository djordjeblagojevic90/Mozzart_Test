package com.smartnet.mozzarttest.networking;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.smartnet.mozzarttest.R;
import com.smartnet.mozzarttest.networking.networkingModels.KinoModel;
import com.smartnet.mozzarttest.networking.networkingModels.Results;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Helper class that provide us HttpRequests with RetrofitClient class
 */
public class HttpRequestHelper {

    public static HttpRequestHelper instance;
    private final String TAG = "HttpRequestHelper";
    private RetrofitClient retrofitClient;
    private final int GAME_ID = 1100;
    private MutableLiveData<ArrayList<KinoModel>> kinoList;
    private MutableLiveData<Results> resultsList;
    private Context context;


    /**
     * Constructor of HttpRequestHelper class
     */
    public HttpRequestHelper(Context context) {
        this.context = context;
        Log.i(TAG, "HttpRequestHelper is created");
        this.retrofitClient = RetrofitClient.getInstance();
    }

    public static HttpRequestHelper getInstance(Context context) {
        if (instance == null) {
            instance = new HttpRequestHelper(context);
        }
        return instance;
    }


    /**
     * Helper method to provide KinoModels from server
     */
    public void provideKinoModels() {
        retrofitClient.compositeDisposable.add(retrofitClient.retrofitClientInterface.provideKinoModels(GAME_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(kinoModels -> getKinoList().postValue(kinoModels), throwable -> showErrorMessageAsToast()));
    }


    /**
     * Provide results from server
     */
    public void provideResults(){
        retrofitClient.compositeDisposable.add(retrofitClient.retrofitClientInterface.provideResults(GAME_ID, provideTime(), provideTime())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(results -> getResultsList().postValue(results), throwable -> Log.e(TAG, throwable.getLocalizedMessage())));
    }

    /**
     * Helper method to provide converted string with date time
     * @return
     */
    private String provideTime(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        Format formater = new SimpleDateFormat("YYYY-MM-dd");
        String formatedDate = formater.format(date);
        return formatedDate;
    }

    /**
     * Helper to show message when http request err happend
     */
    private void showErrorMessageAsToast(){
        Toast.makeText(context, context.getString(R.string.http_err), Toast.LENGTH_LONG);
    }

    /**
     * This method create MutableLiveData if its null or retrieve instance of list if its not, this help us to put all kino models from server in that list
     * @return instance of MutableLiveData
     */
    public MutableLiveData<ArrayList<KinoModel>> getKinoList() {
        if (kinoList == null) {
            kinoList = new MutableLiveData<>();
        }
        return kinoList;
    }

    /**
     * This method create MutableLiveData if its null or retrieve instance of list if its not, this help us to put all results models from server in that list
     * @return
     */
    public MutableLiveData<Results> getResultsList() {
        if(resultsList == null){
            resultsList = new MutableLiveData<>();
        }
        return resultsList;
    }
}
