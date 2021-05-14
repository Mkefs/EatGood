package dev.cotapro.mx.recetas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredients")
public class Ingrediente {
	@PrimaryKey
	public int id;

	@ColumnInfo(name = "recipe_id")
	public int receta;

	@ColumnInfo(name = "ingredient")
	public String ingrediente;
}
