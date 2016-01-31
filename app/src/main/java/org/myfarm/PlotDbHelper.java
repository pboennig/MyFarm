package org.myfarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;

/**
 * Created by jakeglass on 1/31/16.
 */
public class PlotDbHelper extends SQLiteOpenHelper {

    public SQLiteDatabase db;

    //init
    @Override
    public void onCreate(SQLiteDatabase db){
        db = this.getWritableDatabase();
        db.execSQL(DICTIONARY_TABLE_CREATE);

        //return (db == null) ? false:true;
    }

    public static final String DICTIONARY_TABLE_NAME = "PLOTS";

    private static final int DATABASE_VERSION = 2;
    private static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                    "KEYWORD" + " TEXT, " +
                    "KEYDEFINITION" + " TEXT);";


    //public initializer
    public PlotDbHelper(Context context) {
        super(context, "database.db", null, 1);
    }

    public final String[] columns = {"NAME",
            "COORDINATES",
            "FERTILIZER_TYPE",
            "PLOT_AREA",
            "FERTILIZER_QUANTITY",
            "SOIL_TYPE",
            "WATER_QUANTITY",
            "CARE_HISTORY",
            "NOTES"};

    //Apparently overriding this function is required unless this class is declared in abstract
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public Cursor getPlots(String filter){
        //filter not required; wildcard (*) is all plots in db
        if(filter == "*" || filter == ""){

            Cursor resultCursor = db.query(DICTIONARY_TABLE_NAME, columns, null, null, null, null, null);
        /*TODO: add filters and order for querying rows in raw SQL*/
            return resultCursor;

        }
        else{
            return null;
        }
    }
    public void editPlot(Plot plot){
        //filter not required; wildcard (*) is all plots in db
        if(plot != null){
            Gson gson = new Gson();

            String latCoorString = gson.toJson(plot.latCoordinates);
            String longCoorString = gson.toJson(plot.longCoordinates);
            String careHistoryString = gson.toJson(plot.careHistory);

            ContentValues values = new ContentValues();
            values.put("NAME",plot.plotName);
            values.put("LAT_COORDINATES",latCoorString);
            values.put("LONG_COORDINATES",longCoorString);
            values.put("FERTILIZER_TYPE",plot.fertilizerType);
            values.put("PLOT_AREA",plot.plotArea);
            values.put("FERTILIZER_QUANTITY",plot.fertilizerQuantity);
            values.put("SOIL_TYPE",plot.soilType);
            values.put("WATER_QUANTITY",plot.waterQuantity);
            values.put("CARE_HISTORY",careHistoryString);
            values.put("NOTES",plot.notes);

            db.insert(DICTIONARY_TABLE_NAME,null,values);
            /*TODO: add filters and order for querying rows in raw SQL*/

        }
    }

}
