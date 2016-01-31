package org.myfarm;

import android.app.*;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
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
import android.widget.PopupWindow;

import com.google.android.gms.maps.MapFragment;

public class MainActivity extends AppCompatActivity implements MapsActivity.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MapsActivity.class.getSimpleName();

    private MapsActivity mapFragment = new MapsActivity();
    private ListActivity listFragment = new ListActivity();

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
            fragmentTransaction.add(R.id.main_bar_view, mapFragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_list) {
            FragmentTransaction fragmentTransaction =  getFragmentManager().beginTransaction();
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

        //end plotting in the Shard and get the result object, which contains plots, plot_areas, a List<PolygonOptions> and List<Double>
        Object[] plotResultsObject = mapFragment.endPlotting();

        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View inflatedView = layoutInflater.inflate(R.layout.plot_edit_popover, null, false);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        PopupWindow popWindow = new PopupWindow(inflatedView, size.x - 50,size.y - 500, true);

        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);
        popWindow.setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));        //popWindow.setAnimationStyle(android.R.anim.an); // call this before showing the popup

        popWindow.showAtLocation(findViewById(R.id.main_bar_view), Gravity.BOTTOM, 0, 150);  // 0 - X postion and 150 - Y position


        return true;
    }

    public boolean startPlotting(MenuItem v){
        mapFragment.startPlotting();
        return true;
    }

    public boolean savePlotInfo(View v){
        //TODO: save things the user entered to DB
        return true;
    }
    public boolean editCurrentPlot(MenuItem v){
        //TODO: implement something here!
        return true;
    }

}
