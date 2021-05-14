package dev.cotapro.mx;

import android.content.Context;
import androidx.room.Room;

import dev.cotapro.mx.api_ingredientes.ApiManagement;
import dev.cotapro.mx.recetas.GuardadosDB;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FeedData {
	private static boolean started = false;
	public static GuardadosDB db;
	public static dev.cotapro.mx.api.ApiManagement.API_INTERACTION kiwilimon;
	public static ApiManagement.API_INTERACION ingredientes;

	public static void init(Context ctx) {
		if(started)
			return;
		kiwilimon = new Retrofit.Builder()
				.addConverterFactory(ScalarsConverterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl("https://gr.kiwilimon.com/v6/")
				.build()
				.create(dev.cotapro.mx.api.ApiManagement.API_INTERACTION.class);
		ingredientes = new Retrofit.Builder()
				.addConverterFactory(ScalarsConverterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl("https://ingredients-eatgood.000webhostapp.com/")
				.build()
				.create(ApiManagement.API_INTERACION.class);
		db = Room.databaseBuilder(ctx,
			GuardadosDB.class, "Recetas_Guardadas")
			.build();
		started = true;
	}
}
