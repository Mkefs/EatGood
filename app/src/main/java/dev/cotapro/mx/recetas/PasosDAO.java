package dev.cotapro.mx.recetas;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PasosDAO {
	@Insert
	public void inertSteps(Pasos... pasos);

	@Query("DELETE FROM steps WHERE recipe_id=:id")
	public void deleteSteps(int id);
}
