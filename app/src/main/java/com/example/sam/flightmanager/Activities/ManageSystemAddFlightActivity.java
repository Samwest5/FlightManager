// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// add flight activity for manage system
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
import com.example.sam.flightmanager.R;

public class ManageSystemAddFlightActivity extends AppCompatActivity {

    public void showAlertDialogButtonClicked(boolean flightExists) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notification");
        if (flightExists) {
            builder.setMessage("Flight with this number already exists in system");
        }
        else {
            builder.setMessage("Flight added!");
        }
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mainMenu = new Intent(ManageSystemAddFlightActivity.this, MainMenuActivity.class);
                startActivity(mainMenu);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    dbSystem mdbSystem;
    EditText etFlightNumber
            ,etDeparture
            ,etArrival
            ,etDepartureTime
            ,etFlightCapacity
            ,etPrice;
    Button btnAddFlight;

    int flightNumber, flightCapacity;
    String departure
            ,arrival
            ,departureTime;
    double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system_add_flight);

        mdbSystem = dbSystem.get(this.getApplicationContext());
        etFlightNumber = findViewById(R.id.mFNEditText);
        etDeparture = findViewById(R.id.mDEditText);
        etArrival = findViewById(R.id.mAEditText);
        etDepartureTime = findViewById(R.id.mDTEditText);
        etFlightCapacity = findViewById(R.id.mFCEditText);
        etPrice = findViewById(R.id.mPEditText);
        btnAddFlight = findViewById(R.id.mAddFlightButton);

        btnAddFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flightNumber = Integer.parseInt(etFlightNumber.getText().toString());
                departure = etDeparture.getText().toString();
                arrival = etArrival.getText().toString();
                departureTime = etDepartureTime.getText().toString();
                flightCapacity = Integer.parseInt(etFlightCapacity.getText().toString());
                price = Double.parseDouble(etPrice.getText().toString());

                boolean flightExists = mdbSystem.flightExists(flightNumber);

                if (!flightExists) {
                    FlightItem item = new FlightItem();
                    item.setFlightNumber(flightNumber);
                    item.setDeparture(departure);
                    item.setArrival(arrival);
                    item.setDepartureTime(departureTime);
                    item.setFlightCapacity(flightCapacity);
                    item.setSeatsRemaining(flightCapacity);
                    item.setPrice(price);
                    mdbSystem.addFlight(item);
                }

                showAlertDialogButtonClicked(flightExists);
            }
        });

    }
}
