package dev.cotapro.mx.Fragments;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.stream.Stream;

import dev.cotapro.mx.Adapters.IngredientAdapter;
import dev.cotapro.mx.Models.IngredientModel;
import dev.cotapro.mx.R;

public class IngredientFragment extends Fragment {
	private ProgressBar progressBar;
	private RecyclerView recyclerView;
	private SearchView searchView;
	private final Executor executor;
	private final Handler handler;
	public IngredientAdapter adapter;

	public IngredientFragment () {
		adapter = new IngredientAdapter();
		executor = Executors.newSingleThreadExecutor();
		handler = new Handler(Looper.myLooper());
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View vista = inflater.inflate(R.layout.fragment_ingredients, container, false);

		progressBar = vista.findViewById(R.id.loading);
		searchView = vista.findViewById(R.id.search);
		searchView.setOnQueryTextListener(queryTextListener);
		searchView.setOnQueryTextFocusChangeListener(this::focusChangeListener);

		recyclerView = vista.findViewById(R.id.recycleIngredientes);
		recyclerView.setHasFixedSize(true);
		recyclerView.setNestedScrollingEnabled(true);
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
		recyclerView.setAdapter(adapter);

		executor.execute(this::getIngredientes);
		return vista;
	}

	private final SearchView.OnQueryTextListener queryTextListener =
		new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				String query = newText.toLowerCase();
				adapter.ingredientModels.clear();
				for (IngredientModel model : adapter.modelBackup)
					if (model.name.toLowerCase().contains(query))
						adapter.ingredientModels.add(model);
				adapter.notifyDataSetChanged();
				return false;
			}
		};


	public void focusChangeListener(View v, boolean hasFocus) {
		if(hasFocus) {
			adapter.modelBackup.clear();
			adapter.modelBackup.addAll(adapter.ingredientModels);
			adapter.ingredientModels.clear();
		} else {
			adapter.ingredientModels.clear();
			adapter.ingredientModels.addAll(adapter.modelBackup);
		}
		adapter.notifyDataSetChanged();
	}

	public void getIngredientes () {
		String ingredientsJson;
		Gson gson = new Gson();

		Context context = getContext();
		assert context != null;
		AssetManager manager = context.getAssets();

		try (InputStream stream = manager.open("recetas.json")) {
			byte[] bytes = new byte[stream.available()];
			stream.read(bytes);
			stream.close();

			ingredientsJson = new String(bytes);
			IngredientModel[] ingredients =
				gson.fromJson(ingredientsJson, IngredientModel[].class);
			adapter.ingredientModels.addAll(Arrays.asList(ingredients));

			handler.post(() -> {
				recyclerView.setVisibility(View.VISIBLE);
				progressBar.setVisibility(View.GONE);
				adapter.notifyDataSetChanged();
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
