package com.awesome.skylightflights;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class payment_portal extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference flightRef;
    private DocumentReference passengerRef;
    private DocumentReference bank;
    private Button enter_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_portal);
        Intent intent_prev = getIntent();
        final Passenger passenger = (Passenger) intent_prev.getSerializableExtra("passenger");

        enter_email = findViewById(R.id.enter_email);
        String Date_sel = passenger.getDate_day()
                +"." + passenger.getDate_month()
                +"." + passenger.getDate_year();

        flightRef = db.collection(Date_sel).document(passenger.getFlight());
        passenger.setBooking_Id(genBookingId()+"");

        Map<String,Object> map = new HashMap<>();
        map.put(""+passenger.getSeat(),passenger.getBooking_Id());
        flightRef.update(map);

        Map<String,Object> pass_info = new HashMap<>();
        pass_info.put("price",passenger.getPrice());
        pass_info.put("from",passenger.getFrom_place());
        pass_info.put("to",passenger.getTo_place());
        pass_info.put("flight",passenger.getFlight());
        pass_info.put("bookingId",passenger.getBooking_Id()+"");
        pass_info.put("day",passenger.getDate_day());
        pass_info.put("month",passenger.getDate_month());
        pass_info.put("year",passenger.getDate_year());
        pass_info.put("dep_time",passenger.getDep_time());
        pass_info.put("land_time",passenger.getLan_time());
        pass_info.put("name",passenger.getName());
        pass_info.put("Flight_class",passenger.getFlight_class());
        pass_info.put("seat",passenger.getSeat()+"");
        pass_info.put("age",passenger.getAge()+"");
        pass_info.put("gender",passenger.getGender());
        pass_info.put("duration",passenger.getDuration());
        pass_info.put("status","Booked");

        passengerRef = db.collection(passenger.getBooking_Id()+"")
                .document(passenger.getName());

        passengerRef.set(pass_info);

        bank = db.collection("Bank").document("bank");
        bank.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Bank Add = new Bank();
                        Map<String,Object> mapAmount = new HashMap<>();
                        mapAmount.put("amount",Add.add(documentSnapshot
                                .getString("amount"),passenger.getPrice()));
                        bank.update(mapAmount);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
        enter_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(payment_portal.this,email_generation.class);
                intent.putExtra("passenger", passenger);
                startActivity(intent);
            }
        });
    }
    public static long genBookingId()
    {
        int n = 10000000;
        int m = 99999999;
        return ((long)((Math.random()*(n-m)))+m);
    }
}
