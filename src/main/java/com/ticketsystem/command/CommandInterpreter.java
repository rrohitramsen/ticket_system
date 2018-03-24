package com.ticketsystem.command;

import com.ticketsystem.service.ParkingLotService;
import com.ticketsystem.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * This class interpret the command on the basis of command line arguments / file lines / provided string.
 * Created by rohitkumar on 04/02/17.
 */
@Component
public class CommandInterpreter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandInterpreter.class);

    private ParkingLotService parkingLotService;
    private TicketService ticketService;

    @Autowired
    public CommandInterpreter(ParkingLotService parkingLotService, TicketService ticketService) {
        this.parkingLotService = parkingLotService;
        this.ticketService = ticketService;
    }

    /**
     * Interpet the {@link Command} on the basis of given command line.
     * @param commandLine
     */
    public void interpretCommand(String commandLine) {

        String [] commandParams = commandLine.split(" ");
        CommandType commandType = CommandType.getCommandType(commandParams[0]);

        if (commandType == null) {
            LOGGER.info("Command " + commandParams[0] + " not supported.");
            return;
        }

        if (commandParams.length < commandType.getParamLimit()) {
            LOGGER.error("INVALID ARGUMENTS :: Minimum Argument Limit [ "+commandType.getParamLimit()+" ]for Command - "+commandType.getName());
            return;
        }

        Map<String, Object> params = new HashMap<>();

        switch (commandType) {

            case CREATE_PARKING_LOT:
                params.put("parkingLotSize", parseToInt(commandType, commandParams[1]));
                params.put("parkingLotService", parkingLotService);
                break;

            case PARK:
                params.put("registrationNumber", commandParams[1]);
                params.put("colour", commandParams[2]);
                params.put("parkingLotService", parkingLotService);
                break;

            case LEAVE:
                params.put("slotNumber", parseToInt(commandType, commandParams[1]));
                params.put("parkingLotService", parkingLotService);
                break;

            case REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
                params.put("colour", commandParams[1]);
                params.put("ticketService", ticketService);
                break;

            case SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
                params.put("registrationNumber", commandParams[1]);
                params.put("ticketService", ticketService);
                break;

            case SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
                params.put("colour", commandParams[1]);
                params.put("ticketService", ticketService);
                break;

            case STATUS:
                params.put("ticketService", ticketService);
                break;

        }

        Command command = CommandFactory.newCommand(commandType, params);
        command.execute();

    }

    private int parseToInt(CommandType commandType, String commandParam) {
        try {
            return Integer.parseInt(commandParam);
        }catch (NumberFormatException e) {
            LOGGER.error("Invalid Input - "+commandParam+" -for Command : "+commandType+". Exception - "+e);
        }
        return 6;
    }

}
