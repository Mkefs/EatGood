package dev.cotapro.mx.Fragments;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.cotapro.mx.Adapters.IngredientAdapter;
import dev.cotapro.mx.Models.IngredientModel;
import dev.cotapro.mx.R;

public class IngredientFragment extends Fragment {
	private TextView mensaje;
	private ImageButton returnBtn;
	private FrameLayout searchLayout;
	private ExtendedFloatingActionButton openSearchBtn;
	private ProgressBar progressBar;
	private final SearchFragment searchFragment;
	private RecyclerView recyclerView;
	private SearchView searchView;
	private final Executor executor;
	private final Handler handler;
	public IngredientAdapter adapter;

	public IngredientFragment () {
		adapter = new IngredientAdapter();
		executor = Executors.newSingleThreadExecutor();
		handler = new Handler(Looper.myLooper());
		searchFragment = new SearchFragment();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View vista = inflater.inflate(R.layout.fragment_ingredients, container, false);

		progressBar = vista.findViewById(R.id.loading);
		mensaje = vista.findViewById(R.id.mensaje);
		searchView = vista.findViewById(R.id.search);
		searchLayout = vista.findViewById(R.id.searchFragment);
		searchView.setOnQueryTextListener(queryTextListener);

		openSearchBtn = vista.findViewById(R.id.buscar_receta);
		openSearchBtn.setOnClickListener(this::openSearch);
		returnBtn = vista.findViewById(R.id.return_button);
		returnBtn.setOnClickListener(this::returnIngredients);

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
			adapter.modelBackup.addAll(adapter.ingredientModels);

			handler.post(() -> {
				recyclerView.setVisibility(View.VISIBLE);
				progressBar.setVisibility(View.GONE);
				adapter.notifyDataSetChanged();
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openSearch (View v) {
		String[] ingredients = new String[adapter.selectedIngredients.size()];
		for(int i = 0; i < adapter.selectedIngredients.size(); i++)
			ingredients[i] = adapter.selectedIngredients.get(i).name;
		searchFragment.searchQuery = ingredients;
		openSearchBtn.setVisibility(View.GONE);
		recyclerView.setVisibility(View.GONE);
		mensaje.setVisibility(View.GONE);
		searchView.setVisibility(View.GONE);
		searchLayout.setVisibility(View.VISIBLE);
		returnBtn.setVisibility(View.VISIBLE);
		FragmentManager manager = getChildFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.searchFragment, searchFragment);
		transaction.commit();
	}

	public void returnIngredients (View v) {
		openSearchBtn.setVisibility(View.VISIBLE);
		recyclerView.setVisibility(View.VISIBLE);
		mensaje.setVisibility(View.VISIBLE);
		searchView.setVisibility(View.VISIBLE);
		searchLayout.setVisibility(View.GONE);
		returnBtn.setVisibility(View.GONE);
		FragmentManager manager = getChildFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.remove(searchFragment);
		transaction.commit();
	}
}
