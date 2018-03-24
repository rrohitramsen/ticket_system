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

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by rohitkumar on 04/02/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicketSystemJunitConfig.class, loader=AnnotationConfigContextLoader.class)
public class SlotNumbersForCarsWithColourTest {

    @Autowired
    SlotNumbersForCarsWithColour slotNumbersForCarsWithColour ;

    @Test
    public void testSlotNumbersForCarsWithColour() {
        assertEquals(
                "class com.ticketsystem.command.SlotNumbersForCarsWithColour",
                this.slotNumbersForCarsWithColour.getClass().toString());
    }

    @Test
    public void testExecuteParkingLotNotCreated() {

        TicketService ticketService = new TicketServiceImpl();
        SlotNumbersForCarsWithColour slotNumbersForCarsWithColour = new SlotNumbersForCarsWithColour();
        slotNumbersForCarsWithColour.setTicketService(ticketService);

        String message = "Not Found";
        assertEquals(message, slotNumbersForCarsWithColour.execute());
    }

    @Test
    public void testExecuteTicketServiceNotInitialized() {

        SlotNumbersForCarsWithColour slotNumbersForCarsWithColour = new SlotNumbersForCarsWithColour();
        String message = "TicketService is not available";
        assertEquals(message, slotNumbersForCarsWithColour.execute());
    }

    @Test
    public void testExecuteSuccess() {

        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);
        SlotNumbersForCarsWithColour slotNumbersForCarsWithColour = new SlotNumbersForCarsWithColour();

        parkingLotService.createParkingLot(6);
        parkingLotService.park("KA-01-HH-1234", "White");
        parkingLotService.park("KA-01-HH-9999", "White");
        parkingLotService.park("KA-01-BB-0001", "Black");
        parkingLotService.park("KA-01-HH-7777", "Res");
        parkingLotService.park("KA-01-HH-2701", "Blue");
        parkingLotService.park("KA-01-HH-3141", "Black");

        slotNumbersForCarsWithColour.setTicketService(ticketService);
        slotNumbersForCarsWithColour.setColour("White");

       String message = "1, 2";
        assertThat(slotNumbersForCarsWithColour.execute(), equalTo(message));

    }
}
