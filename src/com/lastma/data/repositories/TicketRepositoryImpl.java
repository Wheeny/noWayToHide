package com.lastma.data.repositories;

import com.lastma.data.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketRepositoryImpl implements TicketRepository {
    
        private static List<Ticket> tickets = new ArrayList<Ticket>();
        private static int ticketCount = 0;

        @Override
        public Ticket save(Ticket ticket) {
            tickets.add(ticket);
            ticket.setId(++ticketCount);
            return ticket;
        }

        @Override
        public long count() {
            return ticketCount;
        }

        @Override
        public Ticket findById(int id) {
            for(int counter = 0; counter < tickets.size(); counter++) {
                if(tickets.get(counter).getId() == id) {
                    return tickets.get(counter);
                }
            }
            return null;
        }

    @Override
    public List<Ticket> findByVehicleId(int vehicleId) {
        List<Ticket> result = new ArrayList<>();
        for (int count = 0; count < tickets.size(); count++) {
            if (tickets.get(count).getVehicleId() == vehicleId) {
                result.add(tickets.get(count));
            }
        }
        return result;
    }

        @Override
        public List<Ticket> findAll() {

            return tickets;
        }

        @Override
        public void deleteById(int ticketId) {
            for(int counter = 0; counter < tickets.size(); counter++) {
                if(tickets.get(counter).getId() == ticketId) {
                    tickets.remove(counter);
                    ticketCount--;
                    break;
                }
            }

        }

        @Override
        public void deleteAll() {
            tickets.clear();
            ticketCount = 0;
        }

        @Override
        public boolean existsById(int ticketId) {

            if(findById(ticketId).getId() == ticketId) {
                return true;
            }
            return false;
        };
    }


