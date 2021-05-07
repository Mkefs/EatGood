package dev.cotapro.mx.ui.home;

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

	Button btnSwitch;
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		vista = inflater.inflate(R.layout.home_layout, container, false);

		btnSwitch = vista.findViewById(R.id.btnSwitch);
		btnSwitch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View vista2) {
				Intent siguiente = new Intent(vista.getContext(), RecetaActivity.class);
				startActivity(siguiente);
			}
		});

		return vista;
	}
}

