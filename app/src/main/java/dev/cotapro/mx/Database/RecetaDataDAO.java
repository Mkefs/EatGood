package dev.cotapro.mx.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RecetaDataDAO  {
	@Insert
	long insert_data(RecetaData data);

	@Query("DELETE FROM recipeData WHERE `key`=:key")
	void delete_data(long key);

	@Query("SELECT * FROM recipeData WHERE `key`=:key")
	RecetaData get_recipe(long key);
}
