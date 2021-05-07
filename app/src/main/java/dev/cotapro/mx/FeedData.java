package dev.cotapro.mx;

import android.content.Context;

import androidx.room.Room;

import dev.cotapro.mx.recetas.GuardadosDB;

public class FeedData {
	public static GuardadosDB db;

	static void init(Context ctx) {
		db = Room.databaseBuilder(ctx,
			GuardadosDB.class, "Rectas_Guardadas")
			.build();
	}
}
