package com.vgroupinc.metroapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.vgroupinc.metroapp.base.bean.Station;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 16;
    static Cursor cursor = null;
    private static SQLiteDatabase sqliteDb;
    private static DatabaseHelper instance;
    private Context context;

    private DatabaseHelper(Context context, String name, CursorFactory factory,
                           int version) {
        super(context, name, factory, version);
        this.context = context;

    }

    public static void initialize(Context context, String databaseName) {
        if (instance == null) {
            if (!checkDatabase(context, databaseName)) {

                try {
                    copyDataBase(context, databaseName);
                } catch (IOException e) {

                    System.out.println(databaseName
                            + " does not exists ");
                }
            }

            instance = new DatabaseHelper(context, databaseName, null,
                    DATABASE_VERSION);
            sqliteDb = instance.getWritableDatabase();
//            sqliteDb = instance.getReadableDatabase();

            System.out.println("instance of  " + databaseName + " created ");
        }
    }

    public static final DatabaseHelper getInstance(Context context, String databaseName) {
        if (instance == null) {
            initialize(context, databaseName);
        }
        return instance;
    }

    public static final DatabaseHelper getInstance() {
        return instance;
    }

    private static void copyDataBase(Context aContext, String databaseName)
            throws IOException {

        InputStream myInput = aContext.getAssets().open(databaseName);

        String outFileName = getDatabasePath(aContext, databaseName);

        File f = new File("/data/data/" + aContext.getPackageName()
                + "/databases/");
        if (!f.exists())
            f.mkdir();

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

        System.out.println(databaseName + " copied");
    }

    public static boolean checkDatabase(Context aContext, String databaseName) {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = getDatabasePath(aContext, databaseName);

            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);

            checkDB.close();
        } catch (SQLiteException e) {

            System.out.println(databaseName + " does not exists");
        }

        return checkDB != null ? true : false;
    }

    private static String getDatabasePath(Context aContext, String databaseName) {
        return "/data/data/" + aContext.getPackageName() + "/databases/"
                + databaseName;
    }

    public static Cursor rawQuery(String query) {
        try {
            if (sqliteDb.isOpen()) {
                sqliteDb.close();
            }
            sqliteDb = instance.getWritableDatabase();

            cursor = null;
            cursor = sqliteDb.rawQuery(query, null);
        } catch (Exception e) {
            System.out.println("DB ERROR  " + e.getMessage());
            e.printStackTrace();
        }
        return cursor;
    }

    public static void execute(String query) {
        try {
            if (sqliteDb.isOpen()) {
                sqliteDb.close();
            }
            sqliteDb = instance.getWritableDatabase();
            sqliteDb.execSQL(query);
        } catch (Exception e) {
            System.out.println("DB ERROR  " + e.getMessage());
            e.printStackTrace();
        }
    }

    public SQLiteDatabase getDatabase() {
        return sqliteDb;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public void search() {
        // FIXME: 11/9/2017
        ArrayList<Station> stations = new ArrayList<>();
        Cursor cursor = sqliteDb.query(
                DbConst.TABLE_STATIONS,
                new String[]{DatabaseConstants.STATION_ID, DatabaseConstants.STATION_NAME, DatabaseConstants.STATION_NAME_HINDI, DatabaseConstants.LATITUDE, DatabaseConstants.LONGITUDE}, null, null, null, null, null);
//        if (cursor!=null){
//            cursor.moveToFirst();
//            while(cursor.moveToNext()){
//                Station stationBean=new Station();
////                stationBean.setStation_id(cursor.);
//            }
//        }
    }

//    public boolean insert() {
//        ContentValues cv = new ContentValues();
//
//        return sqliteDb.insert("Product", null, cv) > 0;
//    }


    private class DbConst {
        public static final String STATION_ID = "station_id";
        public static final String STATION_NAME = "station_name";
        public static final String STATION_NAME_HINDI = "station_hindi_name";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String TABLE_STATIONS = "tbl_stations";
        public static final String TABLE_ROUTEMATRIX = "tbl_routematrix";
        public static final String TABLE_FARE = "tbl_fare";
    }

}
