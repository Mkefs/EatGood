package dev.cotapro.mx.ui.recetas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import dev.cotapro.mx.MainActivity;
import dev.cotapro.mx.R;
import dev.cotapro.mx.ui.home.HomeFragment;

public class RecetaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receta_vista);

    }
    //Metodo para el boton anterior
    public void Anterior(android.view.View view) {
        Intent anterior = new Intent(this, MainActivity.class);
        startActivity(anterior);
    }
}