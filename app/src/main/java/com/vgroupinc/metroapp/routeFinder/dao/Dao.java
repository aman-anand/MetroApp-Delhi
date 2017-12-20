package com.vgroupinc.metroapp.routeFinder.dao;

import android.database.Cursor;
import android.util.Log;

import com.vgroupinc.metroapp.Utils.Utils;
import com.vgroupinc.metroapp.base.AppController;
import com.vgroupinc.metroapp.base.bean.Station;
import com.vgroupinc.metroapp.database.DatabaseConstants;
import com.vgroupinc.metroapp.database.DatabaseHelper;
import com.vgroupinc.metroapp.routeFinder.bean.Journey;

import static android.content.ContentValues.TAG;

/**
 * Created by DELL on 11/13/2017.
 */

public class Dao {

    public void initArrayList() {

        String initilizeQuery = "SELECT " +
                DatabaseConstants.STATION_ID + ", " +
                DatabaseConstants.LINE_ID + ", " +
                DatabaseConstants.STATION_NAME + ", " +
                DatabaseConstants.STATION_NAME_HINDI + ", " +
                DatabaseConstants.LATITUDE + ", " +
                DatabaseConstants.LONGITUDE + " FROM " +
                DatabaseConstants.TABLE_STATIONS;
        Cursor cursor = DatabaseHelper.rawQuery(initilizeQuery);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Station stationBean = new Station();
                stationBean.setStation_id(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.STATION_ID)));
                stationBean.setLine_id(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.LINE_ID)));
                stationBean.setStation_name(cursor.getString(cursor.getColumnIndex(DatabaseConstants.STATION_NAME)));
                stationBean.setStation_hindi_name(cursor.getString(cursor.getColumnIndex(DatabaseConstants.STATION_NAME_HINDI)));
                stationBean.setLatitude(cursor.getString(cursor.getColumnIndex(DatabaseConstants.LATITUDE)));
                stationBean.setLongitude(cursor.getString(cursor.getColumnIndex(DatabaseConstants.LONGITUDE)));
                AppController.getInstance().stations.add(stationBean);
            } while (cursor.moveToNext());
        }
    }

    public Journey findRoute(int from_ID, int to_ID, String from, String to) {
        String result = "";
        String fare = "";
        String runtime = "";

        String query = "SELECT `" +
                to_ID + "` " +
                "FROM " +
                DatabaseConstants.TABLE_ROUTEMATRIX + " " +
                "WHERE " +
                DatabaseConstants.STATION_ID + " = " +
                from_ID + " ";
        String query1 = "SELECT `" +
                to_ID + "` " +
                "FROM " +
                DatabaseConstants.TABLE_FARE + " " +
                "WHERE " +
                DatabaseConstants.STATION_ID + " = " +
                from_ID + " ";
        String query2 = "SELECT `" +
                to_ID + "` " +
                "FROM " +
                DatabaseConstants.TABLE_RUNTIME + " " +
                "WHERE " +
                DatabaseConstants.STATION_ID + " = " +
                from_ID + " ";

        Cursor cursor = DatabaseHelper.rawQuery(query);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            try {
                result = cursor.getString(cursor.getColumnIndex(String.valueOf(to_ID)));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.e(TAG, "Route: " + result);
            cursor.close();
        }
        Cursor cursor1 = DatabaseHelper.rawQuery(query1);
        if (cursor1 != null && cursor1.getCount() > 0) {
            cursor1.moveToFirst();
            try {
                fare = cursor1.getString(cursor1.getColumnIndex(String.valueOf(to_ID)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e(TAG, "fare: " + fare);
            cursor1.close();
        } else {
            fare = "N.A.";
        }
        cursor1 = DatabaseHelper.rawQuery(query2);
        if (cursor1 != null && cursor1.getCount() > 0) {
            cursor1.moveToFirst();
            try {
                runtime = cursor1.getString(cursor1.getColumnIndex(String.valueOf(to_ID)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e(TAG, "runtime: " + runtime);
            cursor1.close();
        } if (cursor == null || runtime.equals("0")||runtime.equals("")) {
            runtime = "N.A.";
        }

        return Utils.getInstance().jCalc(result, fare, runtime, from, to);

    }
}
// TODO: 11/13/2017 regex exp for pattern recog and construct arrayList from it for route