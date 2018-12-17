// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// displays reservations for customer
package com.example.sam.flightmanager.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.sam.flightmanager.Database.dbSystem;
import com.example.sam.flightmanager.Objects.FlightItem;
import com.example.sam.flightmanager.R;

import java.util.HashSet;

public class ReserveSeatResultsActivity extends AppCompatActivity {

    TextView etReservations;
    EditText etFlightNumber;
    Button btnLogin;
    dbSystem mdbSystem;

    int flightNumber;
    String departure;
    String arrival;
    int numTickets;

    public void showAlertDialogButtonClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notification");
        builder.setMessage("No Matching Flights. Try different query");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mainMenu = new Intent(ReserveSeatResultsActivity.this, ReserveSeatsActivity.class);
                startActivity(mainMenu);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void showAlertDialogButtonClicked2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notification");
        builder.setMessage("Flight Number not valid");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mainMenu = new Intent(ReserveSeatResultsActivity.this, MainMenuActivity.class);
                startActivity(mainMenu);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seat_results);

        mdbSystem = dbSystem.get(this.getApplicationContext());
        etReservations = (TextView) findViewById(R.id.rsReservationsTextView);
        etReservations.setMovementMethod(new ScrollingMovementMethod());
        etFlightNumber = (EditText) findViewById(R.id.rsFlightNumEditText);
        btnLogin = (Button) findViewById(R.id.rsGoToLoginButton);

        departure = getIntent().getExtras().getString("departure");
        arrival = getIntent().getExtras().getString("arrival");
        numTickets = getIntent().getExtras().getInt("numTickets");

        String flights = mdbSystem.getMatchingFlightList(departure, arrival, numTickets);

        if (flights.equals("none")) {
            showAlertDialogButtonClicked();
        }
        else {
            etReservations.setText(flights);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flightNumber = Integer.parseInt(etFlightNumber.getText().toString());
                HashSet<Integer> flightNumbers = mdbSystem.getMatchingFlightListNumbers(departure, arrival, numTickets);

                if (!flightNumbers.contains(flightNumber)) {
                    showAlertDialogButtonClicked2();
                }
                else {
                    Intent toLogin = new Intent(ReserveSeatResultsActivity.this, ReserveSeatsLoginActivity.class);
                    toLogin.putExtra("flight", flightNumber);
                    toLogin.putExtra("numtickets", numTickets);
                    startActivity(toLogin);
                }

            }
        });

    }
}
