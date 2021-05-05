package dev.cotapro.mx.recetas;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Transaction;

@Dao
public interface RecetasDAO {

	@Transaction
	public void insertRecipe(Receta re, Ingrediente ing, Pasos pas);
}
