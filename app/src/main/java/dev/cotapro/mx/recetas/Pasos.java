package dev.cotapro.mx.recetas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "steps")
public class Pasos {
	 @ColumnInfo(name = "step")
	 public String paso;

	 @ColumnInfo(name = "recipe_id")
	public int receta;
}
