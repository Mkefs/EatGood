package dev.cotapro.mx.ui.feed;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.cotapro.mx.FeedData;
import dev.cotapro.mx.R;
import dev.cotapro.mx.api.Descripcion;
import dev.cotapro.mx.api.Recetas;
import dev.cotapro.mx.ui.recetas.RecetaActivity;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    public int page = 1;
    private final ArrayList<Descripcion> mData;
    public ListAdapter(Descripcion[] itemList) {
        this.mData = new ArrayList<>();
        addRecetas(itemList);
    }

    private void addRecetas(Descripcion[] items) {
        ArrayList<Descripcion> append = new ArrayList<>();
        for (Descripcion desc : items) {
            if (!desc.key.isEmpty())
                append.add(desc);
        }
        mData.addAll(append);
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext())
            .inflate(R.layout.receta_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NotNull final ListAdapter.ViewHolder holder,
                                 final int position){
        holder.bindData(mData.get(position));
        // When the las item is showed up then request the next page
        if(position == mData.size() - 1){
            page++;
            Executor exec = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            exec.execute(() -> {
                String recipe = FeedData.get_feed(page);
                Gson gson = new Gson();

                Recetas receta = gson.fromJson(recipe, Recetas.class);
                addRecetas(receta.payload);
                handler.post(ListAdapter.this::notifyDataSetChanged);
            });
        }
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView iconImage;
        private final TextView platillo, autor, stars;

        ViewHolder(View itemView){
            super(itemView);
            iconImage = itemView.findViewById(R.id.foto);
            platillo = itemView.findViewById(R.id.platillo);
            autor = itemView.findViewById(R.id.chef);
            stars = itemView.findViewById(R.id.estrellas);

        }
        void bindData(final Descripcion item){
            String image_url = "https://cdn.kiwilimon.com/recetaimagen/%s/%s";
            image_url = String.format(image_url, item.key, item.image);

            platillo.setText(item.name);
            autor.setText(item.chef);
            stars.setText(item.rating);
            Glide.with(iconImage)
                .load(image_url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(iconImage);

            LinearLayout container = itemView.findViewById(R.id.receta_container);
            container.setOnClickListener(v -> {
                Bundle extras = new Bundle();
                extras.putInt("id", Integer.parseInt(item.key));
                extras.putBoolean("internet", true);
                RecetaActivity.open_act(itemView.getContext(), extras);
            });
        }

    }
}
