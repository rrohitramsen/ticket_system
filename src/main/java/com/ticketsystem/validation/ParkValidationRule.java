package com.ticketsystem.validation;

import com.ticketsystem.command.CommandType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * Created by rohitkumar on 11/02/17.
 *
 * park KA-01-HH-1234 White
 */
public class ParkValidationRule implements ValidationRule<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParkValidationRule.class);

    private static final String COMMAND_PATTERN = "park+\\s+[a-zA-Z]+-\\d+-[a-zA-Z]+-\\d+\\s+[a-zA-Z]";

    private final Pattern pattern = Pattern.compile(COMMAND_PATTERN);
    @Override
    public void validate(String data) {

        if ( !pattern.matcher(data).matches()) {
            LOGGER.error(CommandType.PARK.getName()+" is not a valid command");
        }
    }

    public static void main(String[] args) {

        ParkValidationRule parkValidationRule = new ParkValidationRule();
        parkValidationRule.validate("park KA-01-HH-1234 White");
    }
}
