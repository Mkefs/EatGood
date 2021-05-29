package dev.cotapro.mx.ui.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dev.cotapro.mx.R;
import dev.cotapro.mx.api.Descripcion;
import dev.cotapro.mx.ui.recetas.RecetaActivity;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private final ArrayList<Descripcion> mData;

    public ListAdapter(Descripcion[] itemList) {
        this.mData = new ArrayList<>();
        for (Descripcion descripcion : itemList) {
            if (!descripcion.key.isEmpty()) {
                mData.add(descripcion);
            }
        }
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext())
            .inflate(R.layout.receta_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
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
            Glide.with(itemView)
                .load(image_url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(iconImage);

            LinearLayout container = itemView.findViewById(R.id.receta_container);
            container.setOnClickListener(v -> {
                Bundle extras = new Bundle();
                extras.putInt("id", Integer.parseInt(item.key));
                extras.putBoolean("internet", false);
                RecetaActivity.open_act(itemView.getContext(), extras);
            });
        }

    }
}
