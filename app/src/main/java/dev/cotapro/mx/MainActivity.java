package dev.cotapro.mx;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.cotapro.mx.Fragments.FeedFragment;
import dev.cotapro.mx.Fragments.HomeFragment;
import dev.cotapro.mx.Fragments.IngredientFragment;
import dev.cotapro.mx.Utils.RequestData;

public class MainActivity extends AppCompatActivity {
	private Map<Integer, Fragment> fragmentMap;
	private FragmentManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manager = getSupportFragmentManager();
		setTheme(R.style.Theme_EatGood);
		setContentView(R.layout.activity_main);

		RequestData.init(this);
		setNavController();
	}

	private void setNavController() {
		BottomNavigationView navView = findViewById(R.id.nav_view);
		fragmentMap = new HashMap<>();
		fragmentMap.put(R.id.navigation_feed, new FeedFragment());
		fragmentMap.put(R.id.navigation_home, new HomeFragment());
		fragmentMap.put(R.id.navigation_search, new IngredientFragment());
		change_fragment(fragmentMap.get(R.id.navigation_home));

		navView.setOnNavigationItemSelectedListener(item -> {
			Fragment change = fragmentMap.get(item.getItemId());
			if(change == null)
				return false;
			change_fragment(change);
			return true;
		});
	}

	private void change_fragment(Fragment frag) {
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		// Hide all fragments
		List<Fragment> fragmentList = manager.getFragments();
		for(Fragment fragment : fragmentList)
			transaction.hide(fragment);
		// Then show current fragment
		if(frag.isAdded())
			transaction.show(frag);
		else
			transaction.add(R.id.nav_host_fragment, frag);
		transaction.commit();
	}
}
