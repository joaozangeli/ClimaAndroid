package br.com.egidioo.projetoclimaandroid.retrofit;

import br.com.egidioo.projetoclimaandroid.services.ClimaService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private Retrofit retrofit;

    public RetrofitConfig() {
        OkHttpClient client = new OkHttpClient.Builder().build();

        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://api.hgbrasil.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ClimaService setClimaService() {
        return this.retrofit.create(ClimaService.class);
    }



}
