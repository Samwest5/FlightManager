// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// wrapper for flight cursor
package com.example.sam.flightmanager.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.sam.flightmanager.Objects.FlightItem;

public class FlightCursorWrapper extends CursorWrapper {
    public FlightCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public FlightItem getFlightItem() {

//        int id = getInt(getColumnIndex(SystemSchema.FlightsTable.Cols.ID));
        int flightNumber = getInt(getColumnIndex(SystemSchema.FlightsTable.Cols.FLIGHTNO));
        String departure = getString(getColumnIndex(SystemSchema.FlightsTable.Cols.DEPARTURE));
        String arrival = getString(getColumnIndex(SystemSchema.FlightsTable.Cols.ARRIVAL));
        String departureTime = getString(getColumnIndex(SystemSchema.FlightsTable.Cols.DEPARTURETIME));
        int flightCapacity = getInt(getColumnIndex(SystemSchema.FlightsTable.Cols.CAPACITY));
        int seatsRemaining = getInt(getColumnIndex(SystemSchema.FlightsTable.Cols.REMAINING));
        double price = getDouble(getColumnIndex(SystemSchema.FlightsTable.Cols.PRICE));

        FlightItem item = new FlightItem();

//        item.setID(id);
        item.setFlightNumber(flightNumber);
        item.setDeparture(departure);
        item.setArrival(arrival);
        item.setDepartureTime(departureTime);
        item.setFlightCapacity(flightCapacity);
        item.setSeatsRemaining(seatsRemaining);
        item.setPrice(price);

        return item;
    }
}
