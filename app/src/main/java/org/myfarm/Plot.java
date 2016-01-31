package org.myfarm;

import java.sql.Array;

/**
 * Created by jakeglass on 1/30/16.
 */
public class Plot{
    public Array coordinates;
    public String plotName;
    public String fertilizerType;
    public String fertilizerQuantity;
    public String soilType;
    public String waterQuantity;
    public Array careHistory;
    public String notes;

    //init
    public Plot(){

    }
    /*//SQL DEFINITION:

    ["NAME",
            "COORDINATES",
            "FERTILIZER_TYPE",
            "FERTILIZER_QUANTITY",
            "SOIL_TYPE",
            "WATER_QUANTITY",
            "CARE_HISTORY"]);
    */
}
