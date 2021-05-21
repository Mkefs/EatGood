package dev.cotapro.mx.ui.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import dev.cotapro.mx.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;




    public ListAdapter(Context context, List<ListElement> itemList)
    {

        this.context=context;
        this.mData=itemList;
    }

    @Override
    public int getItemCount(){ return mData.size(); }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_recetas, parent, false);

        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
        ListElement listElement= mData.get(position);
        ViewHolder viewHolder= (ViewHolder)holder;

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
            //Se carga la imagen con glide
            Glide.with(itemView)
                    .load(items.getImage())
                    .placeholder(R.drawable.ic_lunch_dining_black_24dp)
                    .into(iconImage);
            platillo.setText(items.getPlatillo());
            autor.setText(items.getAutor());
            stars.setText(items.getStars());
        }

    }
}
