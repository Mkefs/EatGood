package dev.cotapro.mx.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.cotapro.mx.Adapters.SearchAdapt;
import dev.cotapro.mx.R;

public class SearchFragment extends Fragment {
	View vista;
	Context context;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		vista = inflater.inflate(R.layout.fragment_search, container, false);
        context = vista.getContext();

		Executor executor = Executors.newSingleThreadExecutor();
		Handler handler = new Handler(Looper.getMainLooper());
		executor.execute(() -> {
			String json_data;
			String[][] ingredientes;
			Gson gson = new Gson();

			try {
				InputStream is = context.getAssets().open("recetas.json");
				int size = is.available();
				byte[] buffer = new byte[size];
				is.read(buffer);
				is.close();
				json_data = new String(buffer);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}

			ingredientes = gson.fromJson(json_data, String[][].class);
			handler.post(() -> {
				SearchAdapt listadap = new SearchAdapt(ingredientes);
				RecyclerView recyclerView = vista.findViewById(R.id.ingredientespepe);
				recyclerView.setHasFixedSize(true);
				recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
				recyclerView.setAdapter(listadap);
			});
			System.out.println("Loco esto esta mas dificil de lo que pensaba :D");
		});

		return vista;
	}
}
