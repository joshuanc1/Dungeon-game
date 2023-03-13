package org.example.Hunter;

public class Stat {

    //Stat parameters

    private int level = 1;
    private int statId;
    private int HP;
    private int MP;
    private int DEX;
    private int STR;
    private int DEF;

    private double exp = 0.0;
    private double expNeeded = 50.0;
    private double expGiven;


    //Getters & Setters


    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getStatId() {
        return statId;
    }

    public void setStatId(int statId) {
        this.statId = statId;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public int getDEX() {
        return DEX;
    }

    public void setDEX(int DEX) {
        this.DEX = DEX;
    }

    public int getSTR() {
        return STR;
    }

    public void setSTR(int STR) {
        this.STR = STR;
    }

    public int getDEF() {
        return DEF;
    }

    public void setDEF(int DEF) {
        this.DEF = DEF;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public double getExpNeeded() {
        return expNeeded;
    }

    public void setExpNeeded(double expNeeded) {
        this.expNeeded = expNeeded;
    }

    public double getExpGiven() {
        return expGiven;
    }

    public void setExpGiven(double expGiven) {
        this.expGiven = expGiven;
    }

    //Constructor

    public Stat(int level, int statId, int HP, int MP, int DEX, int STR, int DEF, double exp, double expNeeded){
        this.level = level;
        this.statId = statId;
        this.HP = HP;
        this.MP = MP;
        this.DEX = DEX;
        this.STR = STR;
        this.DEF = DEF;
        this.exp = exp;
        this.expNeeded = expNeeded;
    }
    public Stat(){
    };


    public int randomNumberGeneratorBetween(int min, int max) {
        return  (int)Math.floor(Math.random() * (max - min + 1) + min);
    }


    public Stat randomCharacterStatGenerator(int typeId) {
        Stat stat = new Stat();
        switch(typeId){
            case 1: //TANK
                stat.setHP(randomNumberGeneratorBetween(10, 20));
                stat.setMP(randomNumberGeneratorBetween(1, 5));
                stat.setSTR(randomNumberGeneratorBetween(1, 6));
                stat.setDEF(randomNumberGeneratorBetween(4, 8));
                stat.setDEX(randomNumberGeneratorBetween(1, 4));
                stat.setExpNeeded(60);
                break;
            case 2: //HEALER
                stat.setHP(randomNumberGeneratorBetween(5, 10));
                stat.setMP(randomNumberGeneratorBetween(5, 10));
                stat.setSTR(randomNumberGeneratorBetween(1, 4));
                stat.setDEF(randomNumberGeneratorBetween(1, 6));
                stat.setDEX(randomNumberGeneratorBetween(4, 8));
                stat.setExpNeeded(70);
                break;
            case 3: //FIGHTER
                stat.setHP(randomNumberGeneratorBetween(7, 15));
                stat.setMP(randomNumberGeneratorBetween(1, 5));
                stat.setSTR(randomNumberGeneratorBetween(4, 8));
                stat.setDEF(randomNumberGeneratorBetween(1, 4));
                stat.setDEX(randomNumberGeneratorBetween(1, 6));
                stat.setExpNeeded(50);
                break;
        }
        return stat;
    }

   /* public void addStatPoint(Hunter hunter, int choice){
        Stat stat = hunter.getStat();
        switch (choice){
            case 1:

        }
    }

    public void levelUp(Hunter hunter, double expGiven){
        exp += expGiven;
        if(exp >= expNeeded){
            System.out.println(hunter.getName() + " leveled up!");
            exp -= expNeeded;
            expNeeded *= 1.3;
            level ++;

        }

    }*/
}