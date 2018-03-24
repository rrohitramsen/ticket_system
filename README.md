Automated Ticket System
==========================

`Ticket System for parking works for the below given commands`
----------------------------------
* `create_parking_lot <numbber>` This command will create the parking lot with the given number of slots.
* `park <car_registration_number> <color>` This command will park the car for the given color and number and allocate the slot from the parking.
* `leave <slot_number>` This command will free the given parking slot.
* `status` This command will display the status of the parking.
* `registration_numbers_for_cars_with_colour <color>` This command will display the registration numbers of the cars with given color.
* `slot_numbers_for_cars_with_colour <color>` This command will display the parking slot number of the cars with the given color.
* `slot_number_for_registration_number <car_registration_number>` This command will display the parking slot number of the cars with the given registration number.

# `Junit Test`
~~~
com.gojek.ticketsystem.command :  JUNIT for all the supported commands.

com.gojek.ticketsystem.config : JUNIT Config File.

com.gojek.ticketsystem.entity: JUNIT for entity classes.

com.gojek.ticketsystem.service : JUNIT for service classes.
~~~

# `How to use`

This is a maven project, can run using below mentioned commands,

~~~
$ mvn test - To execute the Junit tests.
~~~

~~~
$ mvn package - To generate the executable jar --> TicketSystem-0.0.1-SNAPSHOT.jar
~~~
~~~
$ java -jar target/TicketSystem-0.0.1-SNAPSHOT.jar - To execute the application.
~~~
## `How to run File`
~~~
$ java -jar target/TicketSystem-0.0.1-SNAPSHOT.jar file_inputs

~~~


## `How to run Interactive`, Please see the command details as mentioned above..
~~~
$ java -jar target/TicketSystem-0.0.1-SNAPSHOT.jar - User can provide commands at the command line.
$ <command> <apram>
$ park <registration_number>
$ EXIT - Please use exit or EXIT for exiting from the command line.
~~~

# `Queries and Support`
~~~
Email - `rrohit.ramsen@gmail.com`
~~~



