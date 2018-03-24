package com.ticketsystem.command;

import com.ticketsystem.service.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Leave command.
 * Created by rohitkumar on 04/02/17.
 */
public class Leave implements Command<Boolean> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Leave.class);

    private Integer slotNumber;
    private ParkingLotService parkingLotService;

    public Leave() {
    }

    public Leave(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    /**
     *
     * @return True if slot is leave or false if not or slot was already empty.
     */
    public String execute() {

        if (parkingLotService != null) {
            parkingLotService.unPark(slotNumber);
            return "";
        } else {
            LOGGER.info("ParkingLotService is not available");
        }
        return "";
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public ParkingLotService getParkingLotService() {
        return parkingLotService;
    }

    public void setParkingLotService(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }
}
