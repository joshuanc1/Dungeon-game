package Dungeon.Game.dao;

import Dungeon.Game.model.Equipment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEquipmentDao implements EquipmentDao{

    private final JdbcTemplate jdbcTemplate;

    JdbcEquipmentDao(DataSource dataSource){jdbcTemplate = new JdbcTemplate();}
    @Override
    public Equipment getEquipment(int equipmentId){
        Equipment equipment = null;
        String sql = "SELECT * FROM equipment WHERE equipment_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, equipmentId);
        if(result.next()){
            equipment = mapRowToEquipment(result);
        }
        return equipment;
    }
    @Override
    public List<Equipment> getEquipmentListByTeamId(int teamId){
        List<Equipment> equipmentList = new ArrayList<>();
        String sql = "SELECT * FROM equipment WHERE team_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, teamId);
        while(result.next()){
            equipmentList.add(mapRowToEquipment(result));
        }
        return equipmentList;
    }

    @Override
    public Equipment createEquipment(Equipment equipment){
        String sql = "INSERT INTO equipment (weapon_slot, armor_slot, offHand_slot, boot_slot, ring_slot) " +
                "VALUES (?,?,?,?,?);";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, equipment.getWeaponSlot(), equipment.getArmorSlot(), equipment.getOffHandSlot(), equipment.getBootSlot(), equipment.getRingSlot());
        return getEquipment(newId);
    }

    @Override
    public void updateEquipment(Equipment equipment){
        String sql = "UPDATE equipment SET weapon_slot = ?, armor_slot =?, offHand_slot =?, boot_slot=?, ring_slot=? " +
                "WHERE equipment_id = ?;";
        jdbcTemplate.update(sql, equipment.getWeaponSlot(), equipment.getArmorSlot(), equipment.getBootSlot(), equipment.getOffHandSlot(), equipment.getRingSlot(), equipment.getEquipmentId());
    }

    @Override
    public void deleteEquipment(int equipmentId){
        String sql = "DELETE equipment WHERE equipment_id = ?;";
        jdbcTemplate.update(sql, equipmentId);
    }

    public Equipment mapRowToEquipment(SqlRowSet rowSet){
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(rowSet.getInt("id"));
        equipment.setArmorSlot(rowSet.getInt("armor"));
        equipment.setBootSlot(rowSet.getInt("boot"));
        equipment.setWeaponSlot(rowSet.getInt("weapon"));
        equipment.setRingSlot(rowSet.getInt("ring"));
        equipment.setOffHandSlot(rowSet.getInt("offHand"));
        return equipment;
    }
}