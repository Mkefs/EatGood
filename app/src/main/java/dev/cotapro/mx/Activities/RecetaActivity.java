package dev.cotapro.mx.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.cotapro.mx.Database.Receta;
import dev.cotapro.mx.FeedData;
import dev.cotapro.mx.KiwilimonApi.RecetaEntity;
import dev.cotapro.mx.R;

public class RecetaActivity extends AppCompatActivity {
    private long id = 0;
    private boolean internet = true;
    private String json;
    private RecetaEntity recipe;
    private final Executor exec = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta);
        exec.execute(this::get_recipe);
    }

    private void get_recipe() {
        Gson gson = new Gson();
        Bundle b = getIntent().getExtras();
        id = b.getLong("id");
        internet = b.getBoolean("internet");

        if (internet) {
            json = FeedData.get_recipe(RecetaActivity.this.id);
        } else {
            Receta recipe = FeedData.db.recetas().get_receta(RecetaActivity.this.id);
            json = recipe.json;
        }

        recipe = gson.fromJson(json, RecetaEntity.class);
        handler.post(this::bind_data);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void bind_data() {
        ImageView recipeImg = findViewById(R.id.recipe_image);
        TextView txtName = findViewById(R.id.recipe_name);
        TextView txtRating = findViewById(R.id.recipe_rating);
        TextView txtSteps = findViewById(R.id.recipe_steps);
        TextView txtIngredients = findViewById(R.id.recipe_ingredients);

        txtName.setText(recipe.titleh1);
        txtRating.setText(String.valueOf(recipe.raiting));
        String[] lists = dots(recipe);
        txtSteps.setText(Html.fromHtml(lists[0]));
        txtIngredients.setText(Html.fromHtml(lists[1]));

        String cdn_kiwilimon = "https://cdn.kiwilimon.com/recetaimagen/%s/%s";
        cdn_kiwilimon = String.format(cdn_kiwilimon, id, recipe.image);
        Glide.with(this)
            .load(cdn_kiwilimon)
            .placeholder(R.drawable.ic_launcher_background)
            .into(recipeImg);
    }

    private String[] dots(RecetaEntity receta) {
        String[] strings = new String[2];
        strings[0] = strings[1] = "<ul>";

        for(RecetaEntity.Pasos paso : receta.steps)
            strings[0] += "<li>" + paso.text + "</li>";
        for(RecetaEntity.Ingredientes ingrediente : receta.ingredients)
            strings[1] += "<li>" + ingrediente.text + "</li>";

        strings[0] += "</ul>";
        strings[1] += "</ul>";
        return strings;
    }

    public static void open_act(Context context, Bundle extras) {
        Intent siguiente = new Intent(context, RecetaActivity.class);
        siguiente.putExtras(extras);
        context.startActivity(siguiente);
    }

    public void go_back(View view) {
        finish();
    }

}