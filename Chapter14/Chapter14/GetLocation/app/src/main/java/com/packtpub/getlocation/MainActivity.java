package com.packtpub.getlocation;

import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.DateFormat;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(this, new String[] {ACCESS_COARSE_LOCATION},1);
        }
    }

    private void getLocation() throws SecurityException {
        LocationServices.getFusedLocationProviderClient(this).getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        final TextView textView = findViewById(R.id.textView);
                        if (location != null) {
                            textView.setText(DateFormat.getTimeInstance()
                                    .format(location.getTime()) + "\n"
                                    + "Latitude=" + location.getLatitude() + "\n"
                                    + "Longitude=" + location.getLongitude());
                        } else {
                            Toast.makeText(MainActivity.this, "Location null", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });
    }
}
