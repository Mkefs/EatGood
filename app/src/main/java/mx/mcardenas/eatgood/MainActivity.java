package mx.mcardenas.eatgood;

import android.os.AsyncTask;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import mx.mcardenas.eatgood.api.ApiManagement;
import mx.mcardenas.eatgood.ui.main.SectionsPagerAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
	ApiManagement.API_INTERACTION interaction;

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
				.addConverterFactory(ScalarsConverterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl("https://gr.kiwilimon.com/v6/")
				.build();
		interaction = retrofit.create(ApiManagement.API_INTERACTION.class);

		//AsyncTask.execute(new Runnable() {
		//	@Override
		//	public void run() {
		//		Recetas recetas = Datos.getsearch(interaction);
		//	}
		//});
	}
}
