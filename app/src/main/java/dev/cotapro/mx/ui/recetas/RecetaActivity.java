package dev.cotapro.mx.ui.recetas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.cotapro.mx.FeedData;
import dev.cotapro.mx.MainActivity;
import dev.cotapro.mx.R;
import dev.cotapro.mx.api.Datos;
import dev.cotapro.mx.api.Receta;

public class RecetaActivity extends AppCompatActivity {
    String json;
    TextView tituloReceta, caloriasReceta, preparacionReceta, tcoccionReceta, descripcionReceta,
            favoritosReceta, ratingReceta, dateReceta, ingredientesReceta, pasosReceta, nombreChef;
    ImageView imageView;
    int id = 0;
    boolean saved = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receta_vista);
        Bundle b = getIntent().getExtras();
        tituloReceta=findViewById(R.id.txtTitulo);
        caloriasReceta=findViewById(R.id.txtCalorias);
        preparacionReceta=findViewById(R.id.txtTPreparacion);
        tcoccionReceta=findViewById(R.id.txtTCoccion);
        descripcionReceta=findViewById(R.id.txtDescripcion);
        favoritosReceta=findViewById(R.id.txtFavoritos);
        ratingReceta=findViewById(R.id.txtRating);
        dateReceta=findViewById(R.id.txtDate);
        ingredientesReceta=findViewById(R.id.txtIngredientes);
        pasosReceta=findViewById(R.id.txtPasos);
        nombreChef=findViewById(R.id.txtNombre);
        if(b != null) {
            id = b.getInt("id");
            saved = b.getBoolean("saved");

            Executor executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    if(saved)
                        json = FeedData.db.recetas().getReceta(id);
                    else
                        json = Datos.getrecipe(FeedData.kiwilimon, id);

                    // Lo conviertes a una variable tipo receta
                    Gson gson = new Gson();
                    Receta receta = gson.fromJson(json, Receta.class);

                    //Convertimos los int a string necesarios
                    String caloriasRecetaString = String.valueOf(receta.calories);
                    String tcoccionRecetaString = String.valueOf(receta.cooktime);
                    String favoritosRecetaString = String.valueOf(receta.favorites);
                    String ratingreceta = String.valueOf(receta.raiting);


                    //Aqui se hace lo de la vista
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Aqui se hace lo de los TextView
                            tituloReceta.setText(receta.titleh1);
                            caloriasReceta.setText(caloriasRecetaString);
                            if(receta.cooked == 1) {
                                preparacionReceta.setText("Si");
                            } else {
                                preparacionReceta.setText("No");
                            }
                            tcoccionReceta.setText(tcoccionRecetaString);
                            descripcionReceta.setText(receta.description+"\n");
                            favoritosReceta.setText(favoritosRecetaString);
                            ratingReceta.setText(ratingreceta);
                            dateReceta.setText(receta.published);
                            for(int i = 0; i < receta.ingredients.length; i++) {
                                String texto = (String) ingredientesReceta.getText();
                                ingredientesReceta.setText(texto + " - " +receta.ingredients[i].text+"\n");
                            }
                            for(int i = 0; i < receta.steps.length; i++) {
                                String texto = (String) pasosReceta.getText();
                                pasosReceta.setText(texto + " - " +receta.steps[i].text+"\n");
                            }

                            nombreChef.setText(receta.images[0].clientdata.firstname+" "+receta.images[0].clientdata.lastname);
                            //En las siguientes 5 lineas, se hace lo de la imagen desde internet (cabe resaltar que en si, son 3 lineas pero por estetica le di saltos de linea)
                            imageView = findViewById(R.id.imgReceta);
                            String context= "https://cdn.kiwilimon.com/recetaimagen/"+receta.key+"/"+receta.image;
                            Glide.with(imageView)
                                    .load(context).placeholder(R.drawable.ic_launcher_background)
                                    .error(R.drawable.ic_launcher_background).into(imageView);

                            /*descripcionReceta.setMovementMethod(new ScrollingMovementMethod());
                            ingredientesReceta.setMovementMethod(new ScrollingMovementMethod());
                            pasosReceta.setMovementMethod(new ScrollingMovementMethod());*/

                        }
                    });
                }
            });
        } else {
            Toast.makeText(RecetaActivity.this, "Error al conseguir los datos", Toast.LENGTH_LONG).show();
            // Error al conseguir datos
        }

    }

    //Metodo para el boton anterior
    public void Anterior(android.view.View view) {
        Intent anterior = new Intent(this, MainActivity.class);
        startActivity(anterior);
    }
}