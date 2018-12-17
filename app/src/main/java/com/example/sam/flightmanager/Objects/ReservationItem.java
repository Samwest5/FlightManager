// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// provides implementation of reservation object
package com.example.sam.flightmanager.Objects;

public class ReservationItem {
//    private int ID;
    private String username;
    private int flightNumber;
    private int reservationNumber;
    private String departure;
    private String arrival;
    private String departureTime;
    private int ticketsNumber;
    private double totalPrice;

//    public void setID(int ID) {
//        this.ID = ID;
//    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setFlightNumber(int number) {
        this.flightNumber = number;
    }
    public void setReservationNumber(int number) {
        this.reservationNumber = number;
    }
    public void setDeparture(String departure) {
        this.departure = departure;
    }
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
    public void setDepartureTime(String time) {
        this.departureTime = arrival;
    }
    public void setTicketsNumber(int number) {
        this.ticketsNumber = number;
    }
    public void setTotalPrice(double price) {
        this.totalPrice = price;
    }
//    public int getID() {
//        return ID;
//    }
    public String getUsername() {
        return username;
    }
    public int getFlightNumber() {
        return flightNumber;
    }
    public int getReservationNumber() {
        return reservationNumber;
    }
    public String getDeparture() {
        return departure;
    }
    public String getArrival() {
        return arrival;
    }
    public String getDepartureTime() {
        return departureTime;
    }
    public int getTicketsNumber() {
        return ticketsNumber;
    }
    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        sb.append("Username: ").append(username);
        sb.append("Flight Number: ").append(flightNumber);
        sb.append(" Reservation Number: ").append(reservationNumber);
        sb.append(" Departure: ").append(departure);
        sb.append(" Arrival: ").append(arrival);
        sb.append(" Departure Time: ").append(departureTime);
        sb.append(" Tickets: ").append(ticketsNumber);
        sb.append(" Total: $").append(totalPrice);
        sb.append("\n");
        return sb.toString();
    }
}
