package dev.cotapro.mx.ui.search;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.nio.channels.AsynchronousByteChannel;

import dev.cotapro.mx.FeedData;
import dev.cotapro.mx.R;
import dev.cotapro.mx.api_ingredientes.Datos;

public class SearchFragment extends Fragment {
	View vista;
	String[][] seleccionadas;
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		vista = inflater.inflate(R.layout.search_layout, container, false);

		AsyncTask.execute(new Runnable() {
			@Override
			public void run() {
				String[][] ingredientes = Datos.getnombres(FeedData.ingredientes);
				//Provecho guapo 
			}
		});

		return vista;
	}
}
