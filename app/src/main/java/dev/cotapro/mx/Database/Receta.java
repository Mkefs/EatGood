package dev.cotapro.mx.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class Receta {
	@PrimaryKey
	public long key;
	public String name;
	public String chef;
	public float rating;
	public String thumbPath;
}
