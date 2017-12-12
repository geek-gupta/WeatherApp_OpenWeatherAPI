package com.company.gaurav.weather.models;

/**
 * Created by gaurav on 12/12/17.
 */

public class MainModel {

    private String temp;
    private String pressure;
    private String humidity;

    public MainModel(String temp, String pressure, String humidity) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
