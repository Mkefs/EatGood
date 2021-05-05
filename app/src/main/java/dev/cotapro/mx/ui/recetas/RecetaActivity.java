package dev.cotapro.mx.ui.recetas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import dev.cotapro.mx.R;

public class RecetaActivity extends AppCompatActivity {

    TextView textView, textView0, textView1;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receta_vista);

        imageView = findViewById(R.id.imgPlatilloPrueba);

        String url="https://media-adsa.camilyo.software/media-adsa/static/3228/270.png";
        Glide.with(this)
                .load(url).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(imageView);

        textView = findViewById(R.id.txtIngredientes);
        textView0 = findViewById(R.id.txtPreparacion);
        textView1 = findViewById(R.id.txtDescripcion);

        textView.setMovementMethod(new ScrollingMovementMethod());
        textView0.setMovementMethod(new ScrollingMovementMethod());
        textView1.setMovementMethod(new ScrollingMovementMethod());

    }
}