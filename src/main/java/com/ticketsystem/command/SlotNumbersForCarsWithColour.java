package com.ticketsystem.command;

import com.ticketsystem.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * SlotNumbersForCarsWithColour command.
 * Created by rohitkumar on 04/02/17.
 */
public class SlotNumbersForCarsWithColour implements Command<List<Integer>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlotNumbersForCarsWithColour.class);

    private String colour;
    private TicketService ticketService;

    public SlotNumbersForCarsWithColour() {
    }

    public SlotNumbersForCarsWithColour(String colour) {
        this.colour = colour;
    }

    public String execute() {

        List<Integer> slotNumbers = null;

        String result = "";
        if (ticketService != null) {
            result = ticketService.findSlotNumbersForCarsWithColour(colour);
        } else {
            result = "TicketService is not available";
            LOGGER.info(result);
        }

        return result;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }
}
