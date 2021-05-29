package com.project.easypark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String LOCATION_TABLE = "LOCATION_TABLE";
    public static final String ADDRESS = "ADDRESS";
    public static final String LATITUDE = "LATITUDE";
    public static final String LONGITUDE = "LONGITUDE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "location.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable= "CREATE TABLE " + LOCATION_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,TIME DATETIME DEFAULT CURRENT_TIMESTAMP ," + ADDRESS + " TEXT," +
                LATITUDE + " DOUBLE, " + LONGITUDE + " DOUBLE )";
        db.execSQL(createTable);

    }
    public List<LocationModel> getAll(){
        List<LocationModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM "+LOCATION_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            //loop through results, put them into return list
            do{
                int locationId = cursor.getInt(0);
                String datetime = cursor.getString(1);
                String address = cursor.getString(2);
                double longitude = cursor.getDouble(3);
                double latitude = cursor.getDouble(4);

                LocationModel newLocation = new LocationModel(locationId,datetime, new LatLng(latitude,longitude),address);
                returnList.add(newLocation);
            }while (cursor.moveToNext());
        }else{

        }
        cursor.close();
        db.close();
        return returnList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  boolean addOne(LocationModel locationModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ADDRESS,locationModel.getAddress());
        cv.put(LATITUDE,locationModel.getLatLng().latitude);
        cv.put(LONGITUDE,locationModel.getLatLng().longitude);
        long insert = db.insert(LOCATION_TABLE,null,cv);
        if(insert == -1){
            return false;
        }
        else
            return true;
    }
}
