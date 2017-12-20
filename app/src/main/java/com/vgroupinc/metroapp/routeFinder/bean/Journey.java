package com.vgroupinc.metroapp.routeFinder.bean;

import java.io.Serializable;

/**
 * Created by DELL on 11/14/2017.
 */

public class Journey implements Serializable {
    private int[] stationList;
    private String distance;
    private String fare;
    private String runtime;
    private String from;
    private int lineChange;
    private String to;

    public Journey() {

    }

    public int getLineChange() {
        return lineChange;
    }

    public void setLineChange(int lineChange) {
        this.lineChange = lineChange;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public int[] getStationList() {
        return stationList;
    }

    public void setStationList(int[] stationList) {
        this.stationList = stationList;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }
}
