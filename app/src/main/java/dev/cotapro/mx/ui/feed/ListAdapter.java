package dev.cotapro.mx.ui.feed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dev.cotapro.mx.R;
import dev.cotapro.mx.api.Descripcion;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private final ArrayList<Descripcion> mData;

    public ListAdapter(Descripcion[] itemList) {
        this.mData = new ArrayList<>();
        for(int i = 0; i < itemList.length; i++) {
            if(!itemList[i].key.isEmpty()) {
                mData.add(itemList[i]);
            }
        }
    }

    @NonNull
    @Override
    public int getItemCount(){
        return mData.size();
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext())
            .inflate(R.layout.lista_recetas, parent, false);
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
            platillo = itemView.findViewById(R.id.PlatilloTextView);
            autor = itemView.findViewById(R.id.AutorTextView);
            stars = itemView.findViewById(R.id.starsTextView);

        }
        void bindData(final Descripcion item){
            String image_url = "https://cdn.kiwilimon.com/recetaimagen/%s/%s";
            image_url = String.format(image_url, item.key, item.image);

            Glide.with(itemView)
                .load(image_url)
                .placeholder(R.drawable.ic_lunch_dining_black_24dp)
                .into(iconImage);
            platillo.setText(item.name);
            autor.setText(item.chef);
            stars.setText(item.rating);
        }

    }
}
