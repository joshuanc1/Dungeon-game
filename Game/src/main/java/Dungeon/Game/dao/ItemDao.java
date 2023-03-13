package Dungeon.Game.dao;

import Dungeon.Game.model.Item;

import java.util.List;

public interface ItemDao {


    Item getItem (int itemId);

    List<Item> getItemListByEquipmentId(int equipmentId);

    List<Item> getItemListByType(int itemType);
    List<Item> getItemListByHunterID(int hunterID);
    List<Item> getItemListByMonsterID(int monsterId);

    Item createItem(Item item);

    void updateItem(Item item);

    void deleteItemFromInventory(int invId, int itemId);
    void deleteItem(int itemId);

}