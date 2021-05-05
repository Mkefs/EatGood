package dev.cotapro.mx.recetas;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface IngredientesDAO {
	@Insert
	public void insertAll(Ingrediente... ingredientes);

	@Query("DELETE FROM ingredients WHERE recipe_id=:id")
	public void deleteIngredients(int id);
}
