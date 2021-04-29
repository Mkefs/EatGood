package mx.mcardenas.eatgood.items;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemsDAO {
	@Query("SELECT * FROM items")
	List<Item> getItems();

	@Delete
	void deleteItem(Item item);

	@Insert
	void insertAll(Item... items);
}
