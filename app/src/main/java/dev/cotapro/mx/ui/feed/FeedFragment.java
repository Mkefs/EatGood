package dev.cotapro.mx.ui.feed;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.gsm.GsmCellLocation;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.cotapro.mx.FeedData;
import dev.cotapro.mx.R;
import dev.cotapro.mx.api.ApiManagement;
import dev.cotapro.mx.api.Datos;
import dev.cotapro.mx.api.Receta;
import dev.cotapro.mx.api.Recetas;
import kotlin.collections.ArrayDeque;
<<<<<<< HEAD

public class

=======

public class

>>>>>>> dev
FeedFragment extends Fragment {
	View vista;
	List<ListElement> elements;
	Toolbar toolbar;
	Context context;

	@Nullable
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
		elements = new ArrayList<>();
		Executor executor = Executors.newSingleThreadExecutor();
		Handler handler = new Handler(Looper.getMainLooper());
		executor.execute(new Runnable() {
			@Override
			public void run() {

				String json = Datos.getfeed(FeedData.kiwilimon, 1);
				Gson gson = new Gson();
				Recetas recetas = gson.fromJson(json, Recetas.class);
<<<<<<< HEAD
				//Link para la imagen

=======
>>>>>>> dev

				if (recetas == null) {
					System.out.println("Error");
					return;
				}

<<<<<<< HEAD
				for (int i = 0; i < recetas.payload.length; i++) {
=======
				for(int i = 0; i < recetas.payload.length; i++) {
					if(recetas.payload[i].k!=""){
>>>>>>> dev
					String dominio="https://cdn.kiwilimon.com/recetaimagen/"+recetas.payload[i].k+"/"+recetas.payload[i].i;
					elements.add(new ListElement(dominio,
						recetas.payload[i].n,
						recetas.payload[i].cn,
						recetas.payload[i].vr));
<<<<<<< HEAD
				}

=======
					}
				}
>>>>>>> dev
				handler.post(new Runnable() {
					@Override
					public void run() {
						ListAdapter listAdapter=new ListAdapter(context, elements);
						RecyclerView recyclerView= vista.findViewById(R.id.recycleRecetas);
						recyclerView.setHasFixedSize(true);
						recyclerView.setLayoutManager(new LinearLayoutManager(context));
						recyclerView.setAdapter(listAdapter);

					}
				});
			}
		});

	}
<<<<<<< HEAD





=======
>>>>>>> dev
}
