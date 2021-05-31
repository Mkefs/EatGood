package dev.cotapro.mx.ui.recetas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BulletSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.lang.reflect.Executable;
import java.sql.BatchUpdateException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.cotapro.mx.FeedData;
import dev.cotapro.mx.MainActivity;
import dev.cotapro.mx.R;
import dev.cotapro.mx.recetas.Receta;

public class RecetaActivity extends AppCompatActivity {
    private String json;
    int id = 0;
    boolean internet = true;

    private void get_recipe() {
        Bundle b = getIntent().getExtras();
        id = b.getInt("id");

        Executor exec = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        exec.execute(() -> {
            if(b.getBoolean("internet")) {
                json = FeedData.get_recipe(id);
            } else {
                Receta recipe = FeedData.db.recetas().get_receta(id);
                json = recipe.json;
            }
            handler.post(this::bind_data);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void bind_data() {
        setContentView(R.layout.receta_vista);
        ImageView recipeImg = findViewById(R.id.recipe_image);
        TextView txtName = findViewById(R.id.recipe_name);
        TextView txtRating = findViewById(R.id.recipe_rating);
        TextView txtSteps = findViewById(R.id.recipe_steps);
        TextView txtIngredients = findViewById(R.id.recipe_ingredients);
        // Parse json data
        Gson gson = new Gson();
        dev.cotapro.mx.api.Receta recipe = gson.fromJson(json, dev.cotapro.mx.api.Receta.class);
        // Spaneable steps
        SpannableStringBuilder steps_builder = new SpannableStringBuilder();
        for (dev.cotapro.mx.api.Receta.Pasos step : recipe.steps)
            steps_builder.append(
                step.text + "\n",
                new BulletSpan(20, Color.BLACK, 5),
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtSteps.setText(steps_builder);
        // Spaneable ingredients
        SpannableStringBuilder ing_builder = new SpannableStringBuilder();
        for (dev.cotapro.mx.api.Receta.Ingredientes ingredient : recipe.ingredients)
            ing_builder.append(
                ingredient.text + "\n",
                new BulletSpan(20, Color.BLACK, 5),
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtIngredients.setText(ing_builder);
        // Set other data in text
        txtName.setText(recipe.titleh1);
        txtRating.setText(String.valueOf(recipe.raiting));
        // Set image
        String cdn_kiwilimon = "https://cdn.kiwilimon.com/recetaimagen/%s/%s";
        cdn_kiwilimon = String.format(cdn_kiwilimon, id, recipe.image);
        Glide.with(this)
            .load(cdn_kiwilimon)
            .placeholder(R.drawable.ic_launcher_background)
            .into(recipeImg);
        // Buttons action
        ImageButton back = findViewById(R.id.recipe_back);
        back.setOnClickListener(v -> finish());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        get_recipe();
    }

    public static void open_act(Context context, Bundle extras) {
        Intent siguiente = new Intent(context, RecetaActivity.class);
        siguiente.putExtras(extras);
        context.startActivity(siguiente);
    }

    //Metodo para el boton anterior
    public void go_back(View view) {
        finish();
    }
}