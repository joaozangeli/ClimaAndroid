package br.com.egidioo.projetoclimaandroid.utilitarios;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;

import static android.content.Context.LOCATION_SERVICE;

public class Android {

    private Context context;
    private Location location;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    public Android(Context context) {
        this.context = context;
    }

    public void trocarDeActivity(Class<?> activity){
        Intent intent = new Intent(context, activity);


        
        context.startActivity(intent);
    }

    public Location getLocation() {
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            isReadGpsAllowed();
        }
        this.location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (this.location == null)
        {
            this.location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

        }

        return this.location;
    }

    private void isReadGpsAllowed() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED  ){

            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);

            getLocation();
        }

    }



}
