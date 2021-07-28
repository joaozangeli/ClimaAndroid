package br.com.egidioo.projetoclimaandroid.services;

import android.content.Context;
import android.location.Location;

import java.util.List;

import br.com.egidioo.projetoclimaandroid.activities.MainActivity;
import br.com.egidioo.projetoclimaandroid.models.Clima;
import br.com.egidioo.projetoclimaandroid.utilitarios.Android;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ClimaService {

    String API_KEY = "7b50a319";

    @GET("weather")
    Call<Clima> listarClima(@Query("key") String key, @Query("lat") String lat, @Query("lon") String lng);


}
