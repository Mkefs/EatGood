package dev.cotapro.mx.Utils;

import android.content.Context;

import androidx.room.Room;

import java.io.IOException;
import java.io.StringWriter;

import dev.cotapro.mx.Database.GuardadosDB;
import dev.cotapro.mx.KiwilimonApi.ApiManagement;
import dev.cotapro.mx.KiwilimonApi.RecetaEntity;
import dev.cotapro.mx.KiwilimonApi.RecetasEntity;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RequestData {
	private static boolean started = false;
	public static GuardadosDB db;
	private static ApiManagement.API_INTERACTION kiwilimon;

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

	public static class Ingredients {
		private static String ingredients_url =
			"https://ingredients-eatgood.000webhostapp.com/i/%s";
		public static String getIngredientImg (String name) {
			return String.format(ingredients_url, name);
		}
	}

	public static class Kiwilimon {
		private static final String image_url = "https://cdn7.kiwilimon.com/recetaimagen/";

		public static String getImageUrl(String key, String imagen) {
			String url = image_url + "%s/%s";
			return String.format(url, key, imagen);
		}
		public static String getThumbUrl(String resol, String key, String img) {
			String url = image_url + "%s/%s/%s.webp";
			return String.format(url, key, resol, img);
		}

		static public RecetasEntity get_feed(int page) {
			Call<RecetasEntity> consulta = kiwilimon.feed_json(page);
			try {
				Response<RecetasEntity> respuesta = consulta.execute();
				return respuesta.body();
			} catch (IOException exception) {
				return null;
			}
		}
		static public RecetasEntity get_search(String[] ingredientes, int page) {
			StringBuilder busqueda = new StringBuilder();
			for (String ingrediente : ingredientes)
				busqueda.append(ingrediente).append(" ");
			Call<RecetasEntity> consulta = kiwilimon.search_json(
				busqueda.toString(), page);
			try {
				Response<RecetasEntity> respuesta = consulta.execute();
				return respuesta.body();
			} catch (IOException exception) {
				return null;
			}
		}

		static public RecetaEntity get_recipe(long key) {
			Call<RecetaEntity> consulta = kiwilimon.recipe_json(key);
			try {
				Response<RecetaEntity> respuesta = consulta.execute();
				return respuesta.body();
			} catch (IOException exception) {
				return null;
			}
		}
	}
}
