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

    TextView textView, textView0, textView1;
    ImageView imageView;
    private Object View;
    Button btnSwitch2;

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
    //Metodo para el boton anterior
    public void Anterior(android.view.View view) {
        Intent anterior = new Intent(this, MainActivity.class);
        startActivity(anterior);
    }
}