// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// initial reservation activity
package com.example.sam.flightmanager.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sam.flightmanager.Database.dbSystem;
import com.example.sam.flightmanager.R;

public class ReserveSeatsActivity extends AppCompatActivity {

    Button btnCheckFlights;
    EditText etDeparture;
    EditText etArrival;
    EditText etTickets;
    String departure;
    String arrival;
    int numTickets;

    dbSystem mdbSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seats);

        mdbSystem = dbSystem.get(this.getApplicationContext());
        btnCheckFlights = (Button) findViewById(R.id.rsFlightsButton);
        etDeparture = (EditText) findViewById(R.id.rsDeparturePlainText);
        etArrival = (EditText) findViewById(R.id.rsArrivalPlainText);
        etTickets = (EditText) findViewById(R.id.rsNumTicketsPlainText);


        btnCheckFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                departure = etDeparture.getText().toString();
                arrival = etArrival.getText().toString();
                numTickets = Integer.parseInt(etTickets.getText().toString());

                Intent reservationOptions = new Intent(ReserveSeatsActivity.this, ReserveSeatResultsActivity.class);
                reservationOptions.putExtra("departure", departure);
                reservationOptions.putExtra("arrival", arrival);
                reservationOptions.putExtra("numTickets", numTickets);
                startActivity(reservationOptions);
            }
        });
    }
}
