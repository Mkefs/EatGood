package dev.cotapro.mx.recetas;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Ingrediente.class, Pasos.class, RecetasDAO.class}, version = 1)
public abstract class GuardadosDB extends RoomDatabase {
	public abstract IngredientesDAO ingredientesDAO();
	public abstract PasosDAO pasosDAO();
	public abstract RecetasDAO recetasDAO();
}
