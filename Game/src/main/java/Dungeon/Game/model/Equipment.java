package Dungeon.Game.model;

public class Equipment {

    //Parameters Equipment

    private int equipmentId;
    private int weaponSlot;
    private int armorSlot;
    private int offHandSlot;
    private int bootSlot;
    private int ringSlot;


    //Getters & Setters


    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getWeaponSlot() {
        return weaponSlot;
    }

    public void setWeaponSlot(int weaponSlot) {
        this.weaponSlot = weaponSlot;
    }

    public int getArmorSlot() {
        return armorSlot;
    }

    public void setArmorSlot(int armorSlot) {
        this.armorSlot = armorSlot;
    }

    public int getOffHandSlot() {
        return offHandSlot;
    }

    public void setOffHandSlot(int offHandSlot) {
        this.offHandSlot = offHandSlot;
    }

    public int getBootSlot() {
        return bootSlot;
    }

    public void setBootSlot(int bootSlot) {
        this.bootSlot = bootSlot;
    }

    public int getRingSlot() {
        return ringSlot;
    }

    public void setRingSlot(int ringSlot) {
        this.ringSlot = ringSlot;
    }

    //Constructor

    public Equipment(int equipmentId, int weaponSlot, int armorSlot, int offHandSlot, int bootSlot, int ringSlot){
        this.equipmentId = equipmentId;
        this.weaponSlot = weaponSlot;
        this.armorSlot = armorSlot;
        this.offHandSlot = offHandSlot;
        this.bootSlot = bootSlot;
        this.ringSlot = ringSlot;
    }

    public Equipment(){};
}
