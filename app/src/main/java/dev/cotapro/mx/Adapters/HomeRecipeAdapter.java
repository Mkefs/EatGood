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

import java.io.File;
import java.util.ArrayList;

import dev.cotapro.mx.Activities.RecetaActivity;
import dev.cotapro.mx.Database.Receta;
import dev.cotapro.mx.R;

public class HomeRecipeAdapter extends RecyclerView.Adapter<HomeRecipeAdapter.ViewHolder>{
	public ArrayList<Receta> recetas;
	private final int itemMargin;
	public RecipeAdapter.onBottomReached bottomReached;

	public HomeRecipeAdapter(int margin) {
		recetas = new ArrayList<>();
		itemMargin = margin;
	}

	@NotNull
	@Override
	public HomeRecipeAdapter.ViewHolder
	onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
		View item = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.item_receta, parent, false);
		return new HomeRecipeAdapter.ViewHolder(item);
	}

	@Override
	public void onBindViewHolder(@NotNull HomeRecipeAdapter.ViewHolder holder, int position) {
		if(getItemCount() - itemMargin < position && bottomReached != null)
			bottomReached.bottomReached();
		holder.bindData(recetas.get(position));
	}

	@Override
	public int getItemCount() {
		return recetas.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		Receta receta;
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

		public void bindData(Receta receta) {
			this.receta = receta;
			platillo.setText(receta.name);
			chef.setText(receta.chef);
			estrellas.setRating(receta.rating);

			// Load image
			File thumbPath = new File(itemView.getContext().getFilesDir(), receta.thumbPath);
			Picasso.get()
				.load(thumbPath)
				.into(image);
		}

		private void clickRecipe(View v) {
			Bundle data = new Bundle();
			data.putLong("key", receta.key);
			data.putString("thumb", receta.thumbPath);
			RecetaActivity.open_act(itemView.getContext(), data);
		}
	}
}
