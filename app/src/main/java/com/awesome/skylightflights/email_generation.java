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
import android.widget.TextView;

import com.awesome.skylightflights.MainHelpers.GMailSender;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class email_generation extends AppCompatActivity {

    private TextView review;
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
        review = findViewById(R.id.see_details);

        Intent intent_prev = getIntent();
        final Passenger passenger = (Passenger) intent_prev.getSerializableExtra("passenger");

        String data = "See Details Of Booking\n\nName : ";
        int length = passenger.getName().length();
        if(length<14)
        {
            data += passenger.getName();
            for(int i=length;i<13;i++)
                data +=" ";
        }
        else
        {
            data += passenger.getName().substring(0,13);
        }
        data += "\nAge    : " + passenger.getAge() + " years";
        data += "\nGender  : " + passenger.getGender();

        data += "\nFlight     : " + passenger.getFlight();
        data += "\nFrom     : " + passenger.getFrom_place();
        data += "\nTo          : " + passenger.getTo_place();
        data += "\nDate      : " + passenger.getDate_day()
                +"." + passenger.getDate_month()
                +"." + passenger.getDate_year();
        data += "\nDep. time     : " + passenger.getDep_time();
        data += "\nLand. time   : " + passenger.getLan_time();
        review.setText(data);

        data += "\nFlight Class : " + passenger.getFlight_class();
        data += "\nPrice : Rs " + passenger.getPrice();
        data += "\nSeat  : " + passenger.getSeat();
        data += "\nDuration     : " + passenger.getDuration() + " min";
        data += "\nBooking Id : " + passenger.getBooking_Id();
        review.setText(data);

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
                    GMailSender sender = new GMailSender(""/* Enter email id in "" */ , "" /*Enter password in "" */);
                    // TODO Enter you email ID and password here
                    // e.g. if email id is "skylights.airlines@gmail.com" and password is "password"
                    // then enter it as :- GMailSender sender = new GMailSender("skylights.airlines@gmail.com", "password");
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

                    flightRef = db.collection(passenger.getBooking_Id()).document("email");

                    flightRef.set(map);

                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("my log", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }

}
//REMOVE NAVIGATION BAR - Extra Feature that can be added