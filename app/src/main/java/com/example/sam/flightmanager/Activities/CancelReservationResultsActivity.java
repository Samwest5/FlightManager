// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// displays results and cancels reservations
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
import com.example.sam.flightmanager.Objects.ReservationItem;
import com.example.sam.flightmanager.R;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.HashSet;

public class CancelReservationResultsActivity extends AppCompatActivity {

    dbSystem mdbSystem;
    EditText etNumber;
    TextView tvReservations;
    Button btnCancel;
    int reservationNumber;
    String username;

    public void showAlertDialogConfirmCancellation(final int reservationNumber, String username) {
        int resNumber = reservationNumber;
        final String user = username;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notification");
        builder.setMessage("Cancel Reservation?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mainMenu = new Intent(CancelReservationResultsActivity.this, MainMenuActivity.class);
                ReservationItem reservation = mdbSystem.getReservation(user, reservationNumber);
                String time = java.text.DateFormat.getTimeInstance().format(new Date());
                String date = java.text.DateFormat.getDateInstance().format(new Date());
                mdbSystem.addCancellationLog(reservation, time, date);
                mdbSystem.removeReservation(reservationNumber);
                startActivity(mainMenu);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mainMenu = new Intent(CancelReservationResultsActivity.this, MainMenuActivity.class);
                startActivity(mainMenu);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void showAlertDialogNoReservations() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notification");
        builder.setMessage("No Reservations to Cancel");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mainMenu = new Intent(CancelReservationResultsActivity.this, MainMenuActivity.class);
                startActivity(mainMenu);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void showAlertDialogInvalidNumber() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notification");
        builder.setMessage("Invalid Reservation Number Entered");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mainMenu = new Intent(CancelReservationResultsActivity.this, MainMenuActivity.class);
                startActivity(mainMenu);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reservation_results);

        mdbSystem = dbSystem.get(this.getApplicationContext());
        etNumber = findViewById(R.id.crNumberEditText);
        tvReservations = findViewById(R.id.crReservationsTextView);
        tvReservations.setMovementMethod(new ScrollingMovementMethod());
        btnCancel = findViewById(R.id.crCancelButton);
        username = getIntent().getExtras().getString("username");


        boolean valid = mdbSystem.hasReservations(username);

        if (!valid) {
            showAlertDialogNoReservations();
        }
        else {
            tvReservations.setText(mdbSystem.getReservationList(username));
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservationNumber = Integer.parseInt(etNumber.getText().toString());
                HashSet<Integer> numbers = mdbSystem.getReservationNumbers(username);

                if (!numbers.contains(reservationNumber)) {
                    showAlertDialogInvalidNumber();
                }
                else {
                    showAlertDialogConfirmCancellation(reservationNumber, username);
                }
            }
        });
    }
}
