package com.example.mylocationtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.android.SphericalUtil;
public class MainActivity extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationClient;
    LatLng yourLocation;
    LatLng ponda;
    Double distance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Button btn=findViewById(R.id.button2);

         ponda = new LatLng(15.40757526659319, 73.99194849921113);
       fusedLocationClient= LocationServices.getFusedLocationProviderClient(MainActivity.this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
                fusedLocationClient.getLastLocation()
                .addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            TextView textView =findViewById(R.id.as);
                            Double lat =location.getLatitude();
                            Double longi = location.getLongitude();

                            yourLocationPlz(lat,longi);

                            textView.setText(location.getLatitude()+ "sadad" +location.getLongitude()+"gg");
                            distance = SphericalUtil.computeDistanceBetween(yourLocation, ponda);
                            Toast.makeText(MainActivity.this, "Distance between Your Location and Destination is \n " + String.format("%.2f", distance / 1000) + "km", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });







    }



public void  yourLocationPlz(Double Latitude,Double Longitude){

         yourLocation=new LatLng(Latitude,Longitude);


}
}