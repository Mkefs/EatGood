package dev.cotapro.mx.ui.feed;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.cotapro.mx.FeedData;
import dev.cotapro.mx.R;
import dev.cotapro.mx.api.Recetas;

public class FeedFragment extends Fragment {
	View vista;
	Context context;

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		vista = inflater.inflate(R.layout.feed_layout, container, false);
		context= vista.getContext();
		init();
		return vista;
	}

	public void init(){
		Executor executor = Executors.newSingleThreadExecutor();
		Handler handler = new Handler(Looper.getMainLooper());
		executor.execute(() -> {
			String json = FeedData.get_feed(1);
			Gson gson = new Gson();
			Recetas recetas = gson.fromJson(json, Recetas.class);

			if (recetas == null) {
				System.out.println("Error");
				return;
			}

			handler.post(() -> {
				ListAdapter listAdapter = new ListAdapter(recetas.payload);
				RecyclerView recyclerView = vista.findViewById(R.id.recycleRecetas);
				recyclerView.setHasFixedSize(true);
				recyclerView.setLayoutManager(new LinearLayoutManager(context));
				recyclerView.setAdapter(listAdapter);
			});
		});

	}
}
