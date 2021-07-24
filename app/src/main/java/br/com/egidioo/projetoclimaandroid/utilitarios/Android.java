package br.com.egidioo.projetoclimaandroid.utilitarios;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import static android.content.Context.LOCATION_SERVICE;

public class Android {

    private Context context;
    private Location location;
    private Activity activity;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    public Android(Context context) {
        this.context = context;
        this.activity = (Activity) context;
    }

    public void trocarDeActivity(Class<?> activity){
        Intent intent = new Intent(context, activity);

        context.startActivity(intent);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void getCurrentLocation() {
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
    }

    private void isReadGpsAllowed() {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED  ){

            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);

            getLocation();
        }

    }

    public void mensagemCurta(String mensagem){

        Toast.makeText(context,mensagem,Toast.LENGTH_SHORT).show();

    }

    public void mensagemLonga(String mensagem){

        Toast.makeText(context,mensagem,Toast.LENGTH_LONG).show();

    }

    public void esconderComponente(View view){
        view.setVisibility(View.GONE);
    }

    public void mostrarComponente(View view){
        view.setVisibility(View.VISIBLE);
    }



}
