package com.awesome.skylightflights;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.awesome.skylightflights.MainHelpers.GMailSender;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class deleting_flight_process extends AppCompatActivity {

    private FirebaseFirestore db =FirebaseFirestore.getInstance();
    private DocumentReference bookingRef;
    private DocumentReference bankRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleting_flight_process);

        final Intent intent_prev = getIntent();
        /*int c=0;
        for(int i=1;i<=20;i++)
        {
            if(!intent_prev.getStringExtra(i+"").equals("0"))
            {
               // bookingId[c] = intent_prev.getStringExtra(i+"");
                c++;
            }
        }
        for(int i=0;i<c;i++)
        {
            bookingRef = db.collection(bookingId[i]).document("email");
            bookingRef.get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            sendMessage(documentSnapshot.getString("email"),intent_prev.getStringExtra("date"));
                        }
                    });
        }*/




    }
    private void sendMessage(final String email,final String date)
    {
        final ProgressDialog dialog = new ProgressDialog(deleting_flight_process.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    //TODO remove password and email address before uploading

                    GMailSender sender = new GMailSender("" /* Enter email id in "" */ , "" /*Enter password in "" */ );
                    // TODO Enter you email ID and password here
                    // e.g. if email id is "skylights.airlines@gmail.com" and password is "password"
                    // then enter it as :- GMailSender sender = new GMailSender("skylights.airlines@gmail.com", "password");
                    String body = "Dear Customer," +
                            "\nYour flight has been canceled due to bad weather conditions in that region. " +
                            "We sincerely regret this inconvenience and assure you that we will refund the money immediately." +
                            "\nThanking you for your continued cooperation and we hope we continue to be your favorite airline for the next year." +
                            "\nregards," +
                            "\nSkylights Airlines" +
                            "\nDate Of Flight: " + date;

                    sender.sendMail("Skylights Flights Welcome You To Sky",body,"skylights.airlines@gmail.com",email);
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("my log", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }
}
