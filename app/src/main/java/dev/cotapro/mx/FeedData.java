package dev.cotapro.mx;

import android.content.Context;
import androidx.room.Room;

import java.io.IOException;
import java.util.ArrayList;

import dev.cotapro.mx.KiwilimonApi.ApiManagement;
import dev.cotapro.mx.Database.GuardadosDB;
import dev.cotapro.mx.Adapters.SearchAdapt;
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

	public static void init(Context ctx) {
		if(started)
			return;
		kiwilimon = new Retrofit.Builder()
			.addConverterFactory(ScalarsConverterFactory.create())
			.addConverterFactory(GsonConverterFactory.create())
			.baseUrl("https://gr.kiwilimon.com/v6/")
			.build()
			.create(dev.cotapro.mx.KiwilimonApi.ApiManagement.API_INTERACTION.class);
		db = Room.databaseBuilder(ctx,
			GuardadosDB.class, "Recetas_Guardadas")
			.build();
		started = true;
	}

	static public String get_feed(int page) {
		Call<ResponseBody> consulta = kiwilimon.feed_json(page);
		try {
			Response<ResponseBody> respuesta = consulta.execute();
			assert respuesta.body() != null;
			return respuesta.body().string();
		} catch (IOException exception) {
			return null;
		}
	}
	static public String get_search(ArrayList<SearchAdapt.ViewHolder> ingrediente) {
		StringBuilder ingredientes = new StringBuilder();
		for(int i = 0; i <= ingrediente.size(); i++) {
			ingredientes.append(ingrediente.get(i).texto.getText());
		}
		Call<ResponseBody> consulta = kiwilimon.search_json(ingredientes.toString(), 1);
		try {
			Response<ResponseBody> respuesta = consulta.execute();
			assert respuesta.body() != null;
			return respuesta.body().string();
		} catch (IOException exception) {
			return null;
		}
	}
	static public String get_recipe(long key){
		Call<ResponseBody> consulta = kiwilimon.recipe_json(key);
		try {
			Response<ResponseBody> respuesta = consulta.execute();
			assert respuesta.body() != null;
			return respuesta.body().string();
		} catch (IOException exception) {
			return null;
		}
	}

}
