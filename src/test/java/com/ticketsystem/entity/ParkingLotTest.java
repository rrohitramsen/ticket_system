package com.ticketsystem.entity;

import com.ticketsystem.config.TicketSystemJunitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;

/**
 * Created by rohitkumar on 04/02/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicketSystemJunitConfig.class, loader=AnnotationConfigContextLoader.class)
public class ParkingLotTest {

    @Test
    public void testIsParkingSlotFree() {
        ParkingLot parkingLot = ParkingLot.getParkingLotInstance();
        parkingLot.init(6);
        assertTrue(parkingLot.isParkingSlotFree(3));
    }

    @Test
    public void testIsParkingEmpty(){
        ParkingLot parkingLot = ParkingLot.getParkingLotInstance();
        parkingLot.init(6);
        assertTrue(parkingLot.isParkingEmpty());
    }

    @Test
    public void testIsParkingAvailable(){
        ParkingLot parkingLot = ParkingLot.getParkingLotInstance();
        parkingLot.init(6);
        assertTrue(!parkingLot.isParkingAvailable());
    }

    @Test
    public void testFindParkingSlot(){
        ParkingLot parkingLot = ParkingLot.getParkingLotInstance();
        parkingLot.init(6);

        ParkingSlot expected = new ParkingSlot(1);
        ParkingSlot actual = parkingLot.findParkingSlot();

        assertEquals(expected, actual);
    }

}
