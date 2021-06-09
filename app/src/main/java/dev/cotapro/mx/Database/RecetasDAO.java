package dev.cotapro.mx.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RecetasDAO {
	@Insert
	long insert_receta(Receta receta);

	@Delete
	void delete_receta(Receta receta);

	// Get recetas
	@Query("SELECT id, name FROM recipes")
	Receta[] get_recetas();

	// Get receta info
	@Query("SELECT id, json FROM recipes WHERE id = :id")
	Receta get_receta(long id);
}