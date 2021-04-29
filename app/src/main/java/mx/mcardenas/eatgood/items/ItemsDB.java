package mx.mcardenas.eatgood.items;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {Item.class})
public abstract class ItemsDB extends RoomDatabase {
	public abstract ItemsDAO itemsDao();
}
