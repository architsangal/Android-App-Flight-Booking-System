package com.awesome.skylightflights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Get_Flight_Ticket extends AppCompatActivity {

    private EditText bookingId,name;
    private Button go;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference bookingRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get__flight__ticket);

        bookingId = findViewById(R.id.g_booking_id);
        name = findViewById(R.id.g_name);
        go = findViewById(R.id.g_go);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    int booking_id = Integer.parseInt(bookingId.getText().toString().trim());
                    String g_name = name.getText().toString().trim();
                    bookingRef = db.collection(""+booking_id).document(g_name);
                    bookingRef.get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    Passenger passenger = new Passenger();
                                    passenger.setName(documentSnapshot.getString("name"));
                                    passenger.setFlight_class(documentSnapshot.getString("Flight_class"));
                                    passenger.setName(documentSnapshot.getString("name"));
                                    passenger.setBooking_Id(documentSnapshot.getString("bookingId"));
                                    passenger.setAge(Integer.parseInt(documentSnapshot.getString("age")));
                                    passenger.setDep_time(documentSnapshot.getString("dep_time"));
                                    passenger.setDuration(documentSnapshot.getString("duration"));
                                    passenger.setFlight(documentSnapshot.getString("flight"));
                                    passenger.setFrom_place(documentSnapshot.getString("from"));
                                    passenger.setTo_place(documentSnapshot.getString("to"));
                                    passenger.setGender(documentSnapshot.getString("gender"));
                                    passenger.setLan_time(documentSnapshot.getString("land_time"));
                                    passenger.setDate_month(Integer.parseInt(documentSnapshot.getLong("month").toString()));
                                    passenger.setDate_day(Integer.parseInt(documentSnapshot.getLong("day").toString()));
                                    passenger.setDate_year(Integer.parseInt(documentSnapshot.getLong("year").toString()));
                                    passenger.setSeat(Integer.parseInt(documentSnapshot.getString("seat")));
                                    passenger.setPrice(documentSnapshot.getString("price"));
                                    Intent intent = new Intent(Get_Flight_Ticket.this,Show_Ticket.class);
                                    intent.putExtra("passenger",passenger);
                                    intent.putExtra("status",documentSnapshot.getString("status"));
                                    startActivity(intent);
                                }
                            });
                }
                catch(Exception e)
                {
                    Toast.makeText(Get_Flight_Ticket.this,"Enter Text Correctly",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
