package Dungeon.Game.dao;
import org.springframework.jdbc.core.JdbcTemplate;
import Dungeon.Game.model.Item;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcItemDao implements ItemDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcItemDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Item getItem (int itemId){
        Item item = null;
        String sql = "SELECT item_id, name, stock, description, type_id " +
                "FROM item " +
                "WHERE item_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, itemId);
        if(result.next()){
            item = mapRowToItem(result);
        }
        return item;
    }
    @Override
    public List<Item> getItemListByType(int itemType){
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM item i" +
                "JOIN item_type it ON i.item_id = it.item_id " +
                "WHERE type_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, itemType);
        while(result.next()){
            itemList.add(mapRowToItem(result));
        }
        return itemList;
    }

    @Override
    public List<Item> getItemListByEquipmentId(int equipmentId){
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM equipment WHERE equipment_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, equipmentId);
        while(result.next()){
            items.add(mapRowToItem(result));
        }
        return items;
    }
    @Override
    public List<Item> getItemListByHunterID(int hunterID){
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM item i " +
                "JOIN inventory in ON i.item_id = in.slot1 " +
                "JOIN hunter h ON in.inventory_id = h.hunter_id " +
                "WHERE hunter_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, hunterID);
        while(result.next()){
            items.add(mapRowToItem(result));
        }
        return items;
    }
    @Override
    public List<Item> getItemListByMonsterID(int monsterId){
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM item i " +
                "JOIN monster_item mi ON i.item_id = mi.item_id " +
                "JOIN monster m ON mi.monster_id = m.monster_id " +
                "WHERE monster_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, monsterId);
        while(result.next()){
            items.add(mapRowToItem(result));
        }
        return items;

    }
    @Override
    public Item createItem(Item item){
        String sql = "INSERT INTO item (name, stock, description, typeID) " +
                "VALUES (?, ?, ?, ?) RETURNING item_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, item.getName(), item.getStock(), item.getDescription(), item.getTypeID());
        return getItem(newId);
    }
    @Override
    public void updateItem(Item item){
        String sql = "UPDATE item SET name = ?, stock = ?, description = ?, typeID = ? " +
                "WHERE item_id = ?;";
        jdbcTemplate.update(sql, item.getItemID(), item.getName(), item.getStock(), item.getDescription(), item.getTypeID());
    }
    @Override
    public void deleteItemFromInventory(int invId, int itemId){
        String sql = "DELETE FROM inventory WHERE slot1 = ? AND inventory_id = ?;";
        jdbcTemplate.update(sql, invId, itemId);
    }

    @Override
    public void deleteItem(int itemId){
        String sql = "DELETE FROM item WHERE item_id = ?;";
        jdbcTemplate.update(sql, itemId);
    }

    public Item mapRowToItem(SqlRowSet rowSet){
        Item item = new Item();
        item.setItemID(rowSet.getInt("item_id"));
        item.setName(rowSet.getString("name"));
        item.setStock(rowSet.getInt("stock"));
        item.setDescription(rowSet.getString("description"));
        item.setTypeID(rowSet.getInt("type_id"));
        return item;
    }


}