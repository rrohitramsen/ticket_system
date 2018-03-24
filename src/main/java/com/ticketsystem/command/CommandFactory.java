package com.ticketsystem.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Its a command factory, which creates command object on the basis of {@link CommandType} and params.
 * Created by rohitkumar on 04/02/17.
 */
public abstract class CommandFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandFactory.class);

    /**
     *  Factory method for creating {@link Command} instance.
     * @param commandType
     * @param params
     * @return {@link Command}
     */
    public static Command newCommand(CommandType commandType, Map<String, Object> params) {

        Command command = commandType.getCommand(commandType);

        buildCommand(command, params);
        return command;
    }

    private static void buildCommand(Command command, Map<String, Object> params) {

        Class<? extends Command> commandClass = command.getClass();
        for (String fieldName : params.keySet()) {
            try {
                Field f1 = commandClass.getDeclaredField(fieldName);
                f1.setAccessible(true);
                f1.set(command, params.get(fieldName));
            } catch (Exception e) {
                LOGGER.error("Error while building Command and exception is :: " + e);
            }
        }
    }

}
