// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// login page for reserve seats activity
package com.example.sam.flightmanager.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sam.flightmanager.Database.dbSystem;
import com.example.sam.flightmanager.Objects.FlightItem;
import com.example.sam.flightmanager.Objects.ReservationItem;
import com.example.sam.flightmanager.R;

import java.util.Date;
import java.util.Random;

public class ReserveSeatsLoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    dbSystem mdbSystem;
    int flightNumber, numTickets;
    FlightItem flight;
    String username, password;

    public void showAlertDialogButtonClicked(final ReservationItem reservation) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notification");

        StringBuilder sb = new StringBuilder();
        sb.append("Confirm Reservation\n");
        sb.append("Username: ").append(reservation.getUsername());
        sb.append("\nFlight Number: ").append(reservation.getFlightNumber());
        sb.append("\nDeparture: ").append(reservation.getDeparture());
        sb.append("\nArrival: ") .append(reservation.getArrival());
        sb.append("\nNumber Tickets: ").append(reservation.getTicketsNumber());
        sb.append("\nReservation Number: ").append(reservation.getReservationNumber());
        sb.append("\nTotal: $").append(reservation.getTotalPrice());

        builder.setMessage(sb.toString());
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String time = java.text.DateFormat.getTimeInstance().format(new Date());
                String date = java.text.DateFormat.getDateInstance().format(new Date());
                mdbSystem.addReservation(reservation);
                mdbSystem.addReservationLog(reservation, time, date);
                Intent mainMenu = new Intent(ReserveSeatsLoginActivity.this, MainMenuActivity.class);
                startActivity(mainMenu);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mainMenu = new Intent(ReserveSeatsLoginActivity.this, MainMenuActivity.class);
                startActivity(mainMenu);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showAlertDialogButtonClicked2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notification");
        builder.setMessage("Incorrect Login");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mainMenu = new Intent(ReserveSeatsLoginActivity.this, ReserveSeatsActivity.class);
                startActivity(mainMenu);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seats_login);

        mdbSystem = dbSystem.get(this.getApplicationContext());
        etUsername = findViewById(R.id.rsUsernamePlainText);
        etPassword = findViewById(R.id.rsPasswordPassword);
        btnLogin = findViewById(R.id.rsLoginButton);
        flightNumber = getIntent().getExtras().getInt("flight");
        flight = mdbSystem.getFlight(flightNumber);
        numTickets = getIntent().getExtras().getInt("numtickets");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                boolean result = mdbSystem.isCustomer(username, password);

                if (!result) {
                    showAlertDialogButtonClicked2();
                }
                else {
                    ReservationItem reservation = new ReservationItem();
                    reservation.setUsername(username);
                    reservation.setFlightNumber(flightNumber);
                    reservation.setDeparture(flight.getDeparture());
                    reservation.setArrival(flight.getArrival());
                    reservation.setDepartureTime(flight.getDepartureTime());
                    Random rand = new Random();
                    reservation.setReservationNumber(rand.nextInt(10000001) + 1000);
                    reservation.setTicketsNumber(numTickets);
                    reservation.setTotalPrice(flight.getPrice() * numTickets);
                    showAlertDialogButtonClicked(reservation);
                }
            }
        });

    }
}
