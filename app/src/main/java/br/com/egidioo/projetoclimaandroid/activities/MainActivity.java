package br.com.egidioo.projetoclimaandroid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

                    TextView activity_main_text_view_condicao =  findViewById(R.id.activity_main_text_view_condicao);
                    switch(results.getCondition_slug()) {
                        case "storm":
                            activity_main_text_view_condicao.setText("Tempestade");
                            break;
                        case "snow":
                            activity_main_text_view_condicao.setText("Nevando");
                            break;
                        case "hail":
                            activity_main_text_view_condicao.setText("Chovendo granizo");
                            break;
                        case "rain":
                            activity_main_text_view_condicao.setText("Está chovendo");
                            break;
                        case "fog":
                            activity_main_text_view_condicao.setText("Está neblinando");
                            break;
                        case "clear_day":
                            activity_main_text_view_condicao.setText("O dia está limpo");
                            break;
                        case "clear_night":
                            activity_main_text_view_condicao.setText("A noite está limpa");
                            break;
                        case "cloud":
                            activity_main_text_view_condicao.setText("Está nublado");
                            break;
                        case "cloudly_day":
                            activity_main_text_view_condicao.setText("Dia nublado");
                            break;
                        case "cloudly_night":
                            activity_main_text_view_condicao.setText("Noite nublado");
                            break;
                        case "none_day":
                            activity_main_text_view_condicao.setText("erro ao obter condição do dia");
                            break;
                        case "none_night":
                            activity_main_text_view_condicao.setText("erro ao obter condição da noite");
                            break;

                        default:
                            activity_main_text_view_condicao.setText("Condição não encontrada!");
                    }

                    TextView temperatura =  findViewById(R.id.textView2);
                    String temp = String.valueOf(results.getTemp());
                    temperatura.setText(temp+ "°C");
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