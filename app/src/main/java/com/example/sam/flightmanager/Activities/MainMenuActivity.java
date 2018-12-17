// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// displays main menu
package com.example.sam.flightmanager.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sam.flightmanager.Database.dbSystem;
import com.example.sam.flightmanager.R;

public class MainMenuActivity extends AppCompatActivity {

    dbSystem mdbSystem;
    Button btnCreateAccount, btnReserveSeats, btnCancelReservation, btnManageSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mdbSystem = dbSystem.get(this.getApplicationContext());

        btnCreateAccount = (Button) findViewById(R.id.createAccButton);
        btnReserveSeats = (Button) findViewById(R.id.rSeatButton);
        btnCancelReservation = (Button) findViewById(R.id.cReservationButton);
        btnManageSystem = (Button) findViewById(R.id.mSystemButton);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createAccount = new Intent(MainMenuActivity.this, CreateAccountActivity.class);
                startActivity(createAccount);
            }
        });

        btnReserveSeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reserveSeats  = new Intent(MainMenuActivity.this, ReserveSeatsActivity.class);
                startActivity(reserveSeats);
            }
        });

        btnCancelReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelReservation = new Intent(MainMenuActivity.this, CancelReservationActivity.class);
                startActivity(cancelReservation);
            }
        });

        btnManageSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent manageSystem = new Intent(MainMenuActivity.this, ManageSystemActivity.class);
                startActivity(manageSystem);
            }
        });
    }

}
