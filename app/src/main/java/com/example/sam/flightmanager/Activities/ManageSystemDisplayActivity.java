// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// display logs activity for manage system
package com.example.sam.flightmanager.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sam.flightmanager.Database.dbSystem;
import com.example.sam.flightmanager.R;

public class ManageSystemDisplayActivity extends AppCompatActivity {

    dbSystem mdbSystem;
    Button btnConfirm;
    TextView tvLogs;

    public void showAlertDialogButtonClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notification");
        builder.setMessage("Continue to add a flight?");
        builder.setPositiveButton("Back to Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mainMenu = new Intent(ManageSystemDisplayActivity.this, MainMenuActivity.class);
                startActivity(mainMenu);
            }
        });
        builder.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent addFlight = new Intent(ManageSystemDisplayActivity.this, ManageSystemAddFlightActivity.class);
                        startActivity(addFlight);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system_display);

        mdbSystem = dbSystem.get(this.getApplicationContext());
        btnConfirm = (Button) findViewById(R.id.rsGoToLoginButton);
        tvLogs = (TextView) findViewById(R.id.mLogsTextView);
        tvLogs.setMovementMethod(new ScrollingMovementMethod());

        tvLogs.setText(mdbSystem.getLogs());

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogButtonClicked();
            }
        });
    }
}
