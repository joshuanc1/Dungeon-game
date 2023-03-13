package Dungeon.Game.dao;
import Dungeon.Game.model.Hunter;
import java.util.List;

public interface HunterDao {

    Hunter getHunter(int hunterId);

    List<Hunter> list();

    Hunter getHunterWithName(String name);

    List<Hunter> getHunterListByTeamId(int teamId);

    Hunter createHunter(Hunter hunter);

    void updateHunter(Hunter hunter);

    void deleteHunter(int hunterId);

    String getHunterBackstory(String name);

    boolean getAliveStatusHunter(int hunterId);

    //void addHunterToTeamById(int teamId, int hunterId);
}

