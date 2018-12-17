// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// login page for manage system
package com.example.sam.flightmanager.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sam.flightmanager.Objects.Admins;
import com.example.sam.flightmanager.R;

public class ManageSystemActivity extends AppCompatActivity {

    Button btnConfirm;
    EditText etUsername, etPassword;
    String username, password;
    Admins admin;

    public void showAlertDialogButtonClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notification");
        builder.setMessage("Unable to log in");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mainMenu = new Intent(ManageSystemActivity.this, MainMenuActivity.class);
                startActivity(mainMenu);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system);

        btnConfirm = (Button) findViewById(R.id.rsGoToLoginButton);
        etUsername = (EditText) findViewById(R.id.mUsernameEditText);
        etPassword = (EditText) findViewById(R.id.mPasswordEditText);
        admin = new Admins();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                boolean valid = Admins.checkLogin(username, password);
                if (valid) {
                    Intent log = new Intent(ManageSystemActivity.this, ManageSystemDisplayActivity.class);
                    startActivity(log);
                }
                else {
                    showAlertDialogButtonClicked();
                }
            }
        });

    }
}


