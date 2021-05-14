package dev.cotapro.mx.ui.feed;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.cotapro.mx.R;

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
    }

    public void setItems(List<ListElement>items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView platillo, autor, stars;

        ViewHolder(View itemView){
            super(itemView);
            iconImage=itemView.findViewById(R.id.foto);
             platillo=itemView.findViewById(R.id.PlatilloTextView);
             autor=itemView.findViewById(R.id.AutorTextView);
            stars=itemView.findViewById(R.id.starsTextView);
        }
        void bindData(final ListElement items){
            iconImage.setColorFilter(Color.parseColor(items.getColor()), PorterDuff.Mode.SRC_IN);
            platillo.setText(items.getPlatillo());
            autor.setText(items.getAutor());
            stars.setText(items.getStars());
        }

    }
}
