package Dungeon.Game.dao;

import Dungeon.Game.model.Equipment;

import java.util.List;

public interface EquipmentDao {

    Equipment getEquipment(int equipmentId);


    List<Equipment> getEquipmentListByTeamId(int teamId);

    Equipment createEquipment(Equipment equipment);

    void updateEquipment(Equipment equipment);

    void deleteEquipment(int equipmentId);
}
