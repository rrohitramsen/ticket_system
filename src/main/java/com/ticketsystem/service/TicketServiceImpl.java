package com.ticketsystem.service;

import com.ticketsystem.entity.ParkingSlot;
import com.ticketsystem.entity.Ticket;
import com.ticketsystem.entity.TicketStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by rohitkumar on 04/02/17.
 */
@Service
public class TicketServiceImpl implements TicketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);

    private TicketStore ticketStore;

    public TicketServiceImpl() {
        ticketStore = TicketStore.getTicketStoreInstance();
    }

    public String findRegistrationNumbersForCarsWithColour(String colour) {

        List<String> registrationNumbersPerColour = null;
        String result = "";
        if (ticketStore.getRegistrationNumbersPerColor().get(colour) == null ||
                ticketStore.getRegistrationNumbersPerColor().get(colour).isEmpty()) {

            result = "Not Found";
            LOGGER.info(result);

        } else {

            registrationNumbersPerColour = ticketStore.getRegistrationNumbersPerColor().get(colour);
            final StringBuilder tempResult = new StringBuilder();
            registrationNumbersPerColour.forEach(registerNumber -> tempResult.append(registerNumber).append(",").append(" "));

            result = withoutTrailingComma(tempResult);
            LOGGER.info(result);
        }

        return result;
    }

    public String findCarSlotNumberForRegistrationNumber(String registrationNumber) {

        Integer slotNumber = null;
        String result = "";
        if (ticketStore.getSlotNumberForRegistrationNumber().get(registrationNumber) == null) {

            result = "Not Found";
            LOGGER.info(result);
        } else {
            slotNumber = ticketStore.getSlotNumberForRegistrationNumber().get(registrationNumber);
            result = slotNumber.toString();
            LOGGER.info(result);
        }

        return result;
    }

    public String findSlotNumbersForCarsWithColour(String colour) {
        String result = "";
        if (ticketStore.getSlotNumbersPerColor().get(colour) == null ||
                ticketStore.getSlotNumbersPerColor().get(colour).isEmpty()) {
            result = "Not Found";
            LOGGER.info(result);

        } else {
            result = convertMatchingSlotNumbersToStringResult(colour);
            LOGGER.info(result);
        }

        return result;
    }

    private String convertMatchingSlotNumbersToStringResult(String colour) {
        List<Integer> slotNumbers = ticketStore.getSlotNumbersPerColor().get(colour);
        return withoutTrailingComma(convertIntegersToConcatenatedString(slotNumbers));
    }

    private String withoutTrailingComma(StringBuilder tempResult) {
        return tempResult.toString().substring(0, tempResult.length() - 2);
    }

    private StringBuilder convertIntegersToConcatenatedString(List<Integer> slotNumbers) {
        final StringBuilder tempResult = new StringBuilder();
        slotNumbers.forEach(slotNumber -> tempResult.append(slotNumber).append(",").append(" "));
        return tempResult;
    }

    public Ticket issueTicket(String registrationNumber, String colour, Integer parkingSlotNumber) {
        return new Ticket(registrationNumber, colour, parkingSlotNumber);
    }

    public TicketStore getTicketStore() {
        return ticketStore;
    }

    public String status() {

        if (ticketStore.getTicketStoreMap().isEmpty()) {
            String result = "Sorry, parking lot is empty.";
            LOGGER.info(result);
            return result;
        } else {
            Map<ParkingSlot, Ticket> ticketStoreMap = ticketStore.getTicketStoreMap();
            Set<Map.Entry<ParkingSlot, Ticket>> entries = ticketStoreMap.entrySet();

            StringBuilder tempResult = new StringBuilder();
            tempResult.append(buildHeader());

            for (Map.Entry<ParkingSlot, Ticket> entry : entries) {
                tempResult.append(buildRow(entry));
            }
            String result = tempResult.toString();
            LOGGER.info(result);
            return result;
        }

    }

    private String buildRow(Map.Entry<ParkingSlot, Ticket> entry) {
        return entry.getKey().getSlotNumber().toString() + "\t" + "\t" +
                entry.getValue().getRegistrationNumber() + "\t" + "\t" +
                entry.getValue().getCarColour() + "\n";
    }

    private String buildHeader() {
        return "Slot No." + "\t" + "Registration Number" + "\t" + "\t" + "Colour";
    }
}
