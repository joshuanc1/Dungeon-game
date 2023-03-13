package Dungeon.Game.controller;


import Dungeon.Game.model.Hunter;
import Dungeon.Game.dao.HunterDao;
import Dungeon.Game.dao.MonsterDao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

public class HunterController {
    private HunterDao hunterDao;
    private MonsterDao monsterDao;

    public HunterController(HunterDao hunterDao, MonsterDao monsterDao) {
        this.hunterDao = hunterDao;
        this.monsterDao = monsterDao;
    }

    @RequestMapping
    public Hunter getHunter(int id){
        return hunterDao.getHunter(id);
    }
}