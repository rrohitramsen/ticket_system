package com.ticketsystem.config;

import com.ticketsystem.service.ParkingLotService;
import com.ticketsystem.service.ParkingLotServiceImpl;
import com.ticketsystem.service.TicketService;
import com.ticketsystem.service.TicketServiceImpl;
import com.ticketsystem.command.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by rohitkumar on 04/02/17.
 */
@Configuration
public class TicketSystemJunitConfig {


    @Bean
    TicketService newTicketService() {
        return new TicketServiceImpl();
    }

    @Bean
    ParkingLotService newParkingLotService() {
        return new ParkingLotServiceImpl(newTicketService());
    }

    @Bean
    CreateParkingLot newCreateParkingLot() {
        return new CreateParkingLot();
    }

    @Bean
    Leave newLeave(){
        return new Leave();
    }

    @Bean
    Park newPark() {
        return new Park();
    }

    @Bean
    RegistrationNumbersForCarsWithColor newRegistrationNumbersForCarsWIthColour() {
        return new RegistrationNumbersForCarsWithColor();
    }

    @Bean
    SlotNumberForRegistrationNumber newSlotNumberForRegistrationNumber() {
        return new SlotNumberForRegistrationNumber();
    }

    @Bean
    SlotNumbersForCarsWithColour newSlotNumbersForCarsWithColour() {
        return new SlotNumbersForCarsWithColour();
    }

    @Bean
    Status newStatus() {
        return new Status();
    }

}
