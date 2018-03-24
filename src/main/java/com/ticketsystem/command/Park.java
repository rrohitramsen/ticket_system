package com.ticketsystem.command;

import com.ticketsystem.entity.ParkingSlot;
import com.ticketsystem.service.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Park command.
 * Created by rohitkumar on 04/02/17.
 */
public class Park implements Command<ParkingSlot> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Park.class);

    private String registrationNumber;
    private String colour;
    private ParkingLotService parkingLotService;


    public Park() {
    }

    public Park(String registrationNumber, String colour) {
        this.registrationNumber = registrationNumber;
        this.colour = colour;
    }

    public String execute() {

        ParkingSlot parkingSlot = null;
        String result = "";
        if (parkingLotService != null) {
            result = parkingLotService.park(registrationNumber, colour);
         } else {
            result = "ParkingLotService is not available";
            LOGGER.info(result);
        }
        return result;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColour() {
        return colour;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public ParkingLotService getParkingLotService() {
        return parkingLotService;
    }

    public void setParkingLotService(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }
}
