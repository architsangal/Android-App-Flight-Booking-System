package com.awesome.skylightflights;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Delete_Flight_Ticket extends AppCompatActivity {

    private EditText name,booking_id;
    private Button delete_ticket;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference bookingRef;
    DocumentReference emailRef;
    DocumentReference bankRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__flight__ticket);

        name = findViewById(R.id.name_delete);
        booking_id = findViewById(R.id.g_booking_id);

        delete_ticket = findViewById(R.id.delete_ticket);

        delete_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
                try
                {
                    id = Integer.parseInt(booking_id.getText().toString().trim());
                    bookingRef = db.collection(booking_id.getText().toString().trim()).document(name.getText().toString().trim());
                    emailRef = db.collection(booking_id.getText().toString().trim()).document("email");
                    bankRef = db.collection("Bank").document("bank");
                    final Passenger passenger = new Passenger();
                    bookingRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                final DocumentSnapshot document = task.getResult();
                                if (document.exists())
                                {
                                    if(document.getString("status").equals("Cancelled"))
                                        Toast.makeText(Delete_Flight_Ticket.this,"Ticket Already Cancelled",Toast.LENGTH_LONG).show();
                                    else
                                    {
                                        final Bank bank = new Bank();
                                        bank.setNo2(document.getString("price"));
                                        Map<String, Object> map = new HashMap<>();
                                        map.put("status", "Cancelled");
                                        bookingRef.update(map)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(Delete_Flight_Ticket.this, "Ticket Successfully Cancelled.", Toast.LENGTH_LONG).show();
                                      //                  Intent intent = new Intent(Delete_Flight_Ticket.this, Menu.class);
                                        //                startActivity(intent);
                                                    }
                                                });
                                        bankRef.get()
                                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                        bank.setNo1(documentSnapshot.getString("amount"));
                                                        String new_amount = bank.sub();
                                                        Map<String, Object> map1 = new HashMap<>();
                                                        map1.put("amount", new_amount);
                                                        bankRef.update(map1);
                                                    }
                                                });
                                        bookingRef.get()
                                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                        String date = ""+documentSnapshot.getLong("day") + "." + documentSnapshot.getLong("month") + "." + documentSnapshot.getLong("year");
                                                        String flight = "" + documentSnapshot.getString("flight");
                                                        String seat = document.getString("seat");
                                                        Map<String,Object> map2 = new HashMap<>();
                                                        map2.put(seat,"0");
                                                        db.collection(date).document(flight).update(map2);
                                                    }
                                                });
                                        final email emails = new email();
                                        bookingRef.get()
                                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
                                                {
                                                    @Override
                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                        emails.setBody("Dear " + documentSnapshot.getString("name") + ",\n" + "Your flight ticket for flight number :- "
                                                                + documentSnapshot.getString("flight") + "\n Booking Id : " + documentSnapshot.getString("bookingId")
                                                                + "\n From : " + documentSnapshot.getString("from") + "\n To   : "
                                                                + documentSnapshot.getString("to") + "\n Seat : " + documentSnapshot.getString("seat") + "\non " + documentSnapshot.getLong("day")
                                                                + "-" + documentSnapshot.getLong("month") + "-" + documentSnapshot.getLong("year") + " at "
                                                                + documentSnapshot.getString("dep_time").substring(0, 2) + ":"
                                                                + documentSnapshot.getString("dep_time").substring(2) + " has been successfully cancelled. " +
                                                                "Your balance will be credited back to your account within 5 working days." +
                                                                "\nThanks for using Skylights Flights App.\n\nRegards,\nSkylight Airlines.");
                                                        emailRef.get()
                                                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                    @Override
                                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                        emails.setEmail(documentSnapshot.getString("email"));
                                                                        Log.d("check",emails.getBody() + emails.getEmail());
                                                                        Intent intent = new Intent(Delete_Flight_Ticket.this,review_details.class);
                                                                        intent.putExtra("emails",emails);
                                                                        startActivity(intent);
                                                                    }
                                                                });

                                                    }
                                                });
                                    }
                                }
                                else
                                {
                                    Toast.makeText(Delete_Flight_Ticket.this,"No Such Booking Exist",Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Log.d("Failed Deleting Booking", "Failed with: ", task.getException());
                            }
                        }
                    });

                }
                catch(Exception e)
                {
                    Toast.makeText(Delete_Flight_Ticket.this,"Enter Data Correctly",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
