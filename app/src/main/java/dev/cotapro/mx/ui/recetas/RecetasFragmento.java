package dev.cotapro.mx.ui.recetas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import dev.cotapro.mx.R;

public class RecetasFragmento extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receta_vista);

        imageView = findViewById(R.id.imgPlatilloPrueba);

        String url="https://media-adsa.camilyo.software/media-adsa/static/3228/270.png";
        Glide.with(this)
                .load(url).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(imageView);
    }
}