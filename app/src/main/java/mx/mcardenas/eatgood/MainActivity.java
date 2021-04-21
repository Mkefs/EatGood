package mx.mcardenas.eatgood;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import mx.mcardenas.eatgood.api.ApiManagement;
import mx.mcardenas.eatgood.ui.main.SectionsPagerAdapter;
import retrofit2.Call;
import retrofit2.Response;
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

		ApiManagement.API_INTERACTION  interaction = retrofit.create(ApiManagement.API_INTERACTION.class);
		Call<String> consulta = interaction.feed_json("es", "android");
		try {
			Response<String> respuesta = consulta.execute();
			String resultado = respuesta.body();
			System.out.println(resultado);
		} catch (IOException e) {
			e.printStackTrace();
		}




	}
}