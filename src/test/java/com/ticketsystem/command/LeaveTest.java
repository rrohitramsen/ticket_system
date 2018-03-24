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

import static org.junit.Assert.assertEquals;

/**
 * Created by rohitkumar on 04/02/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicketSystemJunitConfig.class, loader=AnnotationConfigContextLoader.class)
public class LeaveTest {

    @Autowired
    Leave leave;

    @Test
    public void testLeave() {
        assertEquals(
                "class com.ticketsystem.command.Leave",
                this.leave.getClass().toString());
    }

    @Test
    public void testExecuteParkingLotNotCreated() {
        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);
        Leave leave = new Leave();

        leave.setParkingLotService(parkingLotService);

        //assertFalse(leave.execute());
    }

    @Test
    public void testExecuteParkingLotServiceNotInitialized() {
        Leave leave = new Leave();

        //assertFalse(leave.execute());
    }

    @Test
    public void testExecuteParkingLotEmpty() {
        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);
        Leave leave = new Leave();

        parkingLotService.createParkingLot(6);
        leave.setParkingLotService(parkingLotService);

        //assertFalse(leave.execute());
    }

    @Test
    public void testExecuteSuccess() {

        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);

        parkingLotService.createParkingLot(6);
        parkingLotService.park("KA-01-HH-1234", "White");
        parkingLotService.park("KA-01-HH-9999", "White");
        parkingLotService.park("KA-01-BB-0001", "Black");
        parkingLotService.park("KA-01-HH-7777", "Res");
        parkingLotService.park("KA-01-HH-2701", "Blue");
        parkingLotService.park("KA-01-HH-3141", "Black");

        Leave leave = new Leave(4);
        leave.setParkingLotService(parkingLotService);

        // assertTrue(leave.execute());
    }

    @Test
    public void testExecuteUnParkTwice() {

        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);

        parkingLotService.createParkingLot(6);
        parkingLotService.park("KA-01-HH-1234", "White");
        parkingLotService.park("KA-01-HH-9999", "White");
        parkingLotService.park("KA-01-BB-0001", "Black");
        parkingLotService.park("KA-01-HH-7777", "Res");
        parkingLotService.park("KA-01-HH-2701", "Blue");
        parkingLotService.park("KA-01-HH-3141", "Black");

        Leave leave = new Leave(4);
        leave.setParkingLotService(parkingLotService);
        leave.execute();

        leave.setSlotNumber(4);
        // assertFalse(leave.execute());
    }
}
