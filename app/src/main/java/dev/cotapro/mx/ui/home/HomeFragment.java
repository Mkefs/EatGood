package dev.cotapro.mx.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import dev.cotapro.mx.R;

public class HomeFragment extends Fragment {
	View vista;
	Text texto;
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		vista = inflater.inflate(R.layout.home_layout, container, false);
		// Mi codigo
		texto = vista.findViewById(R.id.textView);

		return vista;
	}
}
