package com.ticketsystem.service;

import com.ticketsystem.entity.ParkingLot;
import com.ticketsystem.entity.ParkingSlot;

/**
 * Created by rohitkumar on 04/02/17.
 */
public interface ParkingLotService {

    /**
     * Create parking lot with the given size.
     * @param parkingLotSize
     * @return {@link ParkingLot}
     */
    String createParkingLot(int parkingLotSize);

    /**
     * Park the vehicle with the given registration number and colour.
     * @param registrationNumber
     * @param Colour
     * @return {@link ParkingSlot}
     */
    String park(String registrationNumber, String Colour);

    /**
     * Un park the Vehicle from the given slot number.
     * @param slotNumber
     * @return True and False.
     */
    boolean unPark(Integer slotNumber);

    /**
     *
     * @return ParkingLot
     */
    ParkingLot getParkingLot();


    void resetParkingLot();
}
