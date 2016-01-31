package org.myfarm;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakeglass on 1/30/16.
 */
public class Plot {
    public List<LatLng> coordinates;
    public String plotName;
    public String fertilizerType;
    public float fertilizerQuantity;
    public String soilType;
    public float waterQuantity;
    public Array careHistory;
    public String notes;
    public float plotArea;
    public String crop;
}

