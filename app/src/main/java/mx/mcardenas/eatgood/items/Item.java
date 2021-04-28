package mx.mcardenas.eatgood.items;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Item {
	@PrimaryKey
	public int id;
	@ColumnInfo(name = "itemName")
	public String name;
	@ColumnInfo(name = "quantity")
	public int cantidad;
}
