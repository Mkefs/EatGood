package dev.cotapro.mx.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import dev.cotapro.mx.Adapters.SearchAdapt;
import dev.cotapro.mx.R;

public class SearchFragment extends Fragment {
	private View vista;
	private String[][] ingredientes;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		vista = inflater.inflate(R.layout.fragment_search, container, false);
		read_ingredients_json();

		SearchAdapt listadap = new SearchAdapt(ingredientes);
		RecyclerView recyclerView = vista.findViewById(R.id.ingredientespepe);
		recyclerView.setLayoutManager(new GridLayoutManager(
			vista.getContext(), 3));
		recyclerView.setAdapter(listadap);
		return vista;
	}


	private void read_ingredients_json() {
		String json_data;
		Gson gson = new Gson();

		try {
			InputStream is = vista.getContext().getAssets().open("recetas.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json_data = new String(buffer);
			ingredientes = gson.fromJson(json_data, String[][].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
