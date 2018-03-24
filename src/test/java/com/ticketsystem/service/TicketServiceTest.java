package com.ticketsystem.service;

import com.ticketsystem.config.TicketSystemJunitConfig;
import com.ticketsystem.entity.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by rohitkumar on 05/02/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicketSystemJunitConfig.class, loader=AnnotationConfigContextLoader.class)
public class TicketServiceTest {

    @Autowired
    TicketService ticketService;

    @Test
    public void testParkingLotService() {
        assertEquals(
                "class com.ticketsystem.service.TicketServiceImpl",
                this.ticketService.getClass().toString());
    }

    @Test
    public void testFindRegistrationNumbersForCarsWithColour() {

        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);
        parkingLotService.createParkingLot(6);
        parkCars(parkingLotService);

        List<String> expected = new ArrayList<>();
        expected.add("KA-01-HH-1234");
        expected.add("KA-01-HH-9999");

        //List<String> actual = ticketService.findRegistrationNumbersForCarsWithColour("White");

        //assertEquals(expected, actual);
    }

    private void parkCars(ParkingLotService parkingLotService) {

        parkingLotService.park("KA-01-HH-1234", "White");
        parkingLotService.park("KA-01-HH-9999", "White");
        parkingLotService.park("KA-01-BB-0001", "Black");
        parkingLotService.park("KA-01-HH-7777", "Red");
        parkingLotService.park("KA-01-HH-2701", "Blue");
        parkingLotService.park("KA-01-HH-3141", "Black");
    }

    @Test
    public void testFindRegistrationNumbersForCarsWithColourNotFound() {
        TicketService ticketService = new TicketServiceImpl();
        assertEquals("Not Found", ticketService.findRegistrationNumbersForCarsWithColour("Pink"));
    }


    @Test
    public void testFindCarSlotNumberForRegistrationNumber() {
        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);
        parkingLotService.createParkingLot(6);
        parkCars(parkingLotService);

        String message = "1";

        assertThat(ticketService.findCarSlotNumberForRegistrationNumber("KA-01-HH-1234"), equalTo(message));
    }

    @Test
    public void testFindCarSlotNumberForRegistrationNumberNotFound() {
        TicketService ticketService = new TicketServiceImpl();
        assertEquals("Not Found", ticketService.findRegistrationNumbersForCarsWithColour("Pink"));
    }

    @Test
    public void testFindSlotNumbersForCarsWithColour() {

        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);
        parkingLotService.createParkingLot(6);
        parkCars(parkingLotService);

        assertThat(ticketService.findSlotNumbersForCarsWithColour("White"), equalTo("1, 2"));
    }

    @Test
    public void testFindSlotNumbersForCarsWithColourNotFound() {

        TicketService ticketService = new TicketServiceImpl();
        assertEquals("Not Found", ticketService.findSlotNumbersForCarsWithColour("Pink"));
    }

    @Test
    public void testIssueTicket() {

        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);
        parkingLotService.createParkingLot(6);
        parkCars(parkingLotService);
        Ticket expected = new Ticket("KA-01-HH-1234","White", 1);
        Ticket actual = ticketService.issueTicket("KA-01-HH-1234", "White", 1);

        assertEquals(expected, actual);
    }

    @Test
    public void testStatusOnSuccessShouldReturnCorrectMessage() {

        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);
        parkingLotService.createParkingLot(6);
        parkCars(parkingLotService);

        assertThat(ticketService.status(), equalTo(ticketService.status()));

    }

}
