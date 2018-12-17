// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// wrapper for reservation cursor
package com.example.sam.flightmanager.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.sam.flightmanager.Objects.ReservationItem;

public class ReservationCursorWrapper extends CursorWrapper {
    public ReservationCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public ReservationItem getReservationItem() {
//        int id = getInt(getColumnIndex(SystemSchema.ReservationsTable.Cols.ID));
        String username = getString(getColumnIndex(SystemSchema.ReservationsTable.Cols.USERNAME));
        int flightNumber = getInt(getColumnIndex(SystemSchema.ReservationsTable.Cols.FLIGHTNO));
        int reservationNumber = getInt(getColumnIndex(SystemSchema.ReservationsTable.Cols.RESERVATIONNO));
        String departure = getString(getColumnIndex(SystemSchema.ReservationsTable.Cols.DEPARTURE));
        String arrival = getString(getColumnIndex(SystemSchema.ReservationsTable.Cols.ARRIVAL));
        String departureTime = getString(getColumnIndex(SystemSchema.ReservationsTable.Cols.DEPARTURETIME));
        int ticketsNumber = getInt(getColumnIndex(SystemSchema.ReservationsTable.Cols.TICKETSNO));
        double totalPrice = getDouble(getColumnIndex(SystemSchema.ReservationsTable.Cols.TOTAL));

        ReservationItem item = new ReservationItem();
//        item.setID(id);
        item.setUsername(username);
        item.setFlightNumber(flightNumber);
        item.setReservationNumber(reservationNumber);
        item.setDeparture(departure);
        item.setArrival(arrival);
        item.setDepartureTime(departureTime);
        item.setTicketsNumber(ticketsNumber);
        item.setTotalPrice(totalPrice);

        return item;
    }
}
