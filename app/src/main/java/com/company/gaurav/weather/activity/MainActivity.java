package com.company.gaurav.weather.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.gaurav.weather.R;
import com.company.gaurav.weather.api_calls.WeatherApi;
import com.company.gaurav.weather.models.ResultModel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private TextView locationName,locationTemperature
            ,locationPressure,locationHumidity,locationWeatherType,locationWeatherInfo;
    private ImageView weatherTypeIcon;
    private EditText enterLocationName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initializeVars();

        enterLocationName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(enterLocationName.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    init();
                    return true;
                }
                return false;
            }
        });



    }

    public void init(){

        Gson gson = new Gson();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        final WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        final Call<ResultModel> resultModelCall = weatherApi.getWeatherDetail(enterLocationName.getText().toString(),
                                                                                "6244d976b3ab9d0bb6c1f916e8698de8");
        resultModelCall.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {

                if(response.isSuccessful()) {
                    locationName.setText(response.body().getName());
                    locationHumidity.setText(response.body().getMainModel().getHumidity() + "(Humidity)");
                    locationPressure.setText(response.body().getMainModel().getPressure() + "(Pressure)");
                    locationTemperature.setText(response.body().getMainModel().getTemp() + "(Temperature)");
                    locationWeatherInfo.setText(response.body().getWeather().get(0).getDescription());
                    locationWeatherType.setText(response.body().getWeather().get(0).getMain());
                    Picasso.with(getApplicationContext()).
                            load("http://openweathermap.org/img/w/" + response.body().getWeather().
                                    get(0).getIcon() + ".png").into(weatherTypeIcon);

                }else{
                   enterLocationName.setError("City Not Found");
                }


            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {
               //Handle it on failure.
            }
        });
    }


    public void initializeVars(){

        enterLocationName = (EditText) findViewById(R.id.enter_location);
        locationName = (TextView) findViewById(R.id.location_name);
        locationHumidity = (TextView) findViewById(R.id.humidity);
        locationPressure = (TextView) findViewById(R.id.pressure);
        locationTemperature = (TextView) findViewById(R.id.temperature);
        locationWeatherType = (TextView) findViewById(R.id.weather_type_text);
        locationWeatherInfo = (TextView) findViewById(R.id.information_text);
        weatherTypeIcon = (ImageView) findViewById(R.id.weatherIcon);


    }
}
