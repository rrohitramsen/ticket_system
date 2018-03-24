package com.ticketsystem.entity;

/**
 * Created by rohitkumar on 04/02/17.
 */
public class Ticket {

    private final String registrationNumber;
    private final String carColour;
    private final Integer parkingSlot;

    public Ticket(String registrationNumber, String carColour, Integer parkingSlot) {
        this.registrationNumber = registrationNumber;
        this.carColour = carColour;
        this.parkingSlot = parkingSlot;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getCarColour() {
        return carColour;
    }

    public Integer getParkingSlot() {
        return parkingSlot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        if (!registrationNumber.equals(ticket.registrationNumber)) return false;
        if (!carColour.equals(ticket.carColour)) return false;
        return parkingSlot.equals(ticket.parkingSlot);

    }

    @Override
    public int hashCode() {
        int result = registrationNumber.hashCode();
        result = 31 * result + carColour.hashCode();
        result = 31 * result + parkingSlot.hashCode();
        return result;
    }
}
