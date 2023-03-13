package Dungeon.Game.model;

public class Monster {

    //Parameters Monster

    private int monsterId;
    private String name;
    private boolean alive;
    private String description;

    //Getter & Setter


    public int getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(int monsterId) {
        this.monsterId = monsterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Constructor

    public Monster(int monsterId, String name, boolean alive, String description){
        this.monsterId = monsterId;
        this.name = name;
        this.alive = alive;
        this.description = description;
    }

    public Monster(){
        //TODO CONSIDER STATS FOR MONSTERS
    };
}
