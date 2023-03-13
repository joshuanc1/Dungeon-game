package Dungeon.Game.dao;

import Dungeon.Game.model.Stat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcStatDao implements StatDao {


    private final JdbcTemplate jdbcTemplate;
    public JdbcStatDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Stat getStat(int hunterId){
        Stat stat = null;
        String sql = "SELECT * FROM stat s JOIN hunter h ON s.stat_id = h.hunter_id WHERE hunter_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, hunterId);
        if(result.next()){
            stat =   mapRowToStatHunter(result);
        }
        return stat;
    }

    //TODO TEST SQL STRING
    @Override
    public List<Stat> getStatByTeamId(int teamId) {
        List<Stat> statList = new ArrayList<>();
        String sql = "SELECT * FROM stat s " +
                "JOIN hunter h ON s.stat_id = h.hunter_id " +
                "JOIN team t ON h.hunter_id = t.hunter_slot1 " +
                "WHERE team_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, teamId);
        while(result.next()){
            statList.add(mapRowToStatHunter(result));
        }
        return statList;
    }

    @Override
    public Stat getStatByMonsterId(int monsterId){
        Stat stat = null;
        String sql = "SELECT * FROM stat s JOIN monster m ON s.stat_id = m.monster_id WHERE monster_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, monsterId);
        if(result.next()){
            stat =   mapRowToStatHunter(result);
        }
        return stat;
    }

    @Override
    public Stat createStat(Stat stat){
        Stat returnedStat = null;
        String sql = "INSERT INTO stat (level, stat_id, hp, mp, dex, str, def, exp, exp_needed)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            returnedStat = jdbcTemplate.queryForObject(sql, Stat.class, stat.getLevel(), stat.getStatId(), stat.getHP(),
                    stat.getMP(), stat.getDEX(), stat.getSTR(), stat.getDEF(), stat.getExp(), stat.getExpNeeded());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return returnedStat;
    }

    @Override
    public void updateStat(Stat stat) {
        String sql = "UPDATE stat SET level = ?, hp = ?, mp = ?, dex = ?, str = ?, def = ?, exp = ?, exp_needed = ? " +
                "WHERE stat_id = ?;";
        jdbcTemplate.update(sql, stat.getStatId());
    }
    @Override
    public void deleteStat(int statId){
        String sql = "DELETE FROM stat WHERE stat_id = ?;";
        jdbcTemplate.update(sql, statId);
    }


    public Stat mapRowToStatHunter(SqlRowSet rowSet){
        Stat stat = new Stat();
        stat.setLevel(rowSet.getInt("level"));
        stat.setStatId(rowSet.getInt("stat_id"));
        stat.setHP(rowSet.getInt("hp"));
        stat.setMP(rowSet.getInt("mp"));
        stat.setDEX(rowSet.getInt("dex"));
        stat.setSTR(rowSet.getInt("str"));
        stat.setDEF(rowSet.getInt("def"));
        stat.setExp(rowSet.getDouble("exp"));
        stat.setExpNeeded(rowSet.getDouble("exp_needed"));
        return stat;
    }

    /*
    public Stat mapRowToStatMonster(SqlRowSet rowSet){
        Stat stat = new Stat();
        stat.setLevel(rowSet.getInt("level"));
        stat.setStatId(rowSet.getInt("stat_id"));
        stat.setHP(rowSet.getInt("hp"));
        stat.setMP(rowSet.getInt("mp"));
        stat.setDEX(rowSet.getInt("dex"));
        stat.setSTR(rowSet.getInt("str"));
        stat.setDEF(rowSet.getInt("def"));
        stat.setExpGiven(rowSet.getDouble("exp_given"));
        return stat;
    }*/


}
