package com.ticketsystem.validation;

import com.ticketsystem.command.Command;

import java.util.regex.Pattern;

/**
 * Created by rohitkumar on 11/02/17.
 */
public class CommandValidation implements ValidationRule<Command> {

    private static final String COMMAND_PATTERN = "";

    private final Pattern pattern = Pattern.compile(COMMAND_PATTERN);

    @Override
    public void validate(Command data) {

    }
}
