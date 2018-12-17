// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// provides implementation of flight object
package com.example.sam.flightmanager.Objects;

public class FlightItem {
//    private int ID;
    private int flightNumber;
    private String departure;
    private String arrival;
    private String departureTime;
    private int flightCapacity;
    private int seatsRemaining;
    private double price;
//
//    public int getID() {
//        return ID;
//    }
//
//    public void setID(int ID) {
//        this.ID = ID;
//    }

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

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public int getFlightCapacity() {
        return flightCapacity;
    }

    public void setFlightCapacity(int flightCapacity) {
        this.flightCapacity = flightCapacity;
    }

    public int getSeatsRemaining() {
        return seatsRemaining;
    }

    public void setSeatsRemaining(int seatsRemaining) {
        this.seatsRemaining = seatsRemaining;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" Flight Number: ").append(flightNumber);
        sb.append(" Departure: ").append(departure);
        sb.append(" Arrival: ").append(arrival);
        sb.append( "Departure Time: ").append(departureTime);
        sb.append(" Seats Remaining: ").append(seatsRemaining);
        sb.append(" Price: $").append(price);
        sb.append("\n");
        return sb.toString();
    }

}
