package dev.cotapro.mx;

import android.content.Context;
import androidx.room.Room;
import dev.cotapro.mx.recetas.GuardadosDB;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FeedData {
	private static boolean started = false;
	public static GuardadosDB db;
	public static Retrofit kiwilimon;
	public static Retrofit ingredientes;

	public static void init(Context ctx) {
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
			.baseUrl("")
			.build();
    db = Room.databaseBuilder(ctx,
			GuardadosDB.class, "Rectas_Guardadas")
			.build();
	}
}
