package dev.cotapro.mx;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import dev.cotapro.mx.Fragments.FeedFragment;
import dev.cotapro.mx.Fragments.HomeFragment;
import dev.cotapro.mx.Fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {
	public static Fragment feed = new FeedFragment();
	public static Fragment home = new HomeFragment();
	public static Fragment search = new SearchFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Theme_EatGood);
		FeedData.init(this);

		setContentView(R.layout.activity_main);
		setNavController();
	}

	@SuppressLint("NonConstantResourceId")
	private void setNavController() {
		BottomNavigationView navView = findViewById(R.id.nav_view);
		navView.setOnNavigationItemSelectedListener(item -> {
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
		});
		change_fragment(home);
	}

	private void change_fragment(Fragment frag) {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.nav_host_fragment, frag);
		transaction.commit();
	}
}