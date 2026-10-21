package com.lastma.data.repositories;

import com.lastma.data.models.Ticket;

import java.util.List;

public interface TicketRepository {

        Ticket save(Ticket ticket);

        long count();

        Ticket findById(int id);

        List<Ticket> findByVehicleId(int vehicleId);

        List<Ticket> findAll();

        void deleteById(int ticketId);

        void deleteAll();

        boolean existsById(int ticketId);

    }



