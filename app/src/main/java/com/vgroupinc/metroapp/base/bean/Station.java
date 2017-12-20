package com.vgroupinc.metroapp.base.bean;

import android.text.TextUtils;

/**
 * Created by DELL on 11/9/2017.
 */

public class Station {
    private int station_id;
    private int line_id;
    private String station_name;
    private String station_hindi_name;
    private String latitude;
    private String longitude;

    public Station() {
    }

    public Station(int station_id, int line_id, String station_name, String station_hindi_name, String latitude, String longitude) {
        this.station_id = station_id;
        this.line_id = line_id;

        this.station_name = station_name;
        this.station_hindi_name = station_hindi_name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getLine_id() {
        return line_id;
    }

    public void setLine_id(int line_id) {
        this.line_id = line_id;
    }

    public int getStation_id() {
        return station_id;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    public String getStation_name() {
        if (TextUtils.isEmpty(station_name)) {
            station_name = "N.A";
        }
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getStation_hindi_name() {
        if (TextUtils.isEmpty(station_hindi_name)) {
            station_hindi_name = "N.A";
        }
        return station_hindi_name;
    }

    public void setStation_hindi_name(String station_hindi_name) {
        this.station_hindi_name = station_hindi_name;
    }

    public String getLatitude() {
        if (TextUtils.isEmpty(latitude)) {
            latitude = "N.A";
        }
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        if (TextUtils.isEmpty(longitude)) {
            longitude = "N.A";
        }
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
