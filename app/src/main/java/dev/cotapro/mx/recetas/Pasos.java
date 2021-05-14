package dev.cotapro.mx.recetas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "steps")
public class Pasos {
	@PrimaryKey
	public int id;

	@ColumnInfo(name = "step")
	public String paso;

	@ColumnInfo(name = "recipe_id")
	public int receta;
}
