package dev.cotapro.mx.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import dev.cotapro.mx.R;

public class Listadap extends RecyclerView.Adapter<Listadap.ViewHolder> {
    private String[][] data;

    public Listadap (String[][] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext() ).inflate(R.layout.helper,parent,false) ;
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.data(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView texto;
        private final ImageView image;

        public void data(final String[] datos)  {
            texto.setText(datos[1]);
            String context = "https://ingredients-eatgood.000webhostapp.com/imagen?n=" + datos[2];
            Glide.with(image)
                    .load(context).placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background).into(image);
        }

        public ViewHolder(View vista){
            super(vista);
            texto = vista.findViewById(R.id.texto);
            image = vista.findViewById(R.id.ingrediente12);
        }
    }
}
