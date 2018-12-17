// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// wrapper for log cursor
package com.example.sam.flightmanager.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.sam.flightmanager.Objects.LogItem;

public class LogCursorWrapper extends CursorWrapper {
    public LogCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public LogItem getLogitem() {

//        int id = getInt(getColumnIndex(SystemSchema.LogsTable.Cols.ID));
        int type = getInt(getColumnIndex(SystemSchema.LogsTable.Cols.TYPE));
        String username = getString(getColumnIndex(SystemSchema.LogsTable.Cols.USERNAME));
        String date = getString(getColumnIndex(SystemSchema.LogsTable.Cols.DATE));
        String time = getString(getColumnIndex(SystemSchema.LogsTable.Cols.TIME));
        int flightNumber = getInt(getColumnIndex(SystemSchema.LogsTable.Cols.FLIGHTNO));
        String departure = getString(getColumnIndex(SystemSchema.LogsTable.Cols.DEPARTURE));
        String arrival = getString(getColumnIndex(SystemSchema.LogsTable.Cols.ARRIVAL));
        int ticketsNumber = getInt(getColumnIndex(SystemSchema.LogsTable.Cols.TICKETSNO));
        int reservationNumber = getInt(getColumnIndex(SystemSchema.LogsTable.Cols.RESERVATIONNO));
        double totalPrice = getDouble(getColumnIndex(SystemSchema.LogsTable.Cols.TOTAL));

        LogItem item = new LogItem();

//        item.setID(id);
        item.setType(type);
        item.setUsername(username);
        item.setDate(date);
        item.setTime(time);
        item.setFlightNumber(flightNumber);
        item.setDeparture(departure);
        item.setArrival(arrival);
        item.setTicketNumber(ticketsNumber);
        item.setReservationNumber(reservationNumber);
        item.setTotalPrice(totalPrice);

        return item;
    }
}
