package dev.cotapro.mx;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FeedData {
	private static boolean started = false;
	public static Retrofit kiwilimon;
	public static Retrofit ingredientes;

	public void init() {
		if(started)
			return;
		kiwilimon = new Retrofit.Builder()
				.addConverterFactory(ScalarsConverterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl("https://gr.kiwilimon.com/v6")
				.build();
		ingredientes = new Retrofit.Builder()
				.addConverterFactory(ScalarsConverterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl("")
				.build();
	}

}
