// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// initial activity for canceling reservations

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
import com.example.sam.flightmanager.R;

public class CancelReservationActivity extends AppCompatActivity {

    dbSystem system;
    Button btnLogin;
    EditText etUsername, etPassword;
    String username, password;

    public void showAlertDialogBadLogin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notification");
        builder.setMessage("Unable to log in");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mainMenu = new Intent(CancelReservationActivity.this, MainMenuActivity.class);
                startActivity(mainMenu);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reservation);

        btnLogin = (Button) findViewById(R.id.crLogInButton);
        etUsername = (EditText) findViewById(R.id.crUsernameEditText);
        etPassword = (EditText) findViewById(R.id.crPasswordEditText);

        system = dbSystem.get(this.getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                boolean valid = system.isCustomer(username, password);
                if (valid) {

                    Intent reservationResults = new Intent(CancelReservationActivity.this, CancelReservationResultsActivity.class);
                    reservationResults.putExtra("username", username);
                    startActivity(reservationResults);
                }
                else {
                    showAlertDialogBadLogin();
                }
            }
        });
    }
}
