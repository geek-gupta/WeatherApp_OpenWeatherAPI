package com.company.gaurav.weather.models;

import java.util.ArrayList;

/**
 * Created by gaurav on 11/12/17.
 */

public class ResultModel {

    private ArrayList<WeatherModel> weather;
    private MainModel mainModel;
    private String name;


    public ArrayList<WeatherModel> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<WeatherModel> weather) {
        this.weather = weather;
    }

    public ResultModel(ArrayList<WeatherModel> weather, MainModel mainModel, String name) {

        this.weather = weather;

        this.mainModel = mainModel;

        this.name = name;
    }


    public MainModel getMainModel() {
        return mainModel;
    }

    public void setMainModel(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
