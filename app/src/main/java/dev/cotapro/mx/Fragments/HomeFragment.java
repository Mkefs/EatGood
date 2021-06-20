package dev.cotapro.mx.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.cotapro.mx.Adapters.HomeRecipeAdapter;
import dev.cotapro.mx.Database.Receta;
import dev.cotapro.mx.R;
import dev.cotapro.mx.Utils.RequestData;

public class HomeFragment extends Fragment {
	private HomeRecipeAdapter adapter;
	private RecyclerView recyclerView;
	private final Executor executor;
	private final Handler handler;
	private SwipeRefreshLayout refreshLayout;
	private int page;
	private boolean loading;

	public HomeFragment() {
		adapter = new HomeRecipeAdapter(2);
		executor = Executors.newSingleThreadExecutor();
		handler = new Handler(Looper.myLooper());
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View vista = inflater.inflate(R.layout.fragment_home, container, false);

		page = 0;
		loading = false;

		recyclerView = vista.findViewById(R.id.guardados_recycle);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
		recyclerView.setAdapter(adapter);

		refreshLayout = vista.findViewById(R.id.swipe_home);
		refreshLayout.setRefreshing(true);
		refreshLayout.setOnRefreshListener(this::refreshRecipes);

		return vista;
	}

	@Override
	public void onStart() {
		super.onStart();
		refreshRecipes();
	}

	public void refreshRecipes() {
		adapter.recetas.clear();
		adapter.notifyDataSetChanged();
		page = 0;
		loading = true;
		executor.execute(this::getRecipes);
	}

	public void getRecipes() {
		Receta[] recetas = RequestData.db.recetasDAO().get_recetas(page * 10);
		handler.post(() -> {
			refreshLayout.setRefreshing(false);
			if (recetas != null) {
				if (recetas.length > 0) {
					adapter.recetas.addAll(Arrays.asList(recetas));
					adapter.notifyDataSetChanged();
					page++;
				} else
					return;
			} else {
				Toast.makeText(getContext(),
					"No se han podido cargar las recetas!",
					Toast.LENGTH_LONG).show();
			}
			loading = false;
		});
	}
}

