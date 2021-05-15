package dev.cotapro.mx.ui.feed;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import dev.cotapro.mx.FeedData;
import dev.cotapro.mx.R;
import dev.cotapro.mx.api.ApiManagement;
import dev.cotapro.mx.api.Datos;
import dev.cotapro.mx.api.Recetas;
import kotlin.collections.ArrayDeque;

public class

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
		AsyncTask.execute(new Runnable() {
			@Override
			public void run() {

				Recetas recetas= Datos.getfeed(FeedData.kiwilimon);


				if(recetas == null)
				{
					System.out.println("Error");
				}
				System.out.println(recetas.payload[0].cn);
				elements= new ArrayList<>();

				ListAdapter listAdapter=new ListAdapter(elements, context);
				RecyclerView recyclerView= vista.findViewById(R.id.recycleRecetas);
				recyclerView.setHasFixedSize(true);
				recyclerView.setLayoutManager(new LinearLayoutManager(context));
				recyclerView.setAdapter(listAdapter);
			}
		});



		/*elements.add(new ListElement("#3BB637", "Chiles en nogada", "a", "3 estrellas"));
		elements.add(new ListElement("#3BB637", "Costilla en salsa verde", "Paco Esparza", "4 estrellas"));
		elements.add(new ListElement("#3BB637", "Tacos de sal", "Roberto Pinto", "5 estrellas"));
		elements.add(new ListElement("#3BB637", "Frijoles fritos con elote", "a", "4 estrellas"));*/

	}
	/*public void Datillos(){

	}*/




}
