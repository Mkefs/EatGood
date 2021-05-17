package dev.cotapro.mx.recetas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class Receta {
	@PrimaryKey
	public int id;

	@ColumnInfo(name = "json_receta")
	public String receta;
}
