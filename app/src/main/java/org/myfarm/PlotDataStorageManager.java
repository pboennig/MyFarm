package org.myfarm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jakeglass on 1/30/16.
 */
public class PlotDataStorageManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DICTIONARY_TABLE_NAME = "dictionary";
    private static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                    "KEYWORD" + " TEXT, " +
                    "KEYDEFINITION" + " TEXT);";

    DictionaryOpenHelper(Context context) {
        super(context, "STORAGEDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_TABLE_CREATE);
    }

    //Apparently overriding this function is required unless this class is declared in abstract
    @Override
    public abstract void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        super.onUpgrade(db,oldVersion,newVersion);
    }

    public void getPlots(String filter){
        //filter not required
        if(filter != "*"){

        }
    }
}
