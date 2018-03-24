package com.ticketsystem.entity;

/**
 * Created by rohitkumar on 04/02/17.
 */
public class ParkingSlot {

    private Integer slotNumber;
    private Integer levelNumber;
    private Boolean occupied;

    public ParkingSlot(Integer slotNumber) {
        this(slotNumber, 1, false);
    }

    public ParkingSlot(Integer slotNumber, Integer levelNumber, Boolean occupied) {
        this.slotNumber = slotNumber;
        this.levelNumber = levelNumber;
        this.occupied = occupied;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Integer getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(Integer levelNumber) {
        this.levelNumber = levelNumber;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingSlot)) return false;

        ParkingSlot that = (ParkingSlot) o;

        return slotNumber.equals(that.slotNumber);

    }

    @Override
    public int hashCode() {
        return slotNumber.hashCode();
    }
}
