// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// provides interface for activities to interact with database
package com.example.sam.flightmanager.Database;

import android.content.Context;

import com.example.sam.flightmanager.Objects.CustomerItem;
import com.example.sam.flightmanager.Objects.FlightItem;
import com.example.sam.flightmanager.Objects.LogItem;
import com.example.sam.flightmanager.Objects.ReservationItem;

import java.util.ArrayList;
import java.util.HashSet;

public class dbSystem {
    private static dbSystem sdbSystem;
    private Context mContext;
    private SystemHelper mSystemHelper;

    public static dbSystem get(Context context) {
        if (sdbSystem == null) {
            sdbSystem = new dbSystem(context);
        }
        return sdbSystem;
    }

    private dbSystem(Context context) {
        mContext = context.getApplicationContext();
        mSystemHelper = new SystemHelper(mContext);
    }

    // Customer Table Calls
    public void addCustomer(CustomerItem item) {
//        if (!customerExists(item.getUsername())){
//            mSystemHelper.addCustomerItem(item);
//            return true;
//        }
//        return false;
        mSystemHelper.addCustomerItem(item);
    }
    public boolean customerExists(String username) {
        return mSystemHelper.customerExists(username);
    }
    public boolean isCustomer(String username, String password) {
        return mSystemHelper.isValidLogin(username, password);
    }

    // Flight Table Calls
    public void addFlight(FlightItem item) {
        mSystemHelper.addFlightItem(item);
    }
    public boolean flightExists(int flightNumber) {
        return mSystemHelper.flightExists(flightNumber);
    }
    public String getMatchingFlightList(String departure, String arrival, int number) {
        StringBuilder sb = new StringBuilder();
        ArrayList<FlightItem> flights = mSystemHelper.getMatchingFlights(departure, arrival, number);
        if (flights == null) {
            return "none";
        }
        sb.append("Matching Flights\n");
        for (FlightItem item : flights) {
            sb.append(item.toString());
        }
        return sb.toString();
    }
    public HashSet<Integer> getMatchingFlightListNumbers(String departure, String arrival, int number) {
        ArrayList<FlightItem> flights = mSystemHelper.getMatchingFlights(departure, arrival, number);
        HashSet<Integer> numbers = new HashSet<>();
        for (FlightItem item : flights) {
            numbers.add(item.getFlightNumber());
        }
        return numbers;
    }
    public FlightItem getFlight(int number) {
        return mSystemHelper.getFlight(number);
    }
    public String getAllFlights() {
        StringBuilder sb = new StringBuilder();
        ArrayList<FlightItem> flights = mSystemHelper.getAllFlights();
        if (flights == null) {
            return "none";
        }
        sb.append("All Flights\n");
        for (FlightItem item : flights) {
            sb.append(item.toString());
        }
        return sb.toString();
    }

    // Reservations Table Calls
    // TODO IMPLEMENT IN SYSTEMHELPER
    public void addReservation(ReservationItem item) {
        mSystemHelper.addReservationItem(item);
    }
    public void removeReservation(int number) {
        mSystemHelper.removeReservationItem(number);
    }
//    public boolean reservationExistsWithUsername(int reservationNumber, String username) {
//        return mSystemHelper.reservationExistsWithUsername(reservationNumber, username);
//    }
    public boolean reservationExists(int reservationNumber) {
        return mSystemHelper.reservationExists(reservationNumber);
    }
    public String getReservationList(String username) {
        StringBuilder sb = new StringBuilder();
        ArrayList<ReservationItem> reservations = mSystemHelper.getReservationList(username);
        if (reservations == null) {
            return "none";
        }
        sb.append("Reservations\n");
        for (ReservationItem item : reservations) {
            sb.append(item.toString());
        }
        return sb.toString();
    }
    public boolean hasReservations(String username) {
        return (mSystemHelper.getReservationList(username) != null);
    }
    public HashSet<Integer> getReservationNumbers(String username) {
        ArrayList<ReservationItem> reservations = mSystemHelper.getReservationList(username);
        HashSet<Integer> numbers = new HashSet<>();
        for (ReservationItem item : reservations) {
            numbers.add(item.getReservationNumber());
        }
        return numbers;
    }
    public ReservationItem getReservation(String username, int number) {
        return mSystemHelper.getReservationItem(number, username);
    }

    // Log Table Calls
    // TODO IMPLEMENT IN SYSTEMHELPER
    public String getLogs() {
        StringBuilder sb = new StringBuilder();
        ArrayList<LogItem> logs = mSystemHelper.getLogs();
        if (logs == null) {
            return "none";
        }
        sb.append("Logs\n");
        for (LogItem item : logs) {
            sb.append(item.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
    public void addNewAccountLog(CustomerItem customer, String date, String time) {
        LogItem item = new LogItem();
//        item.setID(-1);
        item.setType(0);
        item.setUsername(customer.getUsername());
        item.setDate(date);
        item.setTime(time);
        item.setFlightNumber(-1);
        item.setDeparture("");
        item.setArrival("");
        item.setTicketNumber(-1);
        item.setReservationNumber(-1);
        item.setTotalPrice(-1);
        mSystemHelper.addLog(item);
    }
    public void addReservationLog(ReservationItem reservation, String date, String time) {
        LogItem item = new LogItem();
//        item.setID(-1);
        item.setType(1);
        item.setUsername(reservation.getUsername());
        item.setDate(date);
        item.setTime(time);
        item.setFlightNumber(reservation.getFlightNumber());
        item.setDeparture(reservation.getDeparture());
        item.setArrival(reservation.getArrival());
        item.setTicketNumber(reservation.getTicketsNumber());
        item.setReservationNumber(reservation.getReservationNumber());
        item.setTotalPrice(reservation.getTotalPrice());
        mSystemHelper.addLog(item);
    }
    public void addCancellationLog(ReservationItem reservation, String date, String time) {
        LogItem item = new LogItem();
//        item.setID(-1);
        item.setType(2);
        item.setUsername(reservation.getUsername());
        item.setDate(date);
        item.setTime(time);
        item.setFlightNumber(reservation.getFlightNumber());
        item.setDeparture(reservation.getDeparture());
        item.setArrival(reservation.getArrival());
        item.setTicketNumber(reservation.getTicketsNumber());
        item.setReservationNumber(reservation.getReservationNumber());
        item.setTotalPrice(-1);
        mSystemHelper.addLog(item);
    }

}