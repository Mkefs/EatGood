package dev.cotapro.mx;

import retrofit2.Retrofit;

public class FeedData {
	private static boolean started = false;
	public static Retrofit kiwilimon;
	public static Retrofit ingredientes;

	public void init() {
		if(started)
			return;
		kiwilimon = new Retrofit.Builder()
			.baseUrl("")
			.build();
	}

}
