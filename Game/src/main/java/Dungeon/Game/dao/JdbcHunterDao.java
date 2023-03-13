package Dungeon.Game.dao;
import org.springframework.jdbc.core.JdbcTemplate;
import Dungeon.Game.model.Hunter;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcHunterDao implements HunterDao{

    private final JdbcTemplate jdbcTemplate;
    public JdbcHunterDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Hunter getHunter(int hunterId){
        Hunter hunter = null;
        String sql = "SELECT * FROM hunter WHERE hunter_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, hunterId);
        if(result.next()){
            hunter = mapRowToHunter(result);
        }
        return hunter;
    }

    @Override
    public List<Hunter> list (){
        List<Hunter> hunters = new ArrayList<>();
        String sql = "SELECT * FROM hunter;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            hunters.add(mapRowToHunter(result));
        }
        return hunters;
    }
    @Override
    public Hunter getHunterWithName(String name){
        Hunter hunter = null;
        String sql = "SELECT * FROM hunter WHERE name = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, name);
        if(result.next()){
            hunter = mapRowToHunter(result);
        }
        return hunter;
    }

    //TODO CHECK SQL TO SEE IF IT WORKS
    @Override
    public List<Hunter> getHunterListByTeamId(int teamId){
        List<Hunter> hunters = new ArrayList<>();
        String sql = "SELECT hunter_id, name FROM hunter h JOIN Team t ON h.hunter_id = t.hunter_id WHERE t.team_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, teamId);
        while(result.next()){
            hunters.add(mapRowToHunter(result));
        }
        return hunters;
    }

    @Override
    public Hunter createHunter(Hunter hunter){
        String sql = "INSERT INTO hunter (name, type_id, alive, backstory) " +
                "VALUES (?, ?, ?, ?) RETURNING hunter_id;";
        Integer ID = jdbcTemplate.queryForObject(sql, Integer.class, hunter.getName());
        return getHunter(ID);

    }

    @Override
    public void updateHunter(Hunter hunter){
        String sql = "UPDATE hunter " +
                "SET name = ?, type_id = ?, alive =?, backstory = ? " +
                "WHERE hunter_id = ?;";
        jdbcTemplate.update(sql, hunter.getName(), hunter.getHunterId());

    }

    @Override
    public void deleteHunter(int hunterId){
        String sql = "DELETE hunter " +
                "WHERE hunterId = ?;";
        jdbcTemplate.update(sql, hunterId);
    }


    //TODO ADD HUNTER TO TEAM BY ID;
    /*@Override
    public void addHunterToTeamById(int teamId, int hunterId){

    }*/
    @Override
    public String getHunterBackstory(String name){
        String backstory = "";
        String sql = "SELECT backstory FROM hunter WHERE name = ?;";
        try {
            backstory = jdbcTemplate.queryForObject(sql, String.class, name);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return backstory;
    }
    @Override
    public boolean getAliveStatusHunter(int hunterId){
        boolean alive = false;
        String sql = "SELECT alive FROM hunter WHERE hunter_id = ?;";
        try{
            alive = jdbcTemplate.queryForObject(sql, Boolean.class, hunterId);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return alive;
    }

    public Hunter mapRowToHunter(SqlRowSet rowSet){
        Hunter hunter = new Hunter();
        hunter.setHunterId(rowSet.getInt("hunter_id"));
        hunter.setName(rowSet.getString("name"));
        hunter.setAlive(rowSet.getBoolean("alive"));
        hunter.setBackstory(rowSet.getString("backstory"));
        return hunter;
    }
}
