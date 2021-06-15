package dev.cotapro.mx.Activities;

import android.content.Context;
import android.content.Intent;
import android.icu.number.LocalizedNumberFormatter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.cotapro.mx.Database.RecetaData;
import dev.cotapro.mx.KiwilimonApi.RecetaEntity;
import dev.cotapro.mx.R;
import dev.cotapro.mx.Utils.RequestData;

public class RecetaActivity extends AppCompatActivity {
    RecetaEntity recetaEntity;
    TextView title, ingredientes, steps;
    ImageView imageView;
    RatingBar ratingBar;
    ImageButton saveButton;

    private final Executor exec = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());
    private long key;
    private boolean saved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta);

        title = findViewById(R.id.recipe_name);
        ingredientes = findViewById(R.id.recipe_ingredients);
        steps = findViewById(R.id.recipe_steps);
        imageView = findViewById(R.id.recipe_image);
        ratingBar = findViewById(R.id.recipe_rating);
        saveButton = findViewById(R.id.recipe_fav);

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        if(data != null) {
            key = data.getLong("key");
            exec.execute(this::getReceta);
        } else {
            Toast.makeText(
                getParent(),
                "Ha habido un error abriendo la receta",
                Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void getReceta() {
        RecetaData recetaDB = RequestData.db.recetaDataDAO().get_recipe(key);
        if (recetaDB != null) {
            saved = true;
            Gson gson = new Gson();
            recetaEntity = gson.fromJson(recetaDB.json, RecetaEntity.class);
        } else {
            saved = false;
            recetaEntity = RequestData.Kiwilimon.get_recipe(key);
        }
        if(recetaEntity == null)
            finish();
        handler.post(this::bindData);
    }

    private void bindData() {
        title.setText(recetaEntity.titleh1);
        ratingBar.setRating(recetaEntity.raiting);
        setSaveButton();

        // Ingredients and steps list
        String[] list = list(recetaEntity);
        steps.setText(list[0]);
        ingredientes.setText(list[1]);
    }

    private void setSaveButton() {
        if(saved)
            saveButton.setImageResource(R.drawable.btn_heart2);
        else
            saveButton.setImageResource(R.drawable.btn_heart);
    }

    private String[] list(RecetaEntity receta) {
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
