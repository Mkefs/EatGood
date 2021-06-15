package dev.cotapro.mx.Adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import dev.cotapro.mx.Activities.RecetaActivity;
import dev.cotapro.mx.Database.Receta;
import dev.cotapro.mx.R;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
	private Receta[] recetas;
	public HomeAdapter(Receta[] recetas) {
		this.recetas = recetas;
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
//		holder.bindData(recetas[position]);
	}

	@Override
	public int getItemCount() {
		return recetas.length;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		LinearLayout container;
		TextView platillo, chef;
		public ViewHolder(@NotNull View itemView) {
			super(itemView);
			chef = itemView.findViewById(R.id.chef);
			platillo = itemView.findViewById(R.id.platillo);
			container = itemView.findViewById(R.id.receta_container);
		}

//		public void bindData(Receta data) {
//			chef.setText(data.chef);
//			platillo.setText(data.name);
//			container.setOnClickListener(v -> {
//				Bundle datos = new Bundle();
//				datos.putBoolean("internet", false);
//				datos.putLong("id", data.id);
//				RecetaActivity.open_act(itemView.getContext(), datos);
//			});
//		}
	}
}
