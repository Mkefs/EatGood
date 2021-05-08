package dev.cotapro.mx;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.text.Layout;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class listadapter extends RecyclerView.Adapter<listadapter.ViewHolder> {
    private List<listfavoritos> mData;
    private LayoutInflater mInflater;
    private Context context;

    public listadapter(List<listfavoritos> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;

    }
    @Override
    public int getItemCount() { return mData.size(); }

    @Override
    public listadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_favoritos,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final listadapter.ViewHolder holder, final int position){
        holder.binData(mData.get(position));
    }


    public void setItems(List<listfavoritos> items) { mData =items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView name, autor;

        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            name = itemView.findViewById(R.id.nameTextView);
            autor = itemView.findViewById(R.id.autorTextView);

        }
        void binData(final listfavoritos item){
            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            name.setText(item.getName());
            autor.setText(item.getAutor());

        }
    }
}
