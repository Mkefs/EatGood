package dev.cotapro.mx;

import android.content.Context;
import androidx.room.Room;

import java.io.IOException;
import java.util.ArrayList;

import dev.cotapro.mx.api.ApiManagement;
import dev.cotapro.mx.recetas.GuardadosDB;
import dev.cotapro.mx.ui.search.Listadap;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FeedData {
	private static boolean started = false;
	public static GuardadosDB db;
	public static ApiManagement.API_INTERACTION kiwilimon;

	static public String get_feed(int page) {
		Call<ResponseBody> consulta = kiwilimon.feed_json(1);
		try {
			Response<ResponseBody> respuesta = consulta.execute();
			return respuesta.body().string();
		} catch (IOException exception) {
			return null;
		}
	}
	static public String get_search(ArrayList<Listadap.ViewHolder> ingrediente) {
		String ingredientes = "";
		for(int i = 0; i <= ingrediente.size(); i++) {
			ingredientes = ingredientes + ingrediente.get(i).texto.getText();
		}
		Call<ResponseBody> consulta = kiwilimon.search_json(ingredientes, 1);
		try {
			Response<ResponseBody> respuesta = consulta.execute();
			return respuesta.body().string();
		} catch (IOException exception) {
			return null;
		}
	}
	static public String get_recipe(int key){
		Call<ResponseBody> consulta = kiwilimon.recipe_json(key);
		try {
			Response<ResponseBody> respuesta = consulta.execute();
			return respuesta.body().string();
		} catch (IOException exception) {
			return null;
		}
	}

	public static void init(Context ctx) {
		if(started)
			return;
		kiwilimon = new Retrofit.Builder()
				.addConverterFactory(ScalarsConverterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl("https://gr.kiwilimon.com/v6/")
				.build()
				.create(dev.cotapro.mx.api.ApiManagement.API_INTERACTION.class);
		db = Room.databaseBuilder(ctx,
			GuardadosDB.class, "Recetas_Guardadas")
			.build();
		started = true;
	}
}
