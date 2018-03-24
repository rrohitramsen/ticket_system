package com.ticketsystem.entity;

import com.ticketsystem.config.TicketSystemJunitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertNotNull;

/**
 * Created by rohitkumar on 04/02/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TicketSystemJunitConfig.class, loader=AnnotationConfigContextLoader.class)
public class TicketStoreTest {

    @Test
    public void testCreateTicketStore() {
        TicketStore ticketStore = TicketStore.getTicketStoreInstance();
        assertNotNull(ticketStore);
    }

}
