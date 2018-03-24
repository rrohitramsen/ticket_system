package com.ticketsystem.command;

import com.ticketsystem.config.TicketSystemJunitConfig;
import com.ticketsystem.service.ParkingLotService;
import com.ticketsystem.service.ParkingLotServiceImpl;
import com.ticketsystem.service.TicketService;
import com.ticketsystem.service.TicketServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by rohitkumar on 04/02/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicketSystemJunitConfig.class, loader=AnnotationConfigContextLoader.class)
public class ParkTest {

    @Autowired
    Park park;

    @Test
    public void testPark() {
        assertEquals(
                "class com.ticketsystem.command.Park",
                this.park.getClass().toString());
    }

    @Test
    public void testExecuteParkingLotNotCreated() {

        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);

        Park park = new Park("KA-01-HH-1234", "White");
        park.setParkingLotService(parkingLotService);

        String message = "Sorry, Please create parking lot before parking.";
        assertThat(park.execute(), equalTo(message));
    }

    @Test
    public void testExecuteParkingLotServiceNotInitialized() {

        Park park = new Park("KA-01-HH-1234", "White");

        String message = "ParkingLotService is not available";
        assertThat(park.execute(), equalTo(message));
    }

    @Test
    public void testExecuteSuccess() {

        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);

        parkingLotService.createParkingLot(6);
        Park parkCommand = new Park("KA-01-HH-1234", "White");
        parkCommand.setParkingLotService(parkingLotService);

        assertThat(parkCommand.execute(), equalTo("Allocated slot number: 1"));
    }

    @Test
    public void testExecuteParkingLotFull() {

        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);

        parkingLotService.createParkingLot(6);
        parkingLotService.park("KA-01-HH-1234", "White");
        parkingLotService.park("KA-01-HH-9999", "White");
        parkingLotService.park("KA-01-BB-0001", "Black");
        parkingLotService.park("KA-01-HH-7777", "Res");
        parkingLotService.park("KA-01-HH-2701", "Blue");
        parkingLotService.park("KA-01-HH-3141", "Black");

        Park park = new Park("KA-01-HH-1234", "White");
        park.setParkingLotService(parkingLotService);

        String messsage = "Sorry, parking lot is full.";

        assertEquals(messsage, park.execute());
    }
}
