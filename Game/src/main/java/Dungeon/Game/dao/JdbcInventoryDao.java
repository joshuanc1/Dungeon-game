package Dungeon.Game.dao;
import org.springframework.jdbc.core.JdbcTemplate;
import Dungeon.Game.model.Inventory;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcInventoryDao implements InventoryDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcInventoryDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Inventory getInventory(int hunterId){
        Inventory inventory = null;
        String sql = "SELECT * FROM inventory i JOIN hunter ON h i.inventory_id = h.hunter_id WHERE hunter_id= ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, hunterId);
        if(result.next()){
            inventory = mapRowToInventory(result);
        }
        return inventory;
    }
    @Override
    public Inventory createInventory(Inventory inventory){
        String sql = "INSERT INTO inventory (slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING inventory_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, inventory.getSlot1(),inventory.getSlot2(), inventory.getSlot3(), inventory.getSlot4(), inventory.getSlot5(), inventory.getSlot6(), inventory.getSlot7(), inventory.getSlot8());
        return getInventory(newId);
    }
    @Override
    public void updateInventory(Inventory inventory){
        String sql = "UPDATE inventory " +
                "SET slot1 = ?, slot2 = ?, slot3 = ?, slot4 = ?, slot5 = ?, slot6 = ?, slot7 = ?, slot8 = ? " +
                "WHERE inventory_id = ?;";
        jdbcTemplate.update(sql, inventory.getInventoryId(), inventory.getSlot1(), inventory.getSlot2(), inventory.getSlot3(), inventory.getSlot4(), inventory.getSlot5(), inventory.getSlot6(), inventory.getSlot7(), inventory.getSlot8());
    }
    @Override
    public void deleteInventory(int inventoryId){
        String sql = "DELETE inventory WHERE inventory_id = ?;";
        jdbcTemplate.update(sql, inventoryId);
    }

    public Inventory mapRowToInventory(SqlRowSet rowSet){
        Inventory inventory = new Inventory();
        inventory.setInventoryId(rowSet.getInt("inventory_id"));
        inventory.setSlot1(rowSet.getInt("slot1"));
        inventory.setSlot2(rowSet.getInt("slot2"));
        inventory.setSlot3(rowSet.getInt("slot3"));
        inventory.setSlot4(rowSet.getInt("slot4"));
        inventory.setSlot5(rowSet.getInt("slot5"));
        inventory.setSlot6(rowSet.getInt("slot6"));
        inventory.setSlot7(rowSet.getInt("slot7"));
        inventory.setSlot8(rowSet.getInt("slot8"));
        return inventory;
    }

}