package dev.cotapro.mx;

import android.content.Context;
import androidx.room.Room;
import dev.cotapro.mx.recetas.GuardadosDB;
import retrofit2.Retrofit;

public class FeedData {
	private static boolean started = false;
	public static GuardadosDB db;
	public static Retrofit kiwilimon;
	public static Retrofit ingredientes;

	public static void init(Context ctx) {
		if(started)
			return;
		kiwilimon = new Retrofit.Builder()
			.baseUrl("")
			.build();
		db = Room.databaseBuilder(ctx,
				GuardadosDB.class, "Rectas_Guardadas")
				.build();
		started = true;
	}
}
