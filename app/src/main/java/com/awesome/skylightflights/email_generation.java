package com.awesome.skylightflights;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.awesome.skylightflights.MainHelpers.GMailSender;

public class email_generation extends AppCompatActivity {

    private Button go;
    private EditText email_address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_generation);
        go = findViewById(R.id.go);
        email_address = findViewById(R.id.email_address);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
                Intent intent = new Intent(email_generation.this,Menu.class);
                startActivity(intent);
            }
        });
    }

    private void sendMessage() {
        final ProgressDialog dialog = new ProgressDialog(email_generation.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {//TODO remove password and email address before uploading
                    Intent intent_prev = getIntent();
                    final Passenger passenger = (Passenger) intent_prev.getSerializableExtra("passenger");
                    GMailSender sender = new GMailSender("skylights.airlines@gmail.com", "QwerAsdfZxcv");
                    String body = "Dear " + passenger.getName();
                    sender.sendMail("Skylights Flights Welcome You To Sky",body,"skylights.airlines@gmail.com", email_address.getText().toString().trim().toLowerCase());
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }

}
