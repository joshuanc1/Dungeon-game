package Dungeon.Game.dao;
import org.springframework.jdbc.core.JdbcTemplate;
import Dungeon.Game.model.Monster;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMonsterDao implements MonsterDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMonsterDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Monster getMonster(int monsterId){
        Monster monster = null;
        String sql = "SELECT * FROM monster WHERE monster_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, monsterId);
        if(result.next()){
            monster = mapRowToMonster(result);
        }
        return monster;
    }

    @Override
    public List<Monster> getMonsterListByLevel(int level){
        List<Monster> monsterList = new ArrayList<>();
        String sql = "SELECT * FROM monster m JOIN stat s ON m.monster_id = s.stat_id WHERE s.level = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, level);
        while(result.next()){
            monsterList.add(mapRowToMonster(result));
        }
        return monsterList;

    }
    //TODO STRING SQL LOOK AT IT
    @Override
    public List<Monster> getMonsterListByDropItemName(String itemName){
        List<Monster> monsters = new ArrayList<>();
        String sql = "SELECT name FROM monster m " +
                "JOIN item_monster im ON m.drop_id = im.drop_id " +
                "JOIN item i ON im.item_id = i.item_id " +
                "WHERE i.name = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, itemName);
        while(result.next()){
            monsters.add(mapRowToMonster(result));
        }
        return monsters;
    }

    @Override
    public Monster createMonster(Monster monster){
        String sql = "INSERT INTO monster (name, alive, description) " +
                "VALUES (?, ?, ?) RETURNING monster_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, monster.getName());

        return getMonster(newId);
    }

    @Override
    public void updateMonster(Monster monster){
        String sql = "UPDATE monster " +
                "SET name = ?, alive = ?, description = ? " +
                "WHERE monster_id = ?;";
        jdbcTemplate.update(sql, monster.getName(), monster.getMonsterId());

    }

    @Override
    public void deleteMonster(int monsterId){
        String sql = "DELETE monster " +
                "WHERE monster_id = ?;";
        jdbcTemplate.update(sql, monsterId);

    }

    public boolean getAliveStatusMonster(int monsterId){
        boolean alive = false;
        String sql = "SELECT alive FROM monster WHERE monster_id = ?;";
        try {
            alive = jdbcTemplate.queryForObject(sql, Boolean.class, monsterId);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return alive;
    }

    public Monster mapRowToMonster(SqlRowSet rowSet){
        Monster monster = new Monster();
        monster.setMonsterId(rowSet.getInt("monster_id"));
        monster.setAlive(rowSet.getBoolean("alive"));
        monster.setName(rowSet.getString("name"));
        return monster;
    }

}
