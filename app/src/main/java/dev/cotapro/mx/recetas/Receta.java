package dev.cotapro.mx.recetas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class Receta {
	@PrimaryKey
	public int id;

	@ColumnInfo(name = "chef_name")
	public String chef_n;

	@ColumnInfo(name = "chef_lastname")
	public String	

	@ColumnInfo(name = "calories")
	public int calorias;

	@ColumnInfo(name = "cooked")
	public int cocinado;

	@ColumnInfo(name = "cook_time")
	public String tiempo;

	@ColumnInfo(name = "title")
	public String title;

	@ColumnInfo(name = "description")
	public String descripcion;

	@ColumnInfo(name = "favs")
	public String favoritos;

	@ColumnInfo(name = "image")
	public String image;

	@ColumnInfo(name = "key")
	public int key;

	@ColumnInfo(name = "rating")
	public float rating;

	@ColumnInfo(name = "date")
	public String fecha;
}
