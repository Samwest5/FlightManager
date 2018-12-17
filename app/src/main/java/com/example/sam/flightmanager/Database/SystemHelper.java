// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// implements database as defined by schema and contains methods for interacting with it
package com.example.sam.flightmanager.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sam.flightmanager.Database.SystemSchema.*;
import com.example.sam.flightmanager.Objects.CustomerItem;
import com.example.sam.flightmanager.Objects.FlightItem;
import com.example.sam.flightmanager.Objects.LogItem;
import com.example.sam.flightmanager.Objects.ReservationItem;

import java.lang.reflect.Array;
import java.nio.channels.FileLockInterruptionException;
import java.util.ArrayList;

import static com.example.sam.flightmanager.Database.SystemSchema.CustomersTable.Cols.PASSWORD;
import static com.example.sam.flightmanager.Database.SystemSchema.FlightsTable.Cols.ARRIVAL;
import static com.example.sam.flightmanager.Database.SystemSchema.FlightsTable.Cols.DEPARTURE;
import static com.example.sam.flightmanager.Database.SystemSchema.FlightsTable.Cols.FLIGHTNO;
import static com.example.sam.flightmanager.Database.SystemSchema.ReservationsTable.Cols.RESERVATIONNO;
import static com.example.sam.flightmanager.Database.SystemSchema.ReservationsTable.Cols.USERNAME;


public class SystemHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    public static final String DATABASE_NAME = "system.db";

    private SQLiteDatabase db;

    public SystemHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable;

        createTable = "CREATE TABLE " + CustomersTable.NAME
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CustomersTable.Cols.USERNAME + ","
                + PASSWORD + ")";
        db.execSQL(createTable);

        createTable = "CREATE TABLE " + FlightsTable.NAME
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FlightsTable.Cols.FLIGHTNO + ","
                + DEPARTURE + ","
                + FlightsTable.Cols.ARRIVAL + ","
                + FlightsTable.Cols.DEPARTURETIME + ","
                + FlightsTable.Cols.CAPACITY + ","
                + FlightsTable.Cols.REMAINING + ","
                + FlightsTable.Cols.PRICE + ")";
        db.execSQL(createTable);

        createTable = "CREATE TABLE " + ReservationsTable.NAME
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERNAME + ","
                + ReservationsTable.Cols.FLIGHTNO + ","
                + ReservationsTable.Cols.RESERVATIONNO + ","
                + ReservationsTable.Cols.DEPARTURE + ","
                + ReservationsTable.Cols.ARRIVAL + ","
                + ReservationsTable.Cols.DEPARTURETIME + ","
                + ReservationsTable.Cols.TICKETSNO + ","
                + ReservationsTable.Cols.TOTAL + ")";
        db.execSQL(createTable);

        createTable = "CREATE TABLE " + LogsTable.NAME
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LogsTable.Cols.TYPE + ","
                + LogsTable.Cols.USERNAME + ","
                + LogsTable.Cols.DATE + ","
                + LogsTable.Cols.TIME + ","
                + LogsTable.Cols.FLIGHTNO + ","
                + LogsTable.Cols.DEPARTURE + ","
                + LogsTable.Cols.ARRIVAL + ","
                + LogsTable.Cols.TICKETSNO + ","
                + LogsTable.Cols.RESERVATIONNO + ","
                + LogsTable.Cols.TOTAL + ")";
        db.execSQL(createTable);

        addInitialCustomerItems(db);
        addInitialFlightItems(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void addInitialCustomerItems(SQLiteDatabase currDB) {
        CustomerItem item1 = new CustomerItem();
        CustomerItem item2 = new CustomerItem();
        CustomerItem item3 = new CustomerItem();

        item1.setUsername("alice5");
        item1.setPassword("csumb100");

        item2.setUsername("brian77");
        item2.setPassword("123ABC");

        item3.setUsername("chris21");
        item3.setPassword("CHRIS21");

        addCustomerItem(currDB, item1);
        addCustomerItem(currDB, item2);
        addCustomerItem(currDB, item3);
    }
    private void addInitialFlightItems(SQLiteDatabase currDB) {
        FlightItem item1 = new FlightItem();
        FlightItem item2 = new FlightItem();
        FlightItem item3 = new FlightItem();
        FlightItem item4 = new FlightItem();
        FlightItem item5 = new FlightItem();

        item1.setFlightNumber(101);
        item1.setDeparture("Monterey");
        item1.setArrival("Los Angeles");
        item1.setDepartureTime("10:00AM");
        item1.setFlightCapacity(10);
        item1.setSeatsRemaining(10);
        item1.setPrice(150.00);

        item2.setFlightNumber(102);
        item2.setDeparture("Los Angeles");
        item2.setArrival("Monterey");
        item2.setDepartureTime("1:00PM");
        item2.setFlightCapacity(10);
        item2.setSeatsRemaining(10);
        item2.setPrice(150.00);

        item3.setFlightNumber(201);
        item3.setDeparture("Monterey");
        item3.setArrival("Seattle");
        item3.setDepartureTime("11:00AM");
        item3.setFlightCapacity(5);
        item3.setSeatsRemaining(5);
        item3.setPrice(200.50);

        item4.setFlightNumber(205);
        item4.setDeparture("Monterey");
        item4.setArrival("Seattle");
        item4.setDepartureTime("3:00PM");
        item4.setFlightCapacity(15);
        item4.setSeatsRemaining(15);
        item4.setPrice(150.00);

        item5.setFlightNumber(202);
        item5.setDeparture("Seattle");
        item5.setArrival("Monterey");
        item5.setDepartureTime("2:00PM");
        item5.setFlightCapacity(5);
        item5.setSeatsRemaining(5);
        item5.setPrice(200.50);

        addFlightItem(currDB, item1);
        addFlightItem(currDB, item2);
        addFlightItem(currDB, item3);
        addFlightItem(currDB, item4);
        addFlightItem(currDB, item5);
    }
    private void addCustomerItem(SQLiteDatabase db, CustomerItem item) {
        ContentValues cv = getCustomerContentValues(item);
        db.insert(CustomersTable.NAME, null, cv);
    }
    private void addFlightItem(SQLiteDatabase db, FlightItem item) {
        ContentValues cv = getFlightContentValues(item);
        db.insert(FlightsTable.NAME, null, cv);
    }

    // Customer Table Calls
    public void addCustomerItem(CustomerItem item) {
        ContentValues cv = getCustomerContentValues(item);
        db = this.getWritableDatabase();
        db.insert(CustomersTable.NAME, null, cv);
    }
    private static ContentValues getCustomerContentValues(CustomerItem item) {
        ContentValues cv = new ContentValues();
        cv.put(CustomersTable.Cols.USERNAME, item.getUsername());
        cv.put(PASSWORD, item.getPassword());
        return cv;
    }
    public boolean customerExists(String username){
        String whereClause = CustomersTable.Cols.USERNAME + " = ? ";
        String[] whereArgs = {username};

        CustomerCursorWrapper cursor = new CustomerCursorWrapper(this.queryCustomers(whereClause, whereArgs));
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        else {
            cursor.close();
            return true;
        }
    }
    public boolean isValidLogin(String username, String password) {
        String whereClause = USERNAME + "=?" + " and " + PASSWORD + "=?";
        String[] whereArgs = {username, password};

        CustomerCursorWrapper cursor = new CustomerCursorWrapper(this.queryCustomers(whereClause, whereArgs));
        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        }
        else {
            cursor.close();
            return true;
        }
//        String whereClause = CustomersTable.Cols.USERNAME + " = ? ";
//        String[] whereArgs = {username};
//
//        CustomerCursorWrapper cursor = new CustomerCursorWrapper(this.queryCustomers(whereClause, whereArgs));
//        if (cursor.getCount() == 0){
//            cursor.close();
//            return false;
//        }
//        else {
//            cursor.moveToFirst();
//            CustomerItem item = cursor.getCustomerItem();
//            if (item.getPassword() == password && item.getUsername() == username) {
//                cursor.close();
//                return true;
//            }
//            else {
//                cursor.close();
//                return false;
//            }
//        }
    }

    // Flight Table Calls
    public void addFlightItem(FlightItem item) {
        ContentValues cv = getFlightContentValues(item);
        db = this.getWritableDatabase();
        db.insert(FlightsTable.NAME, null, cv);
    }
    private static ContentValues getFlightContentValues(FlightItem item) {
        ContentValues cv = new ContentValues();
        cv.put(FlightsTable.Cols.FLIGHTNO, item.getFlightNumber());
        cv.put(FlightsTable.Cols.DEPARTURE, item.getDeparture());
        cv.put(FlightsTable.Cols.ARRIVAL, item.getArrival());
        cv.put(FlightsTable.Cols.DEPARTURETIME, item.getDepartureTime());
        cv.put(FlightsTable.Cols.CAPACITY, item.getFlightCapacity());
        cv.put(FlightsTable.Cols.REMAINING, item.getSeatsRemaining());
        cv.put(FlightsTable.Cols.PRICE, item.getPrice());
        return cv;
    }
    public FlightItem getFlight(int flightNumber) {
        ArrayList<FlightItem> flights = getAllFlights();
        for (FlightItem item : flights) {
            if (item.getFlightNumber() == flightNumber) {
                return item;
            }
        }
        return null;
    }
    public boolean flightExists(int flightNumber) {
        ArrayList<FlightItem> flights = getAllFlights();
        for (FlightItem item : flights) {
            if (item.getFlightNumber() == flightNumber) {
                return true;
            }
        }
        return false;
    }

    // TODO FIGURE OUT WHY QUERY ALWAYS RETURNS TRUE. ABOVE FUNCTION WORKS FOR NOW
//    public boolean flightExists(int flightNumber) {
//        String whereClause = FlightsTable.Cols.FLIGHTNO + " = ? ";
//        String[] whereArgs = {Integer.toString(flightNumber)};
//
//        FlightCursorWrapper cursor = new FlightCursorWrapper(this.queryFlights(whereClause, whereArgs));
//        if (cursor.getCount() <= 0) {
//            cursor.close();
//            return false;
//        }
//        else {
//            cursor.close();
//            return true;
//        }
//    }
    public ArrayList<FlightItem> getMatchingFlights(String departure, String arrival, int ticketsNumber) {
        ArrayList<FlightItem> flights = new ArrayList<>();
        String whereClause = DEPARTURE + "=?" + " and " + ARRIVAL + "=?";
        String[] whereArgs = {departure, arrival};

        FlightCursorWrapper cursor = new FlightCursorWrapper(this.queryFlights(whereClause, whereArgs));
        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        else {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                FlightItem item = cursor.getFlightItem();
                if (item.getSeatsRemaining() >= ticketsNumber) {
                    flights.add(cursor.getFlightItem());
                }
                cursor.moveToNext();
            }
            cursor.close();
        }
        if (flights.isEmpty()) {
            return null;
        }
        return flights;
    }
    public ArrayList<FlightItem> getAllFlights() {
        ArrayList<FlightItem> flights = new ArrayList<>();
        FlightCursorWrapper cursor = new FlightCursorWrapper(this.queryFlights(null, null));
        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        else {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                FlightItem item = cursor.getFlightItem();
                flights.add(item);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return flights;
    }
    private void updateSeatsRemaining(int flightNumber, int ticketsNumber) {
        // We call this function after confirm flight exists and has enough seats
        String whereClause = FLIGHTNO + " = ? ";
        String[] whereArgs = {Integer.toString(flightNumber)};
        FlightCursorWrapper cursor = new FlightCursorWrapper(this.queryFlights(whereClause, whereArgs));
        cursor.moveToFirst();
        FlightItem item = cursor.getFlightItem();
        int seatsRemaining = item.getSeatsRemaining();
        item.setSeatsRemaining(seatsRemaining - ticketsNumber);
        ContentValues cv = getFlightContentValues(item);
        db.update(FlightsTable.NAME, cv, whereClause, whereArgs);
    }

    // Reservation Table Calls
    public void addReservationItem(ReservationItem item) {
        ContentValues cv = getReservationContentValues(item);
        db = this.getWritableDatabase();
        db.insert(ReservationsTable.NAME, null, cv);
//        updateSeatsRemaining(item.getFlightNumber(), item.getTicketsNumber());
    }
    private static ContentValues getReservationContentValues(ReservationItem item) {
        ContentValues cv = new ContentValues();
        cv.put(ReservationsTable.Cols.USERNAME, item.getUsername());
        cv.put(ReservationsTable.Cols.FLIGHTNO, item.getFlightNumber());
        cv.put(ReservationsTable.Cols.RESERVATIONNO, item.getReservationNumber());
        cv.put(ReservationsTable.Cols.DEPARTURE, item.getDeparture());
        cv.put(ReservationsTable.Cols.ARRIVAL, item.getDeparture());
        cv.put(ReservationsTable.Cols.DEPARTURETIME, item.getDepartureTime());
        cv.put(ReservationsTable.Cols.TICKETSNO, item.getTicketsNumber());
        cv.put(ReservationsTable.Cols.TOTAL, item.getTotalPrice());
        return cv;
    }
    public void removeReservationItem(int number) {
        String whereClause = RESERVATIONNO + "=?";
        String[] whereArgs = {Integer.toString(number)};
        String table = ReservationsTable.NAME;
        db.delete(table, whereClause , whereArgs);
    }
    public boolean reservationExists(int number) {
        String whereClause = ReservationsTable.Cols.FLIGHTNO + " = ? ";
        String[] whereArgs = {Integer.toString(number)};
        ReservationCursorWrapper cursor = new ReservationCursorWrapper(this.queryReservations(whereClause, whereArgs));
        if (cursor.getCount() != 0) {
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }
//    public boolean reservationExistsWithUsername(int number, String username) {
//        ArrayList<ReservationItem> reservations = getReservationList(username);
//        for (ReservationItem item : reservations) {
//            if (item.getReservationNumber() == number) {
//                return true;
//            }
//        }
//        return false;
//    }
    public ArrayList<ReservationItem> getReservationList(String username) {
        ArrayList<ReservationItem> reservations = new ArrayList<>();
        String whereClause = USERNAME + " = ? ";
        String[] whereArgs = {username};

        ReservationCursorWrapper cursor = new ReservationCursorWrapper(this.queryReservations(whereClause, whereArgs));
        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        else {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                reservations.add(cursor.getReservationItem());
                cursor.moveToNext();
            }
            cursor.close();
        }
        if (reservations.isEmpty()) {
            return null;
        }
        return reservations;
    }
    public ReservationItem getReservationItem(int number, String username) {
        ArrayList<ReservationItem> reservations = getReservationList(username);
        for (ReservationItem item : reservations) {
            if (item.getReservationNumber() == number) {
                return item;
            }
        }
        return null;
    }

    // Log Table Calls
    public ArrayList<LogItem> getLogs() {
        ArrayList<LogItem> logs = new ArrayList<>();
        LogCursorWrapper cursor = new LogCursorWrapper(this.queryLogs(null, null));

        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        else {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                logs.add(cursor.getLogitem());
                cursor.moveToNext();
            }
            cursor.close();
        }
        if (logs.isEmpty()) {
            return null;
        }
        return logs;
    }
    public void addLog(LogItem item) {
        ContentValues cv = getLogContentValues(item);
        db = this.getWritableDatabase();
        db.insert(LogsTable.NAME, null, cv);
    }
    private static ContentValues getLogContentValues(LogItem item) {
        ContentValues cv = new ContentValues();
        cv.put(LogsTable.Cols.TYPE, item.getType());
        cv.put(LogsTable.Cols.USERNAME, item.getUsername());
        cv.put(LogsTable.Cols.DATE, item.getDate());
        cv.put(LogsTable.Cols.TIME, item.getTime());
        cv.put(LogsTable.Cols.FLIGHTNO, item.getFlightNumber());
        cv.put(LogsTable.Cols.DEPARTURE, item.getDeparture());
        cv.put(LogsTable.Cols.ARRIVAL, item.getArrival());
        cv.put(LogsTable.Cols.TICKETSNO, item.getTicketNumber());
        cv.put(LogsTable.Cols.RESERVATIONNO, item.getReservationNumber());
        cv.put(LogsTable.Cols.TOTAL, item.getTotalPrice());
        return cv;
    }

    // Query for each table
    private Cursor queryCustomers(String whereClause, String[] whereArgs) {
        db = this.getWritableDatabase();
        return db.query(CustomersTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
    }
    private Cursor queryReservations(String whereClause, String[] whereArgs) {
        db = this.getWritableDatabase();
        return db.query(ReservationsTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
    }
    private Cursor queryFlights(String whereClause, String[] whereArgs) {
        db = this.getWritableDatabase();
        return db.query(FlightsTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
    }
    private Cursor queryLogs(String whereClause, String[] whereArgs) {
        db = this.getWritableDatabase();
        return db.query(LogsTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
    }

}


