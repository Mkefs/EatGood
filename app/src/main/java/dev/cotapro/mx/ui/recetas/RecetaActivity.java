package dev.cotapro.mx.ui.recetas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.cotapro.mx.FeedData;
import dev.cotapro.mx.MainActivity;
import dev.cotapro.mx.R;
import dev.cotapro.mx.api.Datos;
import dev.cotapro.mx.api.Receta;
import dev.cotapro.mx.api.Recetas;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecetaActivity extends AppCompatActivity {
    String json;
    int id = 0;
    boolean saved = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receta_vista);
        Bundle b = getIntent().getExtras();
        if(b != null) {
            id = b.getInt("id");
            saved = b.getBoolean("saved");
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    if(saved)
                        json = FeedData.db.recetas().getReceta(id);
                    else
                        json = Datos.getrecipe(FeedData.kiwilimon, id);

                    // Lo conviertes a una variable tipo receta
                    Gson gson = new Gson();
                    Receta receta = gson.fromJson(json, Receta.class);
                }
            });
        } else {
            // Error al conseguir datos
        }

    }

    //Metodo para el boton anterior
    public void Anterior(android.view.View view) {
        Intent anterior = new Intent(this, MainActivity.class);
        startActivity(anterior);
    }
}