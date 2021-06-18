package dev.cotapro.mx.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import dev.cotapro.mx.Models.IngredientModel;
import dev.cotapro.mx.R;
import dev.cotapro.mx.Utils.RequestData;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
	public ArrayList<IngredientModel> ingredientModels;
	public ArrayList<IngredientModel> selectedIngredients;
	public ArrayList<IngredientModel> modelBackup;

	public IngredientAdapter() {
		ingredientModels = new ArrayList<>();
		selectedIngredients = new ArrayList<>();
		modelBackup = new ArrayList<>();
	}

	@NotNull
	@Override
	public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.item_ingredient, parent, false);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
		holder.bindData(ingredientModels.get(position));
	}

	@Override
	public int getItemCount() {
		return ingredientModels.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		private final CardView container;
		public TextView title;
		public ImageView imageView;
		public IngredientModel model;

		public ViewHolder(@NotNull View itemView) {
			super(itemView);
			title = itemView.findViewById(R.id.texto);
			imageView = itemView.findViewById(R.id.image_ingrediente);
			container = itemView.findViewById(R.id.item_search_layout);
		}

		public void setStatus () {
			if (model.selected) {
				container.setCardElevation(10);
			} else {
				container.setCardElevation(0);
			}
		}

		public void bindData(IngredientModel model) {
			this.model = model;
			title.setText(model.name);
			container.setOnLongClickListener(this::longClickListener);
			setStatus();
			// Set image
			String url = RequestData.Ingredients.getIngredientImg(model.image);
			Picasso.get()
				.load(url)
				.into(imageView);
		}

		public boolean longClickListener(View v) {
			model.selected = !model.selected;

			if(model.selected)
				selectedIngredients.add(model);
			else
				selectedIngredients.remove(model);
			setStatus();
			return true;
		}
	}
}
