package dev.cotapro.mx.recetas;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Receta.class}, version = 1)
public abstract class GuardadosDB extends RoomDatabase {
	public abstract RecetasDAO recetas();
}