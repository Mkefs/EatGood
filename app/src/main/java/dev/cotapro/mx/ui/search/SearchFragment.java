package dev.cotapro.mx.ui.search;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.nio.channels.AsynchronousByteChannel;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dev.cotapro.mx.FeedData;
import dev.cotapro.mx.R;
import dev.cotapro.mx.api.Ingredientes;
import dev.cotapro.mx.api_ingredientes.Datos;

public class SearchFragment extends Fragment {
	View vista;
	TextView textView;
	Context context;
	String[][] seleccionadas;
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		vista = inflater.inflate(R.layout.search_layout, container, false);
        context = vista.getContext();



		Executor executor = Executors.newSingleThreadExecutor();
		Handler handler = new Handler(Looper.getMainLooper());
		executor.execute(new Runnable() {
			@Override
			public void run() {
				String[][] ingredientes = Datos.getnombres(FeedData.ingredientes);
				handler.post(new Runnable() {
					@Override
					public void run() {
						LinearLayout linearLayout = vista.findViewById(R.id.linear);

						for(String[] ingrediente : ingredientes) {
							TextView textView = new TextView(context);
							ImageView nombre = new ImageView(context);


								textView.setText(ingrediente[1]);
							linearLayout.addView(textView);
							String context = "https://ingredients-eatgood.000webhostapp.com/imagen?n=" + ingrediente[2];
							Glide.with(nombre)
								.load(context).placeholder(R.drawable.ic_launcher_background)
								.error(R.drawable.ic_launcher_background).into(nombre);
							linearLayout.addView(nombre);
						}
					}
				});
				System.out.println("Loco esto esta mas dificil de lo que pensaba :D");
			}
		});


		return vista;
	}
}
