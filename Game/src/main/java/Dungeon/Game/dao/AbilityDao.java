package Dungeon.Game.dao;


import Dungeon.Game.model.Ability;

import java.util.List;

public interface AbilityDao {

    Ability getAbility(int abilityId);
    List<Ability> getAbilitiesByHunterID(int hunterId);

    List<Ability> getAbilitiesByMonsterId(int monsterId);

    Ability createAbility(Ability ability);

    void updateAbility(Ability ability);

    void deleteAbility(int abilityId);

}
