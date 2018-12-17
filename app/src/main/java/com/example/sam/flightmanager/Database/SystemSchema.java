// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// provides structure of database
package com.example.sam.flightmanager.Database;


public class SystemSchema {

    public static final class CustomersTable {
        public static final String NAME = "CUSTOMERS";

        public static final class Cols{
            public static final String ID       = "id";
            public static final String USERNAME = "username";
            public static final String PASSWORD = "password";
        }
    }

    public static final class FlightsTable {
        public static final String NAME = "FLIGHTS";

        public static final class Cols {
            public static final String ID               = "id";
            public static final String FLIGHTNO         = "flightNo";
            public static final String DEPARTURE        = "departure";
            public static final String ARRIVAL          = "arrival";
            public static final String DEPARTURETIME    = "departureTime";
            public static final String CAPACITY         = "capacity";
            public static final String REMAINING        = "remaining";
            public static final String PRICE            = "price";

        }
    }

    public static final class ReservationsTable {
        public static final String NAME = "RESERVATIONS";

        public static final class Cols {
            public static final String ID               = "id";
            public static final String USERNAME         = "username";
            public static final String FLIGHTNO         = "flightNo";
            public static final String RESERVATIONNO    = "reservationNo";
            public static final String DEPARTURE        = "departure";
            public static final String ARRIVAL          = "arrival";
            public static final String DEPARTURETIME     = "departureTime";
            public static final String TICKETSNO        = "ticketsNo";
            public static final String TOTAL            = "total";
        }
    }

    public static final class LogsTable {
        public static final String NAME = "LOGS";

        public static final class Cols {
            public static final String ID               = "id";
            public static final String TYPE             = "type";
            public static final String USERNAME         = "username";
            public static final String DATE             = "date";
            public static final String TIME             = "time";
            public static final String FLIGHTNO         = "flightNo";
            public static final String DEPARTURE        = "departure";
            public static final String ARRIVAL          = "arrival";
            public static final String TICKETSNO        = "ticketsNo";
            public static final String RESERVATIONNO    = "reservationNo";
            public static final String TOTAL            = "total";
        }
    }

}