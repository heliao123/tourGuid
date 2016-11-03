package com.tourguide.geofind;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        AdapterView.OnItemSelectedListener, GoogleMap.OnPoiClickListener {

    private GoogleMap mMap;
    LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Spinner spinner = (Spinner) findViewById(R.id.google_map_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.google_map_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
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

        boolean isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED && isNetworkEnabled && isGPSEnabled){
            Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            LatLng ll = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(ll).title("Current Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 15));
            CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
            mMap.animateCamera(zoom);
        }
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mMap.setOnPoiClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        mMap.setMapType(pos+1);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPoiClick(PointOfInterest pointOfInterest) {
        Toast.makeText(getApplicationContext(), "Clicked: " +
                               pointOfInterest.name + "\nPlace ID:" + pointOfInterest.placeId +
                               "\nLatitude:" + pointOfInterest.latLng.latitude +
                               " Longitude:" + pointOfInterest.latLng.longitude,
                       Toast.LENGTH_SHORT).show();
    }
}
