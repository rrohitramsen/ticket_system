package com.ticketsystem.command;

import com.ticketsystem.entity.ParkingLot;
import com.ticketsystem.service.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create Parking Lot Command.
 * Created by rohitkumar on 04/02/17.
 */
public class CreateParkingLot implements Command<ParkingLot> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateParkingLot.class);

    private Integer parkingLotSize;
    private ParkingLotService parkingLotService;

    public CreateParkingLot() {
    }

    public CreateParkingLot(Integer parkingLotSize) {
        this.parkingLotSize = parkingLotSize;
    }

    /**
     *
     * @return parking slot number.
     */
    public String execute() {

        String result = "";
        if (parkingLotService != null) {
            result = parkingLotService.createParkingLot(parkingLotSize);
        } else {
            result = "ParkingLotService is not available.";
            LOGGER.info(result);
        }
        return result;
    }

    public Integer getParkingLotSize() {
        return parkingLotSize;
    }

    public void setParkingLotSize(Integer parkingLotSize) {
        this.parkingLotSize = parkingLotSize;
    }

    public ParkingLotService getParkingLotService() {
        return parkingLotService;
    }

    public void setParkingLotService(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }
}
