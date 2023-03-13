package Dungeon.Game.dao;

import Dungeon.Game.model.Stat;

import java.util.List;

public interface StatDao {


    Stat getStat(int hunterId);
    List<Stat> getStatByTeamId(int teamId);

    Stat getStatByMonsterId(int monsterId);

    Stat createStat(Stat stat);

    void updateStat(Stat stat);

    void deleteStat(int statId);

}