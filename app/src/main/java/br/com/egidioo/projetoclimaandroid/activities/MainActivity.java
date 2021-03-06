package br.com.egidioo.projetoclimaandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import br.com.egidioo.projetoclimaandroid.R;
import br.com.egidioo.projetoclimaandroid.models.Clima;
import br.com.egidioo.projetoclimaandroid.models.Forecast;
import br.com.egidioo.projetoclimaandroid.models.Results;
import br.com.egidioo.projetoclimaandroid.retrofit.RetrofitConfig;
import br.com.egidioo.projetoclimaandroid.services.ClimaService;
import br.com.egidioo.projetoclimaandroid.utilitarios.Android;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //possivelmente mudar para climaservice, dps pegar apenas lat e lngq
        Android android = new Android(MainActivity.this);
        android.getCurrentLocation();
        Location location = android.getLocation();
        System.out.println(location);
        constraintLayout = (ConstraintLayout) findViewById(R.id.mylayout);

        Call<Clima> call = new RetrofitConfig().setClimaService().listarClima(ClimaService.API_KEY, String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
        call.enqueue(new Callback<Clima>() {
            @Override
            public void onResponse(Call<Clima> call, Response<Clima> response) {
                if(response.body() != null){

                    Results results = response.body().getResults();
                    Forecast forecast = results.getForecast().get(1);
                    System.out.println(results);
                    Log.d("teste", String.valueOf(results));
                    TextView activity_main_text_view_condicao =  findViewById(R.id.activity_main_text_view_condicao);
                    switch(forecast.getCondition()) {
                        case "storm":
                            //aqui ao inves de pegar os textos pelo strings.xml vc pode pegar pelo .getDescription(), q vem do site
                            activity_main_text_view_condicao.setText(R.string.tempestade);
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.storm));
                            break;
                        case "snow":
                            activity_main_text_view_condicao.setText(R.string.Nevando);
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.snow));
                            break;
                        case "hail":
                            activity_main_text_view_condicao.setText(R.string.Chovendo_granizo);
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.hail));
                            break;
                        case "rain":
                            activity_main_text_view_condicao.setText(R.string.Est??_chovendo);
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.rain));
                            break;
                        case "fog":
                            activity_main_text_view_condicao.setText(R.string.Est??_neblinando);
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.fog));
                            break;
                        case "clear_day":
                            activity_main_text_view_condicao.setText(R.string.O_dia_est??_limpo);
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.clearday));
                            break;
                        case "clear_night":
                            activity_main_text_view_condicao.setText(R.string.A_noite_est??_limpa);
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.clearnight));
                            break;
                        case "cloud":
                            activity_main_text_view_condicao.setText(R.string.Est??_nublado);
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.clouds));
                            break;
                        case "cloudly_day":
                            activity_main_text_view_condicao.setText(R.string.Dia_nublado);
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.cloudly_day));
                            break;
                        case "cloudly_night":
                            activity_main_text_view_condicao.setText(R.string.Noite_nublada);
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.cloudly_night));
                            break;
                        case "none_day":
                            activity_main_text_view_condicao.setText(R.string.erro_ao_obter_condi????o_do_dia);
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.error));
                            break;
                        case "none_night":
                            activity_main_text_view_condicao.setText(R.string.erro_ao_obter_condi????o_da_noite);
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.error));
                            break;
                        default:
                            activity_main_text_view_condicao.setText(R.string.Condi????o_n??o_encontrada);
                            constraintLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.error));

                    }

                    TextView activity_main_text_view_max =  findViewById(R.id.activity_main_text_view_max);
                    TextView activity_main_text_view_min =  findViewById(R.id.activity_main_text_view_min);
                    String min = String.valueOf(forecast.getMin());
                    String max = String.valueOf(forecast.getMax());

                    activity_main_text_view_max.setText(max+ "??C");
                    activity_main_text_view_min.setText(min+ "??C");
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