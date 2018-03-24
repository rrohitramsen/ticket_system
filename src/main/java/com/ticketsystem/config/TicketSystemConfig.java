package com.ticketsystem.config;

import com.ticketsystem.command.CommandInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Project configuration class.
 * Created by rohitkumar on 04/02/17.
 */
@Configuration
@ComponentScan(basePackages = {
        "com.ticketsystem.command",
        "com.ticketsystem.service"
})
public class TicketSystemConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketSystemConfig.class);

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(TicketSystemConfig.class);
        CommandInterpreter commandInterpreter = context.getBean(CommandInterpreter.class);

        if (args.length == 0) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String commandLine = "";
            while (true) {
                try {
                    commandLine = reader.readLine();
                    if (commandLine.equalsIgnoreCase("EXIT")) {
                        break;
                    }
                    commandInterpreter.interpretCommand(commandLine);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {

            List<String> commandLines = new ArrayList<>();
            try(BufferedReader br = Files.newBufferedReader(Paths.get(args[0]))) {
                commandLines = br.lines().collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
            commandLines.forEach(commandInterpreter :: interpretCommand);
        }
    }


}
