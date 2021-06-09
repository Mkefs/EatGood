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
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.cotapro.mx.FeedData;
import dev.cotapro.mx.R;
import dev.cotapro.mx.Database.Receta;

public class HomeFragment extends Fragment {
	View vista;
	Context contexto;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		vista = inflater.inflate(R.layout.fragment_home, container, false);
		contexto = vista.getContext();

		get_guardados();

		return vista;
	}

	private void get_guardados() {
		RecyclerView recyclerView = vista.findViewById(R.id.guardados_recycle);
		Handler handler = new Handler(Looper.getMainLooper());
		Executor exec = Executors.newSingleThreadExecutor();

		Gson gson = new Gson();
		exec.execute(() -> {
			Receta[] recetas = FeedData.db.recetas().get_recetas();
		});
	}
}

