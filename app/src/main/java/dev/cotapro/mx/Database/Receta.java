package dev.cotapro.mx.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class Receta {
	@PrimaryKey(autoGenerate = true)
	public long id;
	public String name;
	public String json;
}
