package dev.cotapro.mx.recetas;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import dev.cotapro.mx.FeedData;

@Dao
public interface RecetasDAO {
	@Insert
	public long insertReceta(RecetasDAO receta);
}