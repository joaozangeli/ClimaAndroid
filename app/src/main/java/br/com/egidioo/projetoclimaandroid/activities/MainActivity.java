package br.com.egidioo.projetoclimaandroid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import java.sql.SQLOutput;
import java.util.List;

import br.com.egidioo.projetoclimaandroid.R;
import br.com.egidioo.projetoclimaandroid.models.Clima;
import br.com.egidioo.projetoclimaandroid.models.Results;
import br.com.egidioo.projetoclimaandroid.retrofit.RetrofitConfig;
import br.com.egidioo.projetoclimaandroid.services.ClimaService;
import br.com.egidioo.projetoclimaandroid.utilitarios.Android;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //possivelmente mudar para climaservice, dps pegar apenas lat e lngq
        Android android = new Android(MainActivity.this);
        android.getCurrentLocation();
        Location location = android.getLocation();
        System.out.println(location);


        Call<Clima> call = new RetrofitConfig().setClimaService().listarClima(ClimaService.API_KEY, String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
        call.enqueue(new Callback<Clima>() {
            @Override
            public void onResponse(Call<Clima> call, Response<Clima> response) {

                if(response.body() != null){
                    Results results = response.body().getResults();
                    System.out.println(results);
                    Log.d("teste", String.valueOf(results));
                }
            }

            @Override
            public void onFailure(Call<Clima> call, Throwable t) {
                Log.d("teste", String.valueOf(t));
                Log.d("teste2", String.valueOf(call));

            }
        });


    }

}