package dev.cotapro.mx.recetas;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import dev.cotapro.mx.FeedData;

@Dao
public interface RecetasDAO {
	@Insert
	public long insert_receta(Receta receta);

	@Delete
	public void delete_receta(Receta receta);

	// Get recetas
	@Query("SELECT id, name FROM recipes")
	public Receta[] get_recetas();

	// Get receta info
	@Query("SELECT id, json FROM recipes WHERE id = :id")
	public Receta get_receta(int id);
}