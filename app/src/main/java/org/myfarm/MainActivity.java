package org.myfarm;

import android.app.*;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.*;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.games.internal.PopupManager;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MapsActivity.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MapsActivity.class.getSimpleName();

    private MapsActivity mapFragment = new MapsActivity();
    private ListActivity listFragment = new ListActivity();

    public String fertilizerSpinnerSelection;
    public String cropSpinnerSelection;

    public  PopupWindow popWindow;
    private View plotEditPopoverView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //finish nav layout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;

        if (id == R.id.nav_map) {
            FragmentTransaction fragmentTransaction =  getFragmentManager().beginTransaction();
            fragmentTransaction.remove(mapFragment);
            fragmentTransaction.add(R.id.main_bar_view, mapFragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_list) {
            FragmentTransaction fragmentTransaction =  getFragmentManager().beginTransaction();
            fragmentTransaction.remove(listFragment);
            fragmentTransaction.add(R.id.main_bar_view, listFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_supplies) {

        } else if (id == R.id.nav_yields) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //implement fragment callbacks:
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }

    //implement action handlers into fragments
    public boolean donePlotting(MenuItem v){

        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        plotEditPopoverView = layoutInflater.inflate(R.layout.plot_edit_popover,null);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        popWindow = new PopupWindow(plotEditPopoverView, size.x - 50,size.y - 500, true);

        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);
        popWindow.setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));        //popWindow.setAnimationStyle(android.R.anim.an); // call this before showing the popup

        popWindow.showAtLocation(findViewById(R.id.main_bar_view), Gravity.BOTTOM, 0, 150);  // 0 - X postion and 150 - Y position

        /*//set up fertilizerSpinner
        Spinner fertilizerSpinner;
        try{
            fertilizerSpinner = (Spinner)findViewById(R.id.fertilizer_spinner);
            // Spinner Drop down elements
            List<String> categories = new ArrayList<String>();
            categories.add("Cow");
            categories.add("Chicken");
            categories.add("Sheep");
            categories.add("Swine");
            categories.add("Urea");

            // Creating adapter for spinner
            ArrayAdapter<String> fertilizerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

            fertilizerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Specify the layout to use when the list of choices appears
            fertilizerSpinner.setAdapter(fertilizerAdapter);    // Apply the adapter to the spinner
        } catch (Exception e){
            Log.d("TT","ERROOURURURU : "+e.getLocalizedMessage());
        }

        //set up cropSpinner
        Spinner cropSpinner;
        try{
            cropSpinner = (Spinner)findViewById(R.id.crop_spinner);

            // Spinner Drop down elements
            List<String> categories2 = new ArrayList<String>();
            categories2.add("Corn");
            categories2.add("Rice");
            categories2.add("Grain");
            categories2.add("Soybean");

            // Creating adapter for spinner
            ArrayAdapter<String> cropAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);

            cropAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Specify the layout to use when the list of choices appears
            cropSpinner.setAdapter(cropAdapter);    // Apply the adapter to the spinner
        } catch (Exception e){
            Log.d("TT","ERROOURURURU2222222 : "+e.getLocalizedMessage());
        }
        */

        return true;
    }

    public boolean startPlotting(MenuItem v){
        mapFragment.startPlotting();
        return true;
    }

    public boolean savePlotInfo(View v){

        Log.d("TEST","OOH SAVEPLOTINFO OOH");
        Plot plot = new Plot();

        plot.coordinates = mapFragment.getPoints();
        plot.plotArea = (float) mapFragment.getPlotArea();

        plot.plotName = ((EditText)plotEditPopoverView.findViewById(R.id.plot_name_field)).getText().toString();

        Log.d("TEST", "PLOTNAME IS " + plot.plotName);

        //get values from radio selectors
        int cropRadioId = ((RadioGroup)findViewById(R.id.crop_radio_group)).getCheckedRadioButtonId();
        int fertilizerRadioId = ((RadioGroup)findViewById(R.id.fertilizer_radio_group)).getCheckedRadioButtonId();

        String cropChoice = ((RadioButton)(findViewById(cropRadioId))).getText().toString();
        String fertilizerChoice = ((RadioButton)(findViewById(fertilizerRadioId))).getText().toString();

        plot.crop = cropChoice;
        plot.fertilizerType = fertilizerChoice;

        //dismiss the old popWindow
        popWindow.dismiss();

        //DO CALCULATIONS & show the view plot stats winder'

        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflatedView = layoutInflater.inflate(R.layout.view_plot_stats, null, false);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        popWindow = new PopupWindow(inflatedView, size.x - 50,size.y - 500, true);

        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);
        popWindow.setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));        //popWindow.setAnimationStyle(android.R.anim.an); // call this before showing the popup

        //((TextView)findViewById(R.id.plot_name_display)).setText(plot.plotName);
        Crop crop;
        switch (plot.crop){
            case "Corn":
                crop = Crop.CORN;
                break;
            case "Grain":
                crop = Crop.GRAIN;
                break;
            case "Soybean":
                crop = Crop.SOYBEAN;
                break;
            case "Rice":
                crop = Crop.RICE;
                break;
            default:
                crop = Crop.SOYBEAN;
                break;
        }
        Fertilizer fertilizer;
        switch (plot.fertilizerType){
            case "Urea":
                fertilizer = Fertilizer.UREA;
                break;
            case "Swine":
                fertilizer = Fertilizer.SWINE;
                break;
            case "Chicken":
                fertilizer = Fertilizer.CHICKEN;
            break;
            case "Cow":
                fertilizer = Fertilizer.COW;
                break;
            case "Sheep":
                fertilizer = Fertilizer.SHEEP;
                break;
            default:
                fertilizer = Fertilizer.CHICKEN;
                break;
        }
        //run the calculator
        OptimalValuesCalculator calculator = new OptimalValuesCalculator(plot.plotArea,crop,fertilizer);

        //set all of the views to display the output
        Log.d("TAGGGGGGG",(((Double)calculator.getSeedAmount()).toString())+"SEED FERT"+(((Double)calculator.getFertilizerAmount()).toString()));
        ((TextView)findViewById(R.id.seed_amount_display)).setText(((Double) calculator.getSeedAmount()).toString());
        ((TextView)findViewById(R.id.fertilizer_amount_display)).setText(((Double) calculator.getFertilizerAmount()).toString());
        ((TextView)findViewById(R.id.water_amount_display)).setText(((Double)calculator.getWaterAmount()).toString());
        ((TextView)findViewById(R.id.land_area_display)).setText(((Float) plot.plotArea).toString());
        ((TextView)findViewById(R.id.crop_type_display)).setText(cropChoice);
        ((TextView)findViewById(R.id.fertilizer_type_display)).setText(fertilizerChoice);
        ((TextView)findViewById(R.id.plot_recommendations_field)).setText("Recommendations for "+plot.plotName);

        popWindow.showAtLocation(findViewById(R.id.main_bar_view), Gravity.BOTTOM, 0, 150);  // 0 - X postion and 150 - Y position


        //PlotDbHelper dbHelper = new PlotDbHelper(getApplicationContext());
        //dbHelper.editPlot(plot);

        return true;
    }
    public boolean editCurrentPlot(MenuItem v){
        //TODO: implement something here!
        return true;
    }

}