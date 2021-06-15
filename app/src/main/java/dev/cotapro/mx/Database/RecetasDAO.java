package dev.cotapro.mx.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;

@Dao
public interface RecetasDAO {
	@Insert
	long insert_receta(Receta receta);

	@Query("DELETE FROM recipes WHERE `key` = :key")
	void delete_receta(long key);

	@Query("SELECT * FROM recipes LIMIT :offset, 10")
	Receta[] get_recetas(int offset);
}