package com.ticketsystem.entity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by rohitkumar on 04/02/17.
 *
 * ParkingLot is a Singleton Class.
 */
public class ParkingLot implements Serializable, Cloneable {

    private int capacity;

    public void reset() {
        init(capacity);
    }

    private static class InstanceHolder {
        public static final ParkingLot PARKING_LOT_INSTANCE = new ParkingLot();
    }

    private TreeSet<ParkingSlot> availableParkingSlots;
    private Set<ParkingSlot> occupiedParkingSlots;

    private ParkingLot() {

        if (InstanceHolder.PARKING_LOT_INSTANCE != null) {
            throw new IllegalArgumentException();
        }
    }

    public static ParkingLot getParkingLotInstance() {
        return InstanceHolder.PARKING_LOT_INSTANCE;
    }

    public void init(int capacity) {

        this.capacity = capacity;
        availableParkingSlots = new TreeSet<ParkingSlot>(new Comparator<ParkingSlot>() {
            public int compare(ParkingSlot o1, ParkingSlot o2) {
                return o1.getSlotNumber().compareTo(o2.getSlotNumber());
            }
        });

        for (int slotNumber = 1; slotNumber <= capacity; slotNumber++) {
            ParkingSlot parkingSlot = new ParkingSlot(slotNumber);
            availableParkingSlots.add(parkingSlot);
        }

        occupiedParkingSlots = new HashSet<ParkingSlot>();
    }

    public boolean isParkingAvailable() {
        return availableParkingSlots.isEmpty();
    }

    public boolean isParkingEmpty() {
        return occupiedParkingSlots.isEmpty();
    }

    private Object readResolve() {
        return getParkingLotInstance();
    }

    public ParkingSlot findParkingSlot() {

        ParkingSlot parkingSlot = availableParkingSlots.first();
        availableParkingSlots.remove(parkingSlot);
        occupiedParkingSlots.add(parkingSlot);

        return parkingSlot;
    }

    public boolean isParkingSlotFree(Integer slotNumber) {
        for (ParkingSlot parkingSlot : occupiedParkingSlots) {
            if (parkingSlot.getSlotNumber().equals(slotNumber)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param slotNumber
     * @return Parking Slot after marking it available else return null.
     */
    public ParkingSlot leaveParkingSlot(final Integer slotNumber) {

        for(ParkingSlot parkingSlot : occupiedParkingSlots) {
            if (parkingSlot.getSlotNumber().equals(slotNumber)) {
                occupiedParkingSlots.remove(parkingSlot);
                availableParkingSlots.add(parkingSlot);
                return parkingSlot;
            }
        }

        return null;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return getParkingLotInstance();
    }


}
