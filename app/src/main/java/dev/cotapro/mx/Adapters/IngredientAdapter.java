package dev.cotapro.mx.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
	@NotNull
	@Override
	public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void onBindViewHolder(@NotNull ViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return 0;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public ViewHolder(@NotNull View itemView) {
			super(itemView);
		}
	}
}
