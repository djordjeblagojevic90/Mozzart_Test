package com.smartnet.mozzarttest.networking.networkingInterface;

import com.smartnet.mozzarttest.networking.networkingModels.KinoModel;
import com.smartnet.mozzarttest.networking.networkingModels.Results;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitClientInterface {


    @GET("draws/v3.0/{gameId}/upcoming/20")
    Observable<ArrayList<KinoModel>> provideKinoModels(@Path("gameId") int gameId);


    @GET("draws/v3.0/{gameId}/draw-date/{fromDate}/{toDate}")
    Observable<Results> provideResults(@Path("gameId") int gameId, @Path("fromDate") String fromDate, @Path("toDate") String toDate);

}
