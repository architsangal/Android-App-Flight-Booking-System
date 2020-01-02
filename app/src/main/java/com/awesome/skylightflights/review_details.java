package com.awesome.skylightflights;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.awesome.skylightflights.MainHelpers.GMailSender;

import java.util.HashMap;
import java.util.Map;

public class review_details extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_details);

        sendMessage();

        Intent intent = new Intent(review_details.this,Menu.class);
        startActivity(intent);

    }

    private void sendMessage() {
        final ProgressDialog dialog = new ProgressDialog(review_details.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    //TODO remove password and email address before uploading

                    Intent intent_prev = getIntent();
                    final email emails = (email) intent_prev.getSerializableExtra("emails");
                    GMailSender sender = new GMailSender("", "");

                    sender.sendMail("Ticket Cancelled",emails.getBody(),"skylights.airlines@gmail.com",
                            emails.getEmail());
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("my log", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }
}
