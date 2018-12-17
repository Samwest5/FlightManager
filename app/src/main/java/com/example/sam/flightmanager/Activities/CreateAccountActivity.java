// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// activity to create account
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
import com.example.sam.flightmanager.Objects.CustomerItem;
import com.example.sam.flightmanager.R;

import java.util.Date;


public class CreateAccountActivity extends AppCompatActivity {

    Button btnConfirm;
    EditText etUsername, etPassword;
    String username, password;
    dbSystem mdbSystem;

    public void showAlertDialogButtonClicked(boolean valid) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notification");
        if (valid) {
            builder.setMessage("Account created successfully");
            // add to log here
        }
        else {
            builder.setMessage("Unable to create account");
        }
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mainMenu = new Intent(CreateAccountActivity.this, MainMenuActivity.class);
                startActivity(mainMenu);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public boolean validateDetails(String username, String password) {
        int usernameNumNumbers = 0;
        int usernameNumLetters = 0;
        for (char ch : username.toCharArray()) {
            if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') {
                usernameNumLetters += 1;
            }
            if (ch >= '0' && ch <= '9') {
                usernameNumNumbers += 1;
            }
        }

        int passwordNumLetters = 0;
        int passwordNumNumbers = 0;
        for (char ch : password.toCharArray()) {
            if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') {
                passwordNumLetters += 1;
            }
            if (ch >= '0' && ch <= '9') {
                passwordNumNumbers += 1;
            }
        }

        if (usernameNumNumbers < 1 || passwordNumNumbers < 1) {
            return false;
        }
        if (usernameNumLetters < 3 || passwordNumLetters < 3) {
            return false;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mdbSystem = dbSystem.get(this.getApplicationContext());

        btnConfirm = (Button) findViewById(R.id.caConfirmButton);
        etUsername = (EditText) findViewById(R.id.caUsernameEditText);
        etPassword = (EditText) findViewById(R.id.caPasswordEditText);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                boolean valid = validateDetails(username, password) && !mdbSystem.customerExists(username);

                if (valid) {
                    CustomerItem customer = new CustomerItem();
                    customer.setUsername(username);
                    customer.setPassword(password);
                    mdbSystem.addCustomer(customer);
                    String time = java.text.DateFormat.getTimeInstance().format(new Date());
                    String date = java.text.DateFormat.getDateInstance().format(new Date());
                    mdbSystem.addNewAccountLog(customer, date, time);
                }
                showAlertDialogButtonClicked(valid);
            }
        });

    }
}

