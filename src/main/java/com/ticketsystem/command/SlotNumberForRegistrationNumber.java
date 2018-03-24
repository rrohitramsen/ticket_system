package com.ticketsystem.command;

import com.ticketsystem.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SlotNumberForRegistrationNumber command.
 * Created by rohitkumar on 04/02/17.
 */
public class SlotNumberForRegistrationNumber implements Command<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlotNumberForRegistrationNumber.class);

    private String registrationNumber;
    private TicketService ticketService;

    public SlotNumberForRegistrationNumber() {
    }

    public SlotNumberForRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String execute() {

        String result = "";
        if (ticketService != null) {
            result = ticketService.findCarSlotNumberForRegistrationNumber(registrationNumber);
        } else {
            result = "TicketService is not available";
            LOGGER.info(result);
        }
        return result;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }
}
