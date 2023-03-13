package org.example.Hunter;
import javax.validation.constraints.*;

public class Hunter {

    //Hunter Parameters

    private int hunterId;

    @NotBlank(message = "Name cannot be empty")
    private String name;
    private int typeId;
    @NotNull(message = "Cannot be null")
    private boolean alive;
    private String backstory;



    //Getters & Setters;

    public int getHunterId() {
        return hunterId;
    }

    public void setHunterId(int hunterId) {
        this.hunterId = hunterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getBackstory() {
        return backstory;
    }

    public void setBackstory(String backstory) {
        this.backstory = backstory;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    //Constructors

    public Hunter(int hunterId, String name, int typeId, String backstory, boolean alive){
        this.hunterId = hunterId;
        this.name = name;
        this.typeId = typeId;
        this.backstory = backstory;
        this.alive = alive;
    }

    public Hunter(){
    }


    /*public void addStatPoint(Hunter hunter, int choice){
        Stat stat = hunter.getStat();
        switch (choice){
            case 1:

        }
    }

    public void levelUp(double expGiven){
        double exp = stat.getExp();
        double expNeeded = stat.getExpNeeded();
        exp += expGiven;
        if(exp >= expNeeded){
            System.out.println(getName() + " leveled up!");
            exp -= expNeeded;
            stat.setExp(exp);
            expNeeded *= 1.3;
            stat.setExpNeeded(expNeeded);
        }
    }*/
}
