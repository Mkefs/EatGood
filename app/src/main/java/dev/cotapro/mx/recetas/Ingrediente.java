package dev.cotapro.mx.recetas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "ingredients")
public class Ingrediente {
	@ColumnInfo(name = "recipe_id")
	public int receta;

	@ColumnInfo(name = "ingredient")
	public String ingrediente;
}
