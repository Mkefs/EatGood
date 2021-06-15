package dev.cotapro.mx.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RecetasDAO {
	@Insert
	long insert_receta(Receta receta);

	@Query("DELETE FROM recipes WHERE `key`= :key")
	void delete_receta(long key);

	// Get recetas
	@Query("SELECT `key`, `name`, `chef`, `thumbPath` FROM recipes")
	Receta[] get_recetas();
}