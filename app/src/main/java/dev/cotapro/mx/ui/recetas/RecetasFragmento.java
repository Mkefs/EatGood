package dev.cotapro.mx.ui.recetas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

        ImageView imageView = (ImageView) findViewById(R.id.imgPlatilloPrueba);

        Glide.with(this)
            .load("https://www.laespanolaaceites.com/wp-content/uploads/2019/06/pizza-con-chorizo-jamon-y-queso-1080x671.jpg")
                .placeholder(new ColorDrawable(Color.RED))
                .into(imageView);
    }
}