package com.company.gaurav.weather.api_calls;

import com.company.gaurav.weather.models.ResultModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaurav on 11/12/17.
 */

public interface WeatherApi {

    @GET("weather?")
    Call<ResultModel> getWeatherDetail(@Query("q") String query, @Query("appid") String appid);
}
