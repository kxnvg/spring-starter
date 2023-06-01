package com.kxnvg.jdbc.starter;

import com.kxnvg.jdbc.starter.dao.FlightDao;
import com.kxnvg.jdbc.starter.dao.TicketDao;
import com.kxnvg.jdbc.starter.dto.TicketFilter;
import com.kxnvg.jdbc.starter.entity.Flight;
import com.kxnvg.jdbc.starter.entity.Ticket;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class DaoRunner {
    public static void main(String[] args) {
//


    }

    private static void saveFlightTest() {
        FlightDao flightDao = FlightDao.getInstance();
        Flight flight = new Flight(111L, "TEST123", LocalDateTime.now(), "MSK",
                LocalDateTime.now(), "LDN", 2, "DEPARTED");
        Flight savedFlight = flightDao.save(flight);
        System.out.println(savedFlight);
    }

    private static void filterTicketTest() {
        TicketFilter ticketFilter = new TicketFilter(10, 0, "Евгений Кудрявцев", "A1");
        List<Ticket> tickets = TicketDao.getInstance().findAll(ticketFilter);
        System.out.println(tickets);
    }

    private static void findAllTicketsTest() {
        List<Ticket> tickets = TicketDao.getInstance().findAll();
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    private static void updateTicketTest() {
        TicketDao ticketDao = TicketDao.getInstance();
        Optional<Ticket> maybeTicket = ticketDao.findById(2L);
        System.out.println(maybeTicket);


        maybeTicket.ifPresent(ticket -> {
            ticket.setCost(BigDecimal.valueOf(188.88));
            ticketDao.update(ticket);
        });
    }

    private static void deleteTicketTest(Long id) {
        TicketDao ticketDao = TicketDao.getInstance();
        boolean deleteResult = ticketDao.delete(id);
        System.out.println(deleteResult);
    }

    private static void saveTicketTest() {
        TicketDao ticketDao = TicketDao.getInstance();
        Ticket ticket = new Ticket();
        ticket.setPassengerNo("1234567");
        ticket.setPassengerName("Test");
        //ticket.setFlight(3L);
        ticket.setSeatNo("B3");
        ticket.setCost(BigDecimal.TEN);
        Ticket savedTicket = ticketDao.save(ticket);
        System.out.println(savedTicket);
    }
}
