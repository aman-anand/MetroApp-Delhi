package com.vgroupinc.metroapp.Utils;

import android.text.TextUtils;

import com.vgroupinc.metroapp.routeFinder.bean.Journey;

import java.util.regex.Pattern;

/**
 * Created by DELL on 11/14/2017.
 */

public class Utils {
    private static Utils utils;

    private Utils() {
    }

    public static Utils getInstance() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    public Journey jCalc(String result, String fare, String runtime, String from, String to) {
        Journey journey = null;
        if (result.length() > 0) {
            String[] parts = result.split(":");
            if (parts.length > 0) {
                String[] stations = parts[0].split("-");
                int[] stationsL = new int[stations.length];
                for (int i = 0; i < stations.length; i++) {
                    try {
                        stationsL[i] = TextUtils.isEmpty(stations[i]) ? 0 : Integer.parseInt(stations[i]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                journey = new Journey();
                journey.setDistance(parts[1]);
                journey.setStationList(stationsL);
                journey.setFare(fare);
                journey.setRuntime(runtime);
                journey.setFrom(from);
                journey.setTo(to);
            }
        }
        return journey;
    }

    public boolean isNull(Object o) {
        boolean b = false;
        if (o == null) {
            b = true;
        }
        return b;
    }
    public static int getMinutesFromFormattedDuration(String duration){
        if(duration==null)
            return 0;
        try{

            Pattern patternDuration = Pattern.compile("\\d+(?::\\d+){0,2}");

            int hours = 0;
            int minutes = 0;
            int seconds = 0;
            if(patternDuration.matcher(duration).matches()){
                String[] tokens = duration.split(":");
                if(tokens.length==1){
                    seconds = Integer.parseInt(tokens[0]);
                }else if(tokens.length == 2){
                    minutes = Integer.parseInt(tokens[0]);
                    seconds = Integer.parseInt(tokens[1]);
                }else{
                    hours = Integer.parseInt(tokens[0]);
                    minutes = Integer.parseInt(tokens[1]);
                    seconds = Integer.parseInt(tokens[2]);
                }

                return 60 * hours +  minutes + seconds/60;
            }else
                return 0;

        }catch (NumberFormatException ignored){
            return 0;
        }

    }
}
