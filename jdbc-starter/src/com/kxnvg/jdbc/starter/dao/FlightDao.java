package com.kxnvg.jdbc.starter.dao;

import com.kxnvg.jdbc.starter.entity.Flight;
import com.kxnvg.jdbc.starter.entity.Ticket;
import com.kxnvg.jdbc.starter.exception.DaoException;
import com.kxnvg.jdbc.starter.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {

    private static final FlightDao INSTANCE = new FlightDao();
    private static final String FIND_BY_ID_SQL = """
            SELECT id,
                   flight_no,
                   departure_date,
                   departure_airport_code,
                   arrival_date,
                   arrival_airport_code,
                   status,
                   aircraft_id
            FROM flight
            WHERE id = ?       
            """;
    private static final String DELETE_SQL = """
            DELETE FROM ticket
            WHERE id = ?
            """;
    private static final String SAVE_SQL = """
            INSERT INTO flight(flight_no, departure_date, departure_airport_code, arrival_date, arrival_airport_code, aircraft_id, status)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
    private static final String UPDATE_SQL = """
            UPDATE flight
            SET flight_no = ?,
                departure_date = ?,
                departure_airport_code = ?,
                arrival_date = ?,
                arrival_airport_code = ?,
                aircraft_id = ?,
                status = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT id,
                    flight_no,
                    departure_date,
                    departure_airport_code,
                    arrival_date,
                    arrival_airport_code,
                    aircraft_id,
                    status
            FROM flight
            """;

    private FlightDao() {
    }

    public static FlightDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.get();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Flight save(Flight flight) {
        try (Connection connection = ConnectionManager.get();
        PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL,Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, flight.flightNo());
            preparedStatement.setObject(2, flight.departureDate());
            preparedStatement.setString(3, flight.departureAirportCode());
            preparedStatement.setObject(4, flight.arrivalDate());
            preparedStatement.setString(5, flight.arrivalAirportCode());
            preparedStatement.setInt(6, flight.aircraftId());
            preparedStatement.setString(7, flight.status());

            preparedStatement.executeUpdate();
            return flight;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Flight flight) {
        try (Connection connection = ConnectionManager.get();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, flight.flightNo());
            preparedStatement.setObject(2, flight.departureDate());
            preparedStatement.setString(3, flight.departureAirportCode());
            preparedStatement.setObject(4, flight.arrivalDate());
            preparedStatement.setString(5, flight.arrivalAirportCode());
            preparedStatement.setInt(6, flight.aircraftId());
            preparedStatement.setString(7, flight.status());
            preparedStatement.setLong(8, flight.id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Flight> findById(Long id) {
        try (Connection connection = ConnectionManager.get()) {
            return findById(id, connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<Flight> findById(Long id, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            Flight flight = null;
            if (resultSet.next()) {
                flight = new Flight(
                        resultSet.getLong("id"),
                        resultSet.getString("flight_no"),
                        resultSet.getTimestamp("departure_date").toLocalDateTime(),
                        resultSet.getString("departure_airport_code"),
                        resultSet.getTimestamp("arrival_date").toLocalDateTime(),
                        resultSet.getString("arrival_airport_code"),
                        resultSet.getInt("aircraft_id"),
                        resultSet.getString("status")
                );
            }
            return Optional.ofNullable(flight);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Flight> findAll() {
        try (Connection connection = ConnectionManager.get();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Flight> flights = new ArrayList<>();

            while (resultSet.next()) {
                flights.add(buildFlight(resultSet));
            }
            return flights;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Flight buildFlight(ResultSet resultSet) throws SQLException {
        Flight flight = new Flight(
                resultSet.getLong("flight_id"),
                resultSet.getString("flight_no"),
                resultSet.getTimestamp("departure_date").toLocalDateTime(),
                resultSet.getString("departure_airport_code"),
                resultSet.getTimestamp("arrival_date").toLocalDateTime(),
                resultSet.getString("arrival_airport_code"),
                resultSet.getInt("aircraft_id"),
                resultSet.getString("status")
        );
        return flight;
    }
}
