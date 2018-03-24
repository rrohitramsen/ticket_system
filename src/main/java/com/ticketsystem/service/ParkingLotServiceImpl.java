package com.ticketsystem.service;

import com.ticketsystem.entity.ParkingLot;
import com.ticketsystem.entity.ParkingSlot;
import com.ticketsystem.entity.Ticket;
import com.ticketsystem.entity.TicketStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rohitkumar on 04/02/17.
 */
@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParkingLotServiceImpl.class);

    private ParkingLot parkingLot;
    private TicketService ticketService;

    @Autowired
    public ParkingLotServiceImpl(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public String createParkingLot(int parkingLotSize) {

        LOGGER.info("Created a parking lot with "+parkingLotSize+" slots");
        String result = "Created a parking lot with "+parkingLotSize+" slots";
        parkingLot = ParkingLot.getParkingLotInstance();
        parkingLot.init(parkingLotSize);

        if (ticketService.getTicketStore() != null) {
            ticketService.getTicketStore().reset();
        }
        return result;
    }

    public String park(String registrationNumber, String colour) {

        ParkingSlot parkingSlot = null;
        String result = "";
        if (parkingLot == null) {
            result = "Sorry, Please create parking lot before parking.";
            LOGGER.info(result);
            return result;
        }

        if (parkingLot.isParkingAvailable()) {
            result = "Sorry, parking lot is full.";
            LOGGER.info(result);
            return result;

        } else {

            parkingSlot = parkingLot.findParkingSlot();
            Ticket ticket = ticketService.issueTicket(registrationNumber, colour, parkingSlot.getSlotNumber());

            TicketStore ticketStore = ticketService.getTicketStore();
            ticketStore.addTicket(ticket, parkingSlot);

            result = "Allocated slot number: " + parkingSlot.getSlotNumber();
            LOGGER.info(result);
            return result;
        }
    }

    public boolean unPark(Integer slotNumber) {

        if (parkingLot == null) {
            LOGGER.info("Sorry, Please create parking lot.");
            return false;
        }

        if (parkingLot.isParkingEmpty() || parkingLot.isParkingSlotFree(slotNumber)) {

            LOGGER.info("Sorry, Parking Lot is already free");
            return false;

        } else {

            ParkingSlot parkingSlot;
            if (( parkingSlot =  parkingLot.leaveParkingSlot(slotNumber)) != null) {

                LOGGER.info("Slot number "+slotNumber+" is free");
                TicketStore ticketStore = ticketService.getTicketStore();
                ticketStore.removeTicket(parkingSlot);
                return true;
            }

        }
        return false;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    @Override
    public void resetParkingLot() {
        parkingLot.reset();

    }
}
