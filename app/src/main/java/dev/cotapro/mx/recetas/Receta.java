package dev.cotapro.mx.recetas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class Receta {
	@PrimaryKey(autoGenerate = true)
	public int id;
	public String name;
	public String json;
}
