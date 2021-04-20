package mx.mcardenas.eatgood;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import mx.mcardenas.eatgood.api.ApiService;
import mx.mcardenas.eatgood.api.Info;
import mx.mcardenas.eatgood.ui.main.SectionsPagerAdapter;
import retrofit2.Call;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
		ViewPager viewPager = findViewById(R.id.view_pager);
		viewPager.setAdapter(sectionsPagerAdapter);
		TabLayout tabs = findViewById(R.id.tabs);
		tabs.setupWithViewPager(viewPager);
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://gr.kiwilimon.com/v6")
				.build();

		ApiService service = retrofit.create(ApiService.class);

		Call<Info> language, device = service.getFeed("es", "android");

	}
}