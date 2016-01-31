package org.myfarm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Array;

/**
 * Created by jakeglass on 1/30/16.
 */
public class PlotDataStorageManager extends SQLiteOpenHelper {
    private static String[] columns = {"NAME",
                                        "COORDINATES",
                                        "FERTILIZER_TYPE",
                                        "FERTILIZER_QUANTITY",
                                        "SOIL_TYPE",
                                        "WATER_QUANTITY",
                                        "CARE_HISTORY",
                                        "NOTES"};

    private static final int DATABASE_VERSION = 2;
    private static final String DICTIONARY_TABLE_NAME = "dictionary";
    private static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                    "KEYWORD" + " TEXT, " +
                    "KEYDEFINITION" + " TEXT);";

    /*private void DictionaryOpenHelper(Context context) {
        super(context, "STORAGEDB", null, DATABASE_VERSION);
    }*/

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
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public void getPlots(String filter){
        //filter not required; wildcard (*) is all plots in db
        if(filter == "*" || filter == ""){


            SQLiteDatabase readableDatabase = this.getReadableDatabase();
            Cursor resultCursor = readableDatabase.query("PLOTS",columns,null,null,null,null,null);
            /*TODO: add filters and order for querying rows in raw SQL*/
        }
    }
    public void editPlot(String filter){
        //filter not required; wildcard (*) is all plots in db
        if(filter == "*" || filter == ""){
            SQLiteDatabase readableDatabase = this.getReadableDatabase();
            Cursor resultCursor = readableDatabase.query("PLOTS", columns,null,null,null,null,null);
                                                                /*TODO: add filters and order for querying rows in raw SQL*/
        }
    }
}
