package Dungeon.Game.dao;

import Dungeon.Game.model.Monster;

import java.util.List;

public interface MonsterDao {

    Monster getMonster(int monsterId);
    List<Monster> getMonsterListByLevel(int level);
    List<Monster> getMonsterListByDropItemName(String itemName);
    Monster createMonster(Monster monster);
    void updateMonster(Monster monster);
    void deleteMonster(int monsterId);

    boolean getAliveStatusMonster(int monsterId);
}
