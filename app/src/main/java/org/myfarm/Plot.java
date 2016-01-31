package org.myfarm;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Array;

/**
 * Created by jakeglass on 1/30/16.
 */
public abstract class Plot extends ContentProvider{
    public Array coordinates;
    public String plotName;
    public String fertilizerType;
    public String fertilizerQuantity;
    public String soilType;
    public String waterQuantity;
    public Array careHistory;
    public String notes;
    private String[] plotKeys = {"NAME",
            "COORDINATES",
            "FERTILIZER_TYPE",
            "FERTILIZER_QUANTITY",
            "SOIL_TYPE",
            "WATER_QUANTITY",
            "CARE_HISTORY",
            "NOTES"};
    public final String[] columns = {"NAME",
            "COORDINATES",
            "FERTILIZER_TYPE",
            "FERTILIZER_QUANTITY",
            "SOIL_TYPE",
            "WATER_QUANTITY",
            "CARE_HISTORY",
            "NOTES"};
    public Array plotValues;
    public static final String DICTIONARY_TABLE_NAME = "PLOTS";

    private SQLiteDatabase db;

    //init
    @Override
    public boolean onCreate(){
        db = new PlotDataStorageManager(getContext()).getWritableDatabase();
        return (db == null) ? false:true;
    }

    public class PlotDataStorageManager extends SQLiteOpenHelper {


        private static final int DATABASE_VERSION = 2;
        private static final String DICTIONARY_TABLE_CREATE =
                "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                        "KEYWORD" + " TEXT, " +
                        "KEYDEFINITION" + " TEXT);";

        //mandatory implementation for SQLiteOpenHelper abstract class
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DICTIONARY_TABLE_CREATE);
        }

        //public initializer
        public PlotDataStorageManager(Context context) {
            super(context, "database.db", null, 1);
        }

        //Apparently overriding this function is required unless this class is declared in abstract
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

    }

    public void getPlots(String filter){
        //filter not required; wildcard (*) is all plots in db
        if(filter == "*" || filter == ""){

        Cursor resultCursor = db.query(DICTIONARY_TABLE_NAME, columns, null, null, null, null, null);
        /*TODO: add filters and order for querying rows in raw SQL*/
        }
    }
    public void editPlot(Array plot){
        //filter not required; wildcard (*) is all plots in db
        if(plot != null){
            ContentValues values = new ContentValues();
            for(int i=0;i<plotKeys.length;i++){
          //      values.put(plotKeys[i],plotValues[i]);
            }

            db.insert(DICTIONARY_TABLE_NAME,null,Plot);
            /*TODO: add filters and order for querying rows in raw SQL*/

        }
    }
}