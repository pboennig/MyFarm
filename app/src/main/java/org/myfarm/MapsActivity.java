package com.mihirtrivedi.farm;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.*;
import android.graphics.*;
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


//import gms.drive.*;
import android.support.v4.app.FragmentActivity;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private double currentLat;
    private double currentLon;
    private double lastLat;
    private double lastLon;
    PolygonOptions plotOptions = new PolygonOptions();
    List<LatLng> latLngs = new ArrayList<>();
    private static final String TAG = MapsActivity.class.getSimpleName();
    private Location mCurrentLocation;
    LocationRequest mLocationRequest = new LocationRequest();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Button addPoint = (Button)findViewById(R.id.plot_point);
        addPoint.setText("point");
        Button addPlot = (Button)findViewById(R.id.plot_polygon);
        addPlot.setText("plot");
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
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

    }
    public void onConnectionSuspended (int t) {

    }
    public void onConnectionFailed (ConnectionResult t) {

    }
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
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
    private void refreshConnection() {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            currentLat = mLastLocation.getLatitude();
            currentLon = mLastLocation.getLongitude();
            Log.d(TAG, currentLon+"");
            Log.d(TAG, currentLat+"");
        }
    }
    /**
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

        Polygon polygon2 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(0, 0), new LatLng(0, 5), new LatLng(3, 5), new LatLng(0, 0))
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));

// Get back the mutable Polygon
    }
    public void plotPoint(View v) {
        //refreshConnection();
        if (!(currentLat == lastLat && currentLon == lastLon)) {
            plotOptions.add(new LatLng(currentLat, currentLon));
            latLngs.add(new LatLng(currentLat, currentLon));
            lastLon = currentLon;
            lastLat = currentLat;
        }
        mMap.addMarker(new MarkerOptions().position(new LatLng(currentLat, currentLon)));
        Log.d(TAG, currentLat + "");
        Log.d(TAG, currentLon + "");
        Log.d(TAG, latLngs.toString());


    }
    public void plotPolygon (View v) {
        plotOptions.strokeColor(Color.RED);
        mMap.addPolygon(plotOptions);
        mMap.addPolygon(plotOptions);
        Log.d(TAG, SphericalUtil.computeArea(latLngs)+"");
    }

}
