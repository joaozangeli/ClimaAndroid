package br.com.egidioo.projetoclimaandroid.services;

import java.util.List;

import br.com.egidioo.projetoclimaandroid.models.Clima;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ClimaService {



    String lat= "-20.3174967";
    String lng= "-40.3085394";

    @GET("/weather?key=7b50a319&lat="+lat+"&lon="+lng+"")
    Call<Clima> listarClima();


}
