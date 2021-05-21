package dev.cotapro.mx;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import dev.cotapro.mx.ui.feed.FeedFragment;
import dev.cotapro.mx.ui.home.HomeFragment;
import dev.cotapro.mx.ui.search.SearchFragment;

public class MainActivity extends AppCompatActivity {
	Fragment feed, home, search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		feed = new FeedFragment();
		home = new HomeFragment();
		search = new SearchFragment();
		FeedData.init(this);

		setContentView(R.layout.activity_main);
		setNavController();
	}

	private void setNavController() {
		BottomNavigationView navView = findViewById(R.id.nav_view);
		navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				boolean ret = true;
				switch (item.getItemId()) {
					case R.id.navigation_home:
						change_fragment(home);
						break;
					case R.id.navigation_feed:
						change_fragment(feed);
						break;
					case R.id.navigation_search:
						change_fragment(search);
						break;
					default:
						ret = false;
						break;
				}
				return ret;
			}
		});
		change_fragment(home);
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		// AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
		// 	R.id.navigation_home, R.id.navigation_feed, R.id.navigation_search)
		// 	.build();
		// NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		// NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
		// NavigationUI.setupWithNavController(navView, navController);
	}

	private void change_fragment(Fragment frag) {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.nav_host_fragment, frag);
		transaction.commit();
	}



}