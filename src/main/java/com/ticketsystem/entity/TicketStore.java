package com.ticketsystem.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rohitkumar on 04/02/17.
 */
public class TicketStore implements Serializable, Cloneable {

    public static class InstanceHolder {
        private static final TicketStore TICKET_STORE_INSTANCE = new TicketStore();
    }

    private Map<ParkingSlot, Ticket> ticketStoreMap;

    private Map<String, List<Integer>> slotNumbersPerColor;
    private Map<String, Integer> slotNumberForRegistrationNumber;
    private Map<String, List<String>> registrationNumbersPerColor;

    private TicketStore() {
        if (InstanceHolder.TICKET_STORE_INSTANCE != null) {
            throw new IllegalArgumentException();
        }
        init();
    }

    private void init() {
        ticketStoreMap = new HashMap<ParkingSlot, Ticket>();
        slotNumbersPerColor = new HashMap<String, List<Integer>>();
        slotNumberForRegistrationNumber = new HashMap<String, Integer>();
        registrationNumbersPerColor = new HashMap<String, List<String>>();
    }


    /**
     * Reset the Ticket Store, if Parking Lot is created again.
     */
    public void reset() {
        if (this != null) {
            init();
        }
    }


    public void removeTicket(ParkingSlot parkingSlot) {

        Ticket removeTicket = ticketStoreMap.get(parkingSlot);
        this.ticketStoreMap.remove(parkingSlot);
        this.slotNumbersPerColor.get(removeTicket.getCarColour()).remove(removeTicket.getParkingSlot());
        this.slotNumberForRegistrationNumber.remove(removeTicket.getRegistrationNumber());
        this.registrationNumbersPerColor.get(removeTicket.getCarColour()).remove(removeTicket.getRegistrationNumber());
    }

    public void addTicket(Ticket ticket, ParkingSlot parkingSlot) {

        this.ticketStoreMap.put(parkingSlot, ticket);
        this.slotNumberForRegistrationNumber.put(ticket.getRegistrationNumber(), parkingSlot.getSlotNumber());

        if(this.slotNumbersPerColor.get(ticket.getCarColour()) == null) {
            this.slotNumbersPerColor.put(ticket.getCarColour(), new ArrayList<>());
        }
        this.slotNumbersPerColor.get(ticket.getCarColour()).add(parkingSlot.getSlotNumber());

        if (this.registrationNumbersPerColor.get(ticket.getCarColour()) == null) {
            this.registrationNumbersPerColor.put(ticket.getCarColour(), new ArrayList<>());
        }
        this.registrationNumbersPerColor.get(ticket.getCarColour()).add(ticket.getRegistrationNumber());

    }

    public static TicketStore getTicketStoreInstance() {
        return InstanceHolder.TICKET_STORE_INSTANCE;
    }

    private Object readResolve() {
        return getTicketStoreInstance();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return getTicketStoreInstance();
    }

    public Map<ParkingSlot, Ticket> getTicketStoreMap() {
        return ticketStoreMap;
    }

    public Map<String, List<Integer>> getSlotNumbersPerColor() {
        return slotNumbersPerColor;
    }

    public Map<String, Integer> getSlotNumberForRegistrationNumber() {
        return slotNumberForRegistrationNumber;
    }

    public Map<String, List<String>> getRegistrationNumbersPerColor() {
        return registrationNumbersPerColor;
    }
}
