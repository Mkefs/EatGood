package dev.cotapro.mx.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import dev.cotapro.mx.R;
import dev.cotapro.mx.ui.recetas.RecetaActivity;

public class HomeFragment extends Fragment {
	View vista;
	Context contexto;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		vista = inflater.inflate(R.layout.home_layout, container, false);
		contexto = vista.getContext();

		return vista;
	}
}

