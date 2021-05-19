package dev.cotapro.mx.ui.search;

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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
	String[][] seleccionadas;
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		vista = inflater.inflate(R.layout.search_layout, container, false);
		Executor executor = Executors.newSingleThreadExecutor();
		Handler handler = new Handler(Looper.getMainLooper());
		executor.execute(new Runnable() {
			@Override
			public void run() {
				String[][] ingredientes = Datos.getnombres(FeedData.ingredientes);
				LinearLayout search_layout = (LinearLayout) vista.findViewById(R.id.kk);

				for(String[] ingrediente : ingredientes){
					System.out.println(ingrediente[0]);
					System.out.println(ingrediente[1]);
					System.out.println(ingrediente[2]);
				}

				System.out.println("Loco esto esta mas dificil de lo que pensaba :D");

			}
		});

		return vista;
	}
}
