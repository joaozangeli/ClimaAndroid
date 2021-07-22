package br.com.egidioo.projetoclimaandroid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import br.com.egidioo.projetoclimaandroid.R;
import br.com.egidioo.projetoclimaandroid.models.Clima;
import br.com.egidioo.projetoclimaandroid.retrofit.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Call<Clima> call = new RetrofitConfig().setClimaService().listarClima();
        call.enqueue(new Callback<Clima>() {
            @Override
            public void onResponse(Call<Clima> call, Response<Clima> response) {

                Clima climaList = response.body().results;
                System.out.println(climaList);
                Log.d("teste", String.valueOf(climaList));
            }

            @Override
            public void onFailure(Call<Clima> call, Throwable t) {
                Log.d("teste", String.valueOf(t));
                Log.d("teste2", String.valueOf(call));

            }
        });


    }

}