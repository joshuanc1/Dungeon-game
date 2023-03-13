
package Dungeon.Game.dao;
import Dungeon.Game.model.Inventory;



import java.util.List;

public interface InventoryDao {

    Inventory getInventory(int hunterId);

    Inventory createInventory(Inventory inventory);

    void updateInventory(Inventory inventory);

    void deleteInventory(int inventoryId);
}
