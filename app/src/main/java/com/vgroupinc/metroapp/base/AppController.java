package com.vgroupinc.metroapp.base;

import android.app.Application;

import com.vgroupinc.metroapp.base.bean.Station;
import com.vgroupinc.metroapp.database.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by DELL on 11/7/2017.
 */

public class AppController extends Application {

    private static AppController appControllerInstance;
    private final String DB_NAME = "metroData.sqlite";
    public ArrayList<Station> stations = new ArrayList<>();

    public static synchronized AppController getInstance() {
        return appControllerInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.initialize(this, DB_NAME);
        appControllerInstance = this;
    }

}
