package dev.cotapro.mx.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipeData")
public class RecetaData {
	@PrimaryKey
	public int key;
	public String imagePath;
	public String json;
}
