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

import static org.junit.Assert.*;

/**
 * Created by rohitkumar on 04/02/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicketSystemJunitConfig.class, loader=AnnotationConfigContextLoader.class)
public class StatusTest {

    @Autowired
    Status status;

    @Test
    public void testStatus() {
        assertEquals(
                "class com.ticketsystem.command.Status",
                this.status.getClass().toString());
    }

    @Test
    public void testExecuteTicketServiceNotInitialized() {

        Status status = new Status();
        String message = "TicketService is not available";
        // assertFalse(message, status.execute());
    }

    @Test
    public void testExecuteSuccess() {
        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);
        Status status = new Status();

        parkingLotService.createParkingLot(6);
        parkingLotService.park("KA-01-HH-1234", "White");
        parkingLotService.park("KA-01-HH-9999", "White");
        parkingLotService.park("KA-01-BB-0001", "Black");
        parkingLotService.park("KA-01-HH-7777", "Res");
        parkingLotService.park("KA-01-HH-2701", "Blue");
        parkingLotService.park("KA-01-HH-3141", "Black");

        status.setTicketService(ticketService);
        // assertTrue(status.execute());
    }

}
