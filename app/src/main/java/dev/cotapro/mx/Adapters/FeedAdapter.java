package dev.cotapro.mx.Adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import dev.cotapro.mx.Activities.RecetaActivity;
import dev.cotapro.mx.KiwilimonApi.DescripcionEntity;
import dev.cotapro.mx.R;
import dev.cotapro.mx.Utils.RequestData;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder>{
    public interface onBottomReached { void bottomReached(); }

    public ArrayList<DescripcionEntity> recetas;
    private int itemMargin;
    public onBottomReached bottomReached;

    public FeedAdapter(int margin) {
        recetas = new ArrayList<>();
        itemMargin = margin;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_receta, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        if(getItemCount() - itemMargin < position && bottomReached != null)
            bottomReached.bottomReached();
        holder.bindData(recetas.get(position));
    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        DescripcionEntity descripcionEntity;
        ImageView image;
        TextView platillo, chef;
        RatingBar estrellas;
        LinearLayout container;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.foto);
            platillo = itemView.findViewById(R.id.platillo);
            chef = itemView.findViewById(R.id.chef);
            estrellas = itemView.findViewById(R.id.estrellas);
            container = itemView.findViewById(R.id.receta_container);
            container.setOnClickListener(this::clickRecipe);
        }

        public void bindData(DescripcionEntity descripcionEntity) {
            this.descripcionEntity = descripcionEntity;
            platillo.setText(descripcionEntity.name);
            chef.setText(descripcionEntity.chef);
            estrellas.setRating(Float.parseFloat(descripcionEntity.rating));
            // Load image
            String url = RequestData.Kiwilimon.getThumbUrl("100x100",
                descripcionEntity.key,
                descripcionEntity.image);
            Picasso.get()
                .load(url)
                .into(image);
        }

        private void clickRecipe(View v) {
            Bundle data = new Bundle();
            data.putLong("key", Long.parseLong(descripcionEntity.key));
            RecetaActivity.open_act(itemView.getContext(), data);
        }
    }
}
