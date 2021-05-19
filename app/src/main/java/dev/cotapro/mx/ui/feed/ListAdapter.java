package dev.cotapro.mx.ui.feed;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.util.List;

import dev.cotapro.mx.FeedData;
import dev.cotapro.mx.R;
import dev.cotapro.mx.api.Datos;
import dev.cotapro.mx.api.Recetas;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<ListElement> itemList, Context context)
    {
        this.mInflater=LayoutInflater.from(context);
        this.context=context;
        this.mData=itemList;
    }

    @Override
    public int getItemCount(){ return mData.size(); }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=mInflater.inflate(R.layout.lista_recetas, null);
        return new ListAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
        ListElement listElement= mData.get(position);
        ViewHolder viewHolder= (ViewHolder)holder;
        //Cargamos la imagen
        RequestOptions requestOptions=  new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_lunch_dining_black_24dp);
        requestOptions.error(R.drawable.ic_lunch_dining_black_24dp);

        Glide.with(context)
                .load(listElement.getImage())
                .apply(requestOptions)
                .into(viewHolder.iconImage);

    }

    public void setItems(List<ListElement>items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{


        private ImageView iconImage;
        private TextView platillo, autor, stars;

        ViewHolder(View itemView){
            super(itemView);
            iconImage=itemView.findViewById(R.id.foto);
             platillo=itemView.findViewById(R.id.PlatilloTextView);
             autor=itemView.findViewById(R.id.AutorTextView);
             stars=itemView.findViewById(R.id.starsTextView);
        }
        void bindData(final ListElement items){


            platillo.setText(items.getPlatillo());
            autor.setText(items.getAutor());
            stars.setText(items.getStars());
        }

    }
}
