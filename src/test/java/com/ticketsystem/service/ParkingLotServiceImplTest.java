package com.ticketsystem.service;

import com.ticketsystem.config.TicketSystemJunitConfig;
import com.ticketsystem.entity.ParkingSlot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by rohitkumar on 04/02/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicketSystemJunitConfig.class, loader=AnnotationConfigContextLoader.class)
public class ParkingLotServiceImplTest {

    @Autowired
    ParkingLotService parkingLotService;

    @Test
    public void testParkingLotService() {
        assertThat(
                "class com.ticketsystem.service.ParkingLotServiceImpl",
                equalTo(this.parkingLotService.getClass().toString()));
    }

    @Test
    public void testCreateParkingLot() {
        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);

        assertNotNull(parkingLotService.createParkingLot(6));
    }

    @Test
    public void testPark() {
        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);

        parkingLotService.createParkingLot(6);
        ParkingSlot expected = new ParkingSlot(1);
        //ParkingSlot actual = parkingLotService.park("KA-01-HH-1234", "White");

        //assertEquals(expected, actual);
    }

    @Test
    public void testParkWithParkingLot() {
        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);

        String message = "Sorry, Please create parking lot before parking.";
        assertEquals(message, parkingLotService.park("KA-01-HH-1234", "White"));

    }

    @Test
    public void testParkWithParkingFull() {
        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);

        parkingLotService.createParkingLot(6);
        parkingLotService.park("KA-01-HH-1234", "White");
        parkingLotService.park("KA-01-HH-9999", "White");
        parkingLotService.park("KA-01-BB-0001", "Black");
        parkingLotService.park("KA-01-HH-7777", "Res");
        parkingLotService.park("KA-01-HH-2701", "Blue");
        parkingLotService.park("KA-01-HH-3141", "Black");

        String message = "Sorry, parking lot is full.";
        assertEquals(message, parkingLotService.park("KA-01-HH-8888", "White"));
    }

    @Test
    public void testUnPark() {
        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);

        parkingLotService.createParkingLot(6);
        //ParkingSlot parkingSlot = parkingLotService.park("KA-01-HH-1234", "White");

        //Boolean expected = true;
        //Boolean actual = parkingLotService.unPark(parkingSlot.getSlotNumber());

        //assertEquals(expected, actual);
    }

    @Test
    public void testUnParkWithoutParkingLot() {
        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);

        String message = "Sorry, Please create parking lot.";
        assertFalse(message, parkingLotService.unPark(1));
    }

    @Test
    public void testUnParkEmptyParkingLot() {
        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);
        parkingLotService.createParkingLot(6);

        String message = "Sorry, Parking Lot is already free";
        assertFalse(message, parkingLotService.unPark(1));
    }

    @Test
    public void testUnParkAlreadyEmptySlot() {

        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);

        parkingLotService.createParkingLot(6);
        parkingLotService.park("KA-01-HH-1234", "White");
        parkingLotService.park("KA-01-HH-9999", "White");
        parkingLotService.park("KA-01-BB-0001", "Black");
        parkingLotService.park("KA-01-HH-7777", "Res");
        parkingLotService.park("KA-01-HH-2701", "Blue");
        parkingLotService.park("KA-01-HH-3141", "Black");
        parkingLotService.unPark(1);

        String message = "Sorry, Parking Lot is already free";
        assertFalse(message, parkingLotService.unPark(1));
    }

    @Test
    public void testResetClearsTheParkingLot() {

        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);

        parkingLotService.createParkingLot(6);
        parkingLotService.park("KA-01-HH-1234", "White");

        parkingLotService.resetParkingLot();
        assertThat(parkingLotService.getParkingLot().isParkingEmpty(), equalTo(true));
    }
}
