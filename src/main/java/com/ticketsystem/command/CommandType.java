package com.ticketsystem.command;

/**
 * Enum contains {@link Command} type and details.
 * Created by rohitkumar on 04/02/17.
 */
public enum CommandType {

    CREATE_PARKING_LOT("create_parking_lot", CreateParkingLot.class, 2),
    PARK("park", Park.class, 3),
    LEAVE("leave", Leave.class, 2),
    STATUS("status", Status.class, 1),
    REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR("registration_numbers_for_cars_with_colour", RegistrationNumbersForCarsWithColor.class, 1),
    SLOT_NUMBERS_FOR_CARS_WITH_COLOUR("slot_numbers_for_cars_with_colour", SlotNumbersForCarsWithColour.class, 1),
    SLOT_NUMBER_FOR_REGISTRATION_NUMBER("slot_number_for_registration_number", SlotNumberForRegistrationNumber.class, 1);


    private String name;
    private Class classType;
    private Integer paramLimit;

    CommandType(String name, Class classType, Integer paramLimit){
        this.name = name;
        this.classType = classType;
        this.paramLimit = paramLimit;
    }

    public Command getCommand(CommandType commandType) {

        Command command = null;
        try {
            command = (Command)commandType.getClassType().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return command;
    }

    public static CommandType getCommandType(String command) {

        CommandType commandType = null;

        for (CommandType commandTypeValue : CommandType.values()) {
            if (command.equalsIgnoreCase(commandTypeValue.getName())) {
                commandType = commandTypeValue;
                break;
            }
        }
        return commandType;
    }

    public String getName() {
        return name;
    }

    public Class getClassType() {
        return classType;
    }

    public Integer getParamLimit() {
        return paramLimit;
    }
}
