package Dungeon.Game.model;

public class Ability {

    //Parameters Ability
    //TODO ability limit? TO four?
    private int abilityId;
    private String name;
    private int dmgOrHeal;
    private String description;
    private int manaCost;

    //Getter & Setter

    public int getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(int abilityId) {
        this.abilityId = abilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDmgOrHeal() {
        return dmgOrHeal;
    }

    public void setDmgOrHeal(int dmgOrHeal) {
        this.dmgOrHeal = dmgOrHeal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    //Constructor

    public Ability(int abilityId, String name, int dmgOrHeal, String description, int manaCost){
        this.abilityId = abilityId;
        this.name = name;
        this.dmgOrHeal = dmgOrHeal;
        this.description = description;
        this.manaCost = manaCost;
    }
}