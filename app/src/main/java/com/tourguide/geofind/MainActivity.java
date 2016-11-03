package com.tourguide.geofind;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    Button mGoogleMapButton;
    Button mGooglePlaceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();

        // Google Map Button
        mGoogleMapButton = (Button) findViewById(R.id.google_map_button);
        mGoogleMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MapsActivity.class);
                startActivity(intent);
            }
        });

        // Google Place Button
        mGooglePlaceButton = (Button) findViewById(R.id.google_place_button);
        mGooglePlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GooglePlaceActivity.class);
                startActivity(intent);
            }
        });


    }
}
