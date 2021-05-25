package dev.cotapro.mx.ui.recetas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.cotapro.mx.FeedData;
import dev.cotapro.mx.MainActivity;
import dev.cotapro.mx.R;
import dev.cotapro.mx.recetas.Receta;

public class RecetaActivity extends AppCompatActivity {
    String json;
    TextView tituloReceta, caloriasReceta, preparacionReceta, tcoccionReceta, descripcionReceta,
            favoritosReceta, ratingReceta, dateReceta, ingredientesReceta, pasosReceta, nombreChef;
    ImageView imageView;
    int id = 0;
    boolean internet = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receta_vista);
        Bundle b = getIntent().getExtras();
        tituloReceta = findViewById(R.id.txtTitulo);
        caloriasReceta = findViewById(R.id.txtCalorias);
        preparacionReceta = findViewById(R.id.txtTPreparacion);
        tcoccionReceta = findViewById(R.id.txtTCoccion);
        descripcionReceta = findViewById(R.id.txtDescripcion);
        favoritosReceta = findViewById(R.id.txtFavoritos);
        ratingReceta = findViewById(R.id.txtRating);
        dateReceta = findViewById(R.id.txtDate);
        ingredientesReceta = findViewById(R.id.txtIngredientes);
        pasosReceta = findViewById(R.id.txtPasos);
        nombreChef = findViewById(R.id.txtNombre);

        if(b != null) {
            id = b.getInt("id");
            internet = b.getBoolean("internet");

            Executor executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                Gson gson = new Gson();

                if(internet) {
                    Receta receta_guardada = FeedData.db.recetas().get_receta(id);
                    json = receta_guardada.json;
                } else {
                    json = FeedData.get_recipe(id);
                }
                dev.cotapro.mx.api.Receta recipe =
                    gson.fromJson(json, dev.cotapro.mx.api.Receta.class);

                //Convertimos los int a string necesarios
                String caloriasRecetaString = String.valueOf(recipe.calories);
                String tcoccionRecetaString = String.valueOf(recipe.cooktime);
                String favoritosRecetaString = String.valueOf(recipe.favorites);
                String ratingreceta = String.valueOf(recipe.raiting);

                // Aqui se hace lo de la vista
                handler.post(() -> {
                    //Aqui se hace lo de los TextView
                    tituloReceta.setText(recipe.titleh1);
                    caloriasReceta.setText(caloriasRecetaString);
                    if(recipe.cooked == 1) {
                        preparacionReceta.setText("Si");
                    } else {
                        preparacionReceta.setText("No");
                    }
                    tcoccionReceta.setText(tcoccionRecetaString);
                    descripcionReceta.setText(recipe.description+"\n");
                    favoritosReceta.setText(favoritosRecetaString);
                    ratingReceta.setText(ratingreceta);
                    dateReceta.setText(recipe.published);
                    for(int i = 0; i < recipe.ingredients.length; i++) {
                        String texto = (String) ingredientesReceta.getText();
                        ingredientesReceta.setText(texto + " - " +recipe.ingredients[i].text+"\n");
                    }
                    for(int i = 0; i < recipe.steps.length; i++) {
                        String texto = (String) pasosReceta.getText();
                        pasosReceta.setText(texto + " - " +recipe.steps[i].text+"\n");
                    }

                    nombreChef.setText(recipe.images[0].clientdata.firstname
                        +" "+recipe.images[0].clientdata.lastname);
                    //En las siguientes 5 lineas, se hace lo de la imagen desde internet
                    // (cabe resaltar que en si, son 3 lineas pero por estetica le di saltos de linea)
                    imageView = findViewById(R.id.imgReceta);
                    String context= "https://cdn.kiwilimon.com/recetaimagen/"+recipe.key+"/"+recipe.image;
                    Glide.with(imageView)
                            .load(context).placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).into(imageView);
                });
            });
        } else {
            Toast.makeText(RecetaActivity.this, "Error al conseguir los datos", Toast.LENGTH_LONG).show();
        }
    }

    // Metodo para acceder a esta vista
    public static void open_act(Context context, Bundle extras) {
        Intent siguiente = new Intent(context, RecetaActivity.class);
        siguiente.putExtras(extras);
        context.startActivity(siguiente);
    }

    //Metodo para el boton anterior
    public void Anterior(android.view.View view) {
        Intent anterior = new Intent(this, MainActivity.class);
        startActivity(anterior);
    }
}