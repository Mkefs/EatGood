package dev.cotapro.mx.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.cotapro.mx.Adapters.RecipeAdapter;
import dev.cotapro.mx.KiwilimonApi.DescripcionEntity;
import dev.cotapro.mx.KiwilimonApi.RecetasEntity;
import dev.cotapro.mx.R;
import dev.cotapro.mx.Utils.RequestData;

public class SearchFragment extends Fragment {
	private final RecipeAdapter adapter;
	private final Executor executor;
	private final Handler handler;
	private RecyclerView recyclerView;
	private SwipeRefreshLayout refreshLayout;
	public String searchQuery;
	private int page;
	boolean loading;

	public SearchFragment() {
		loading = false;
		executor = Executors.newSingleThreadExecutor();
		handler = new Handler(Looper.myLooper());
		adapter = new RecipeAdapter(2);
	}

	public void getSearch() {
		RecetasEntity recetasEntity = RequestData.Kiwilimon.get_search(
			new String[]{searchQuery}, page);
		handler.post(() -> {
			refreshLayout.setRefreshing(false);
			if (recetasEntity != null) {
				if (recetasEntity.quantity > 0) {
					for (DescripcionEntity desc : recetasEntity.payload)
						if (desc.type.equals("receta"))
							adapter.recetas.add(desc);
					adapter.notifyDataSetChanged();
					page++;
				} else
					return;
			} else {
				Toast.makeText(getContext(),
					"No se ha podido hacer la busqueda!",
					Toast.LENGTH_LONG).show();
			}
			loading = false;
			recyclerView.setVisibility(View.VISIBLE);
		});

	}

	@Override
	public View onCreateView(@NotNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_search, container, false);

		page = 1;
		loading = true;
		adapter.recetas.clear();
		adapter.bottomReached = this::bottomReached;

		refreshLayout = view.findViewById(R.id.swipe);
		refreshLayout.setRefreshing(true);
		refreshLayout.setOnRefreshListener(this::refreshSearch);

		recyclerView = view.findViewById(R.id.recetas);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
		recyclerView.setAdapter(adapter);

		executor.execute(this::getSearch);
		return view;
	}

	public void refreshSearch() {
		loading = true;
		adapter.recetas.clear();
		page = 1;
		executor.execute(this::getSearch);
	}

	public void bottomReached() {
		if(!loading) {
			loading = true;
			refreshLayout.setRefreshing(true);
			executor.execute(this::getSearch);
		}
	}
}

