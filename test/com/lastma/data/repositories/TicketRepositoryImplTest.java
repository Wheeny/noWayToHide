package com.lastma.data.repositories;

import com.lastma.data.models.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryImplTest {

        private TicketRepositoryImpl ticketRepository;
        private Ticket ticket;

        @BeforeEach
        public void setUp() {
            ticketRepository = new TicketRepositoryImpl();
            ticket = new Ticket();
        }


        @Test
        public void testThatICreateAndSaveATicket_CountIncreases() {
            ticketRepository.save(ticket);
            assertEquals(1, ticketRepository.count());

        }

        @Test
        public void testThatICreateAndSave2Tickets_CountIncreases() {
            ticketRepository.save(ticket);
            Ticket ticket2 = new Ticket();
            ticketRepository.save(ticket2);
            assertEquals(2, ticketRepository.count());

        }

        @Test
        public void testToFindATicketById() {
            ticketRepository.save(ticket);
            Ticket ticket2 = new Ticket();
            ticketRepository.save(ticket2);
            Ticket ticket3 = new Ticket();
            ticketRepository.save(ticket3);

            Ticket foundTicket = ticketRepository.findById(2);
            assertEquals(3, ticketRepository.count());
            assertEquals(2, foundTicket.getId());
        }

        @Test
        public void testToFindNonExistentTickets() {
            ticketRepository.save(ticket);
            Ticket ticket2 = new Ticket();
            ticketRepository.save(ticket2);
            Ticket foundTicket = ticketRepository.findById(3);
            assertEquals(2, ticketRepository.count());
            assertNull(foundTicket);
        }

        @Test
        public void testToFindAllTickets() {
            ticketRepository.save(ticket);
            Ticket ticket2 = new Ticket();
            ticketRepository.save(ticket2);
            Ticket ticket3 = new Ticket();
            ticketRepository.save(ticket3);

            List<Ticket> tickets = ticketRepository.findAll();
            assertEquals(3, tickets.size());

        }

        @Test
        public void testToDeleteTicketsById() {
            ticketRepository.save(ticket);
            Ticket ticket2 = new Ticket();
            ticketRepository.save(ticket2);
            Ticket ticket3 = new Ticket();
            ticketRepository.save(ticket3);

            ticketRepository.deleteById(2);
            assertEquals(2, ticketRepository.count());
        }

        @Test
        public void testToDeleteAllTickets() {
            ticketRepository.save(ticket);
            Ticket ticket2 = new Ticket();
            ticketRepository.save(ticket2);
            Ticket ticket3 = new Ticket();
            ticketRepository.save(ticket3);

            ticketRepository.deleteAll();
            assertEquals(0, ticketRepository.count());
        }

        @Test
        public void testThatATicketExistsById() {
            ticketRepository.save(ticket);
            Ticket ticket2 = new Ticket();
            ticketRepository.save(ticket2);
            Ticket ticket3 = new Ticket();
            ticketRepository.save(ticket3);

            boolean foundTicket = ticketRepository.existsById(2);
            assertTrue(foundTicket);

        }


    }

