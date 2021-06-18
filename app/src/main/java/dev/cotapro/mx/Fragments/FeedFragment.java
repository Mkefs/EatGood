package dev.cotapro.mx.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.cotapro.mx.Adapters.RecipeAdapter;
import dev.cotapro.mx.KiwilimonApi.DescripcionEntity;
import dev.cotapro.mx.KiwilimonApi.RecetasEntity;
import dev.cotapro.mx.R;
import dev.cotapro.mx.Utils.RequestData;

public class FeedFragment extends Fragment {
	private FragmentManager manager;
	private SwipeRefreshLayout refreshLayout;
	private final SearchFragment searchFragment;
	private RecyclerView rview;
	private FrameLayout searchFrameLayout;
	private SearchView searchView;
	private final Executor executor;
	private final Handler handler;
	private final RecipeAdapter adapter;
	boolean loading = false;
	boolean charge = false;
	int page = 1;
	ImageView image;

	public FeedFragment() {
		executor = Executors.newSingleThreadExecutor();
		handler = new Handler(Looper.myLooper());
		adapter = new RecipeAdapter(2);
		searchFragment = new SearchFragment();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View vista = inflater.inflate(R.layout.fragment_feed, container, false);
		manager = getChildFragmentManager();
		adapter.bottomReached = this::bottomReached;

		searchFrameLayout = vista.findViewById(R.id.searchFragment);
		refreshLayout = vista.findViewById(R.id.refresh);
		refreshLayout.setOnRefreshListener(this::refreshData);
		refreshLayout.setRefreshing(true);

		searchView = vista.findViewById(R.id.search);
		searchView.setOnQueryTextListener(queryTextListener);
		searchView.setOnQueryTextFocusChangeListener(this::closedSearch);

		rview = vista.findViewById(R.id.recycleRecetas);
		rview.setLayoutManager(new LinearLayoutManager(getContext()));
		rview.setHasFixedSize(true);
		rview.setAdapter(adapter);

		image =  vista.findViewById(R.id.image);

		executor.execute(this::getRecipes);
		return vista;
	}

	public void getRecipes() {
		loading = true;
		RecetasEntity recetasEntity = RequestData.Kiwilimon.get_feed(page);
		handler.post(() -> {
			refreshLayout.setRefreshing(false);
			if(recetasEntity != null) {
				image.setVisibility(View.GONE);
				rview.setVisibility(View.VISIBLE);
				if (recetasEntity.quantity > 0) {
					for (DescripcionEntity desc : recetasEntity.payload)
						if (!desc.key.isEmpty())
							adapter.recetas.add(desc);
					adapter.notifyDataSetChanged();
					page++;
				} else
					return;
			} else {
				Toast.makeText(getContext(),
						"No se han podido cargar las recetas!",
						Toast.LENGTH_LONG).show();
				rview.setVisibility(View.GONE);
				image.setVisibility(View.VISIBLE);
				charge = true;
			}
			loading = false;

		});
	}

	public void refreshData() {
		if (!charge) {
			adapter.recetas.clear();
			page = 1;
			executor.execute(this::getRecipes);
		} else{
			charge = false;
		}
		executor.execute(this::getRecipes);

	}

	public void bottomReached() {
		if(!loading) {
			loading = true;
			refreshLayout.setRefreshing(true);
			executor.execute(this::getRecipes);
		}
	}

	private final SearchView.OnQueryTextListener queryTextListener =
		new SearchView.OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				refreshLayout.setVisibility(View.GONE);
				searchFrameLayout.setVisibility(View.VISIBLE);
				searchFragment.searchQuery = new String[] { query };
				FragmentTransaction transaction = manager.beginTransaction();
				transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				if(searchFragment.isAdded())
					searchFragment.refreshSearch();
				else
					transaction.add(R.id.searchFragment, searchFragment);
				transaction.commit();
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				return false;
			}
		};

	public void closedSearch(View v, boolean b) {
		if (!b && searchView.getQuery().length() < 1) {
			FragmentTransaction transaction = manager.beginTransaction();
			transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
			transaction.remove(searchFragment);
			transaction.commit();
			refreshLayout.setVisibility(View.VISIBLE);
			searchFrameLayout.setVisibility(View.GONE);
		}
	}
}
