package com.ticketsystem.service;

import com.ticketsystem.entity.Ticket;
import com.ticketsystem.entity.TicketStore;

/**
 * Created by rohitkumar on 04/02/17.
 */
public interface TicketService {

    /**
     *
     * @param colour
     * @return List of registration numbers for cars with the given color.
     */
    String findRegistrationNumbersForCarsWithColour(String colour);


    /**
     * Find slot number for the given registration number.
     * @param registrationNumber
     * @return slot number
     */
    String findCarSlotNumberForRegistrationNumber(String registrationNumber);


    /**
     * Find slot numbers for the cars with the given colour.
     * @param colour
     * @return List of sot numbers.
     */
    String findSlotNumbersForCarsWithColour(String colour);

    /**
     * Issue Ticket.
     * @param registrationNumber
     * @param colour
     * @param parkingSlotNumber
     * @return Ticket
     */
    Ticket issueTicket(String registrationNumber, String colour, Integer parkingSlotNumber);

    /**
     * Get Ticket store instance.
     * @return TicketStore
     */
    TicketStore getTicketStore();

    /**
     * Display the current status of parking lot.
     */
    String status();

}
