package com.ticketsystem.command;

import com.ticketsystem.config.TicketSystemJunitConfig;
import com.ticketsystem.entity.ParkingLot;
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
public class CreateParkingLotTest {

    @Autowired
    CreateParkingLot createParkingLot;

    @Test
    public void testCreateParkingLot() {
        assertEquals(
                "class com.ticketsystem.command.CreateParkingLot",
                this.createParkingLot.getClass().toString());
    }

    @Test
    public void testExecute() {
        TicketService ticketService = new TicketServiceImpl();
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(ticketService);

        ParkingLot expected = ParkingLot.getParkingLotInstance();
        expected.init(6);

        CreateParkingLot createParkingLot = new CreateParkingLot(6);
        createParkingLot.setParkingLotService(parkingLotService);

        String message = "Created a parking lot with "+6+" slots";

        assertThat(createParkingLot.execute(), equalTo(message));
    }

    @Test
    public void testExecuteWithoutService() {
        CreateParkingLot createParkingLot = new CreateParkingLot(6);
        String message = "ParkingLotService is not available.";

        assertThat(createParkingLot.execute(), equalTo(message));
    }
}
