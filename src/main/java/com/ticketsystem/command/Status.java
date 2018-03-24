package com.ticketsystem.command;

import com.ticketsystem.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Status command.
 * Created by rohitkumar on 04/02/17.
 */
public class Status implements Command<Boolean> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Status.class);

    private TicketService ticketService;

    public Status() {
    }

    public String execute() {

        String result = "";
        if (ticketService != null) {
            result = ticketService.status();
        } else {
            result = "TicketService is not available";
            LOGGER.info(result);
        }
        return result;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }
}
