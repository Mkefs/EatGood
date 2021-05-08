package dev.cotapro.mx.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import dev.cotapro.mx.R;

public class HomeFragment extends Fragment {
	View vista;
	List<listfavoritos> elements;
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		vista = inflater.inflate(R.layout.home_layout, container, false);
		init();
	}
	public void init () {

		elements = new ArrayList<>();
		elements.add(new listfavoritos ( color: "#FFD751", name: "PIZZA", autor: "Carlos" ));
		elements.add(new listfavoritos ( color: "#FFD751", name: "FRIJOLES", autor: "Getse" ));
		elements.add(new listfavoritos ( color: "#FFD751", name: "CEREAL", autor: "Sayu" ));

		listadapter listadapter = new listadapter(elements, context: this);
		RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(context: this));
		recyclerView.setAdapter(listadapter);
		return vista;


	}
}
