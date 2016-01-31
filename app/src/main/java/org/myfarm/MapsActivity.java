package org.myfarm;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.*;
import android.graphics.*;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.*;
import android.view.*;
import com.google.android.gms.location.*;
import android.location.*;
import android.content.*;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.*;
import com.google.maps.android.SphericalUtil;
import java.util.*;
import android.util.Log;
import com.google.android.gms.location.LocationRequest;
import android.support.v7.widget.Toolbar;



//import gms.drive.*;
import android.support.v4.app.FragmentActivity;

public class MapsActivity extends MapFragment implements OnMapReadyCallback, ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private double currentLat;
    private double currentLon;
    private double lastLat;
    private double lastLon;
    PolygonOptions plotOptions = new PolygonOptions();
    //List<LatLng> latLngs = new ArrayList<>();
    private static final String TAG = MapsActivity.class.getSimpleName();
    private Location mCurrentLocation;
    LocationRequest mLocationRequest = new LocationRequest();
    List<PolygonOptions> plots = new ArrayList<PolygonOptions>();
    private int plotCount = 0;
    List<Double> plot_areas= new ArrayList<Double>();


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
    /*public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"111");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_maps, container, false);
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ADD BUTTONS TO TOOLBAR
        //Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        //getActivity().findViewById(R.id.add_plot_bar_button).setVisibility(View.VISIBLE);
        //getActivity().findViewById(R.id.edit_plot_bar_button).setVisibility(View.VISIBLE);

        //setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        this.getMapAsync(this);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this.getActivity().getBaseContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
       //locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,this);
        setHasOptionsMenu(true);

    }
    /*public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_maps, container, false);
    }*/

    //inflate the menu
    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        inflater.inflate(R.menu.map_menu, menu);
    }

    public void onConnectionSuspended (int t) {

    }
    public void onConnectionFailed (ConnectionResult t) {

    }
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        currentLat = location.getLatitude();
        currentLon = location.getLongitude();
        Log.d(TAG, currentLon + "");
        Log.d(TAG, currentLat+"");
    }
    @Override
    public void onConnected(Bundle connectionHint) {

            LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            currentLat = mLastLocation.getLatitude();
            currentLon = mLastLocation.getLongitude();
        }
    }
    /**private void refreshConnection() {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            currentLat = mLastLocation.getLatitude();
            currentLon = mLastLocation.getLongitude();
            Log.d(TAG, currentLon+"");
            Log.d(TAG, currentLat+"");
        }
    }

     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera
        LatLng myanmar = new LatLng(21.3423062, 95.0626723);
        mMap.addMarker(new MarkerOptions().position(myanmar).title("Marker in Myanmar"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myanmar));

        Polygon lot1 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(37.412634,-122.0711922), new LatLng(37.412958,-122.0711872), new LatLng(37.413051,-122.0736182), new LatLng(37.412751,-122.0736742))
                .strokeColor(Color.YELLOW)
                .fillColor(Color.argb(30,200,0,0)));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                plots.get(plotCount).add(point);
                //latLngs.add(point);
                mMap.addMarker(new MarkerOptions().position(point));
                /*mMap.clear();
                mMap.addMarker(new MarkerOptions().position(point));*/
            }
        });
        plots.add(new PolygonOptions());
// Get back the mutable Polygon
    }

    public void addPlot(MenuItem v) {
        //refreshConnection();
        /*if (!(currentLat == lastLat && currentLon == lastLon)) {
            plotOptions.add(new LatLng(currentLat, currentLon));
            latLngs.add(new LatLng(currentLat, currentLon));
            lastLon = currentLon;
            lastLat = currentLat;
        }
        mMap.addMarker(new MarkerOptions().position(new LatLng(currentLat, currentLon)));
        Log.d(TAG, currentLat + "");
        Log.d(TAG, currentLon + "");
        Log.d(TAG, latLngs.toString());*/
        plots.get(plotCount).strokeColor(Color.argb(90,200,0,0));
        plots.get(plotCount).fillColor(Color.argb(30, 200, 0, 0));
        mMap.addPolygon(plots.get(plotCount));
        plot_areas.add(SphericalUtil.computeArea(plots.get(plotCount).getPoints()));
        Log.d(TAG, SphericalUtil.computeArea(plots.get(plotCount).getPoints()) + "");
        //Log.d(TAG, plots.get(plotCount).getPoints() + "");
        Log.d(TAG, plot_areas.toString());
        plotCount+=1;
        plots.add(new PolygonOptions());


    }
    public void plotPolygon(View v) {
        plotOptions.strokeColor(Color.RED);
        mMap.addPolygon(plotOptions);
        mMap.addPolygon(plotOptions);

        //Log.d(TAG, SphericalUtil.computeArea(latLngs)+"");
    }

    public void editCurrentPlot(){

    }


}
