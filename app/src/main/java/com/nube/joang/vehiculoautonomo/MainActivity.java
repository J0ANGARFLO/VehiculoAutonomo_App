package com.nube.joang.vehiculoautonomo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ImageView ivBtnStart;
    private ImageView ivBtnStop;
    private Retrofit retrofit;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_engine);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_rutas);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_camara:
                    mTextMessage.setText(R.string.title_camera);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Llamado a la función Lambda para arrancar el vehículo
        startEngineLambda();
    }

    private void startEngineLambda(){
        ivBtnStart = (ImageView) findViewById(R.id.ivBtnStart);
        ivBtnStop = (ImageView) findViewById(R.id.ivBtnStop);

        retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ivBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Iniciando Carro RC", Toast.LENGTH_SHORT).show();

                getStartEngineREST();
            }
        });

        ivBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Deteniendo Carro RC", Toast.LENGTH_SHORT).show();

                getStopEngineREST();
            }
        });
    }

    private void getStartEngineREST() {
        StartEngineService service = retrofit.create(StartEngineService.class);

        Call<mapperAttributes> call = service.getStartEngine();
        call.enqueue(new Callback<mapperAttributes>() {
            @Override
            public void onResponse(Call<mapperAttributes> call, Response<mapperAttributes> response) {
                mapperAttributes mapperAttributes = response.body();
                Log.d("Response", mapperAttributes.getMessage());
                Toast.makeText(MainActivity.this, mapperAttributes.getMessage(), Toast.LENGTH_LONG).show();

                // Cambio de imagenes
                ivBtnStart.setVisibility(View.INVISIBLE);
                ivBtnStop.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<mapperAttributes> call, Throwable t) {
                Log.e("Error", t.getMessage());
                Toast.makeText(MainActivity.this, "Ha ocurrido un error al llamar al servicio", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getStopEngineREST() {
        StopEngineService service = retrofit.create(StopEngineService.class);

        Call<mapperAttributes> call = service.getStopEngine();
        call.enqueue(new Callback<mapperAttributes>() {
            @Override
            public void onResponse(Call<mapperAttributes> call, Response<mapperAttributes> response) {
                mapperAttributes mapperAttributes = response.body();
                Log.d("Response", mapperAttributes.getMessage());
                Toast.makeText(MainActivity.this, mapperAttributes.getMessage(), Toast.LENGTH_LONG).show();

                // Cambio de imagenes
                ivBtnStart.setVisibility(View.VISIBLE);
                ivBtnStop.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<mapperAttributes> call, Throwable t) {
                Log.e("Error", t.getMessage());
                Toast.makeText(MainActivity.this, "Ha ocurrido un error al llamar al servicio", Toast.LENGTH_LONG).show();
            }
        });
    }
}
