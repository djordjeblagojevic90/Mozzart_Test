package com.smartnet.mozzarttest.networking;

import android.util.Log;

import com.smartnet.mozzarttest.networking.networkingInterface.RetrofitClientInterface;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static RetrofitClient instance;
    private final String BASE_URL = "https://api.opap.gr/";
    private final String TAG = "RetrofitClient";
    public Retrofit client;
    public CompositeDisposable compositeDisposable;
    public RetrofitClientInterface retrofitClientInterface;


    /**
     * Constructor of RetrofitClient class
     */
    public RetrofitClient() {
        initComponents();
        Log.i(TAG, "RetrofitClient is created");
    }

    /**
     * Get method to return single instance of RetrofitClient class
     *
     * @return single instance of RetrofitClient class
     */
    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }


    /**
     * Helper method to init components for networking
     */
    private void initComponents() {
        client = provideRetrofitClient(BASE_URL);
        compositeDisposable = new CompositeDisposable();
        retrofitClientInterface = client.create(RetrofitClientInterface.class);
    }


    /**
     * Helper to init retrofit client
     *
     * @return instance of retrofit client
     */
    private Retrofit provideRetrofitClient(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideOkHttpClient())
                .build();
    }

    /**
     * Helper method to provide OkHttpClient
     *
     * @return instance of OkHttpClient
     */
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }
}
