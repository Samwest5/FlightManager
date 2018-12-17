// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// provides implementation of log object
package com.example.sam.flightmanager.Objects;

public class LogItem {
//    private int ID;
    private int type;
    private String username;
    private String date;
    private String time;
    private int flightNumber;
    private String departure;
    private String arrival;
    private int reservationNumber;
    private int ticketNumber;
    private double totalPrice;

//    public int getID() {
//        return ID;
//    }
//
//    public void setID(int ID) {
//        this.ID = ID;
//    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (type == 0) {
            sb.append("Type: New Account");
        }
        if (type == 1) {
            sb.append("Type: Reservation");
        }
        if (type == 2) {
            sb.append("Type: Cancellation");
        }
        sb.append(" Username: ").append(username);
        sb.append(" Date: ").append(date);
        sb.append(" Time: ").append(time);
        if (flightNumber != -1) {
            sb.append(" Flight Number: ").append(flightNumber);
        }
        if (!departure.equals("")) {
            sb.append(" Departure: ").append(departure);
        }
        if (!arrival.equals("")) {
            sb.append(" Arrival: ").append(arrival);
        }
        if (ticketNumber != -1) {
            sb.append(" Tickers: ").append(ticketNumber);
        }
        if (reservationNumber != -1) {
            sb.append(" Reservation #: ").append(reservationNumber);
        }
        if (totalPrice != -1) {
            sb.append(" Total: $").append(totalPrice);
        }
        sb.append("\n");
        return sb.toString();
    }

}
