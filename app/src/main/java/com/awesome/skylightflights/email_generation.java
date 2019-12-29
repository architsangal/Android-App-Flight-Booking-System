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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class email_generation extends AppCompatActivity {

    private Button go;
    private EditText email_address;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference flightRef;

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
                try
                {
                    //TODO remove password and email address before uploading

                    Intent intent_prev = getIntent();
                    final Passenger passenger = (Passenger) intent_prev.getSerializableExtra("passenger");
                    GMailSender sender = new GMailSender("skylights.airlines@gmail.com", "QwerAsdfZxcv");
                    String body = "Dear " + passenger.getName()+",\n" + "Your flight ticket for flight number :- "
                            + passenger.getFlight() + "\n Booking Id : " + passenger.getBooking_Id() + "\n From : " +passenger.getFrom_place() + "\n To   : "
                            + passenger.getTo_place()+ "\n Seat : "+ passenger.getSeat() +"\non "+passenger.getDate_day()
                            +"-"+passenger.getDate_month() +"-"+passenger.getDate_year()+ " at "
                            + passenger.getDep_time().substring(0,2)+":"
                            +passenger.getDep_time().substring(2)+" has been confirmed. Please report to " +
                            "check-in atleast 2 hours before the time of departure. We wish you a pleasant flight." +
                            "\nThanks for using Skylights Flights.\n\nRegards,\nSkylight Airlines.";

                    sender.sendMail("Skylights Flights Welcome You To Sky",body,"skylights.airlines@gmail.com",
                            email_address.getText().toString().trim().toLowerCase());
                    passenger.setEmail(email_address.getText().toString().trim().toLowerCase());

                    Map<String,Object> map = new HashMap<>();
                    map.put("email",passenger.getEmail());

                    flightRef = db.collection(passenger.getBooking_Id()).document(passenger.getName());

                    flightRef.update(map);

                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("my log", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }

}
// TODO REMOVE NAVIGATION BAR