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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by rohitkumar on 04/02/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicketSystemJunitConfig.class, loader=AnnotationConfigContextLoader.class)
public class RegistrationNumbersForCarsWithColorTest {

    @Autowired
    RegistrationNumbersForCarsWithColor registrationNumbersForCarsWithColor;

    @Test
    public void testRegistrationNumbersForCarsWithColour() {
        assertEquals(
                "class com.ticketsystem.command.RegistrationNumbersForCarsWithColor",
                this.registrationNumbersForCarsWithColor.getClass().toString());
    }

    @Test
    public void testExecuteParkingLotNotCreated() {

        TicketService ticketService = new TicketServiceImpl();
        RegistrationNumbersForCarsWithColor registrationNumbersForCarsWithColor = new RegistrationNumbersForCarsWithColor();
        registrationNumbersForCarsWithColor.setTicketService(ticketService);

        String message = "Not Found";
        assertThat(registrationNumbersForCarsWithColor.execute(), equalTo(message));
    }

    @Test
    public void testExecuteTicketServiceNotInitialized() {

        RegistrationNumbersForCarsWithColor registrationNumbersForCarsWithColor = new RegistrationNumbersForCarsWithColor();
        String message = "TicketService is not available";

        assertThat(registrationNumbersForCarsWithColor.execute(), equalTo(message));

    }

    @Test
    public void testExecuteSuccess() {

        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);
        RegistrationNumbersForCarsWithColor registrationNumbersForCarsWithColor = new RegistrationNumbersForCarsWithColor();

        parkingLotService.createParkingLot(6);
        parkingLotService.park("KA-01-HH-1234", "White");
        parkingLotService.park("KA-01-HH-9999", "White");
        parkingLotService.park("KA-01-BB-0001", "Black");
        parkingLotService.park("KA-01-HH-7777", "Res");
        parkingLotService.park("KA-01-HH-2701", "Blue");
        parkingLotService.park("KA-01-HH-3141", "Black");

        registrationNumbersForCarsWithColor.setTicketService(ticketService);
        registrationNumbersForCarsWithColor.setColour("White");

        List<String> expected = new ArrayList<>();
        expected.add("KA-01-HH-1234");
        expected.add("KA-01-HH-9999");

        StringBuilder tempMessage = new StringBuilder();
        expected.forEach(registerNumber -> tempMessage.append(registerNumber).append(",").append(" "));

        String message = tempMessage.toString().substring(0, tempMessage.length() - 2);
        assertThat( registrationNumbersForCarsWithColor.execute(), equalTo(message));

    }
}
