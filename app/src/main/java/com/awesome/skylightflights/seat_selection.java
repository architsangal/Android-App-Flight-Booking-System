package com.awesome.skylightflights;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class seat_selection extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference date;
    private DocumentReference flightRef;
    private TextView seat_display;
    private String Date_sel;
    private EditText seat;
    private Button review;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        Intent intent_prev = getIntent();
        final Passenger passenger = (Passenger) intent_prev.getSerializableExtra("passenger");

        seat_display = findViewById(R.id.seat_display);
        seat = findViewById(R.id.seat_choice);
        review = findViewById(R.id.review);

        final String flight = passenger.getFlight();

        Date_sel = passenger.getDate_day()
                +"." + passenger.getDate_month()
                +"." + passenger.getDate_year();

        date = db.collection(Date_sel);

        flightRef = db.collection(Date_sel).document(flight);

        flightRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot)
                    {
                        String[] seats = new String[21];
                        for(int i=1;i<=20;i++)
                        {
                            if(documentSnapshot.getString(""+i).equals("0"))
                                seats[i]="0";
                            else
                                seats[i]="1";

                        }

                        String seat_data = "";
                        for(int i=1;i<=20;i++ )
                        {
                            if(i%4 == 1)
                            {
                                if(seats[i].equals("0"))
                                    if(i<10)
                                        seat_data = seat_data + "    W [   " + i +" ] ";
                                    else
                                        seat_data = seat_data + "    W [ " + i +" ] ";
                                else
                                    seat_data = seat_data + "    W [   X ] ";
                            }
                            else if(i%4 == 2)
                            {
                                if(seats[i].equals("0"))
                                    if(i<10)
                                        seat_data = seat_data + "[   " + i +" ]    ";
                                    else
                                        seat_data = seat_data + "[ " + i +" ]    ";
                                else
                                    seat_data = seat_data + "[   X ]    ";
                            }
                            else if(i%4 == 3)
                            {
                                if(seats[i].equals("0"))
                                    if(i<10)
                                        seat_data = seat_data + " [   " + i +" ] ";
                                    else
                                        seat_data = seat_data + "[ " + i +" ] ";
                                else
                                    seat_data = seat_data + "[   X ] ";
                            }
                            else
                            {
                                if(seats[i].equals("0"))
                                    if(i<10)
                                        seat_data = seat_data + "[   " + i +" ] W\n";
                                    else
                                        seat_data = seat_data + "[ " + i +" ] W\n";
                                else
                                    seat_data = seat_data + "[   X ] W\n";
                            }
                        }
                        seat_data = "\n\n\n" + seat_data + "\n" + "    Enter The Number Of Seat\n    You Want to book";
                        seat_display.setText(seat_data);
                        review.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try
                                {
                                    passenger.setSeat(Integer.parseInt(seat.getText().toString().trim()));
                                    Intent intent = new Intent(seat_selection.this,payment_portal.class);
                                    intent.putExtra("passenger", passenger);
                                    startActivity(intent);
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(seat_selection.this,"Enter Data Correctly",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent_prev = getIntent();
        final Passenger passenger = (Passenger) intent_prev.getSerializableExtra("passenger");

        seat_display = findViewById(R.id.seat_display);
        seat = findViewById(R.id.seat_choice);
        review = findViewById(R.id.review);

        final String flight = passenger.getFlight();

        Date_sel = passenger.getDate_day()
                +"." + passenger.getDate_month()
                +"." + passenger.getDate_year();

        date = db.collection(Date_sel);

        flightRef = db.collection(Date_sel).document(flight);

        flightRef.addSnapshotListener(this,new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(e == null)
                {
                    flightRef.get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    String[] seats = new String[21];
                                    for (int i = 1; i <= 20; i++) {
                                        if (documentSnapshot.getString("" + i).equals("0"))
                                            seats[i] = "0";
                                        else
                                            seats[i] = "1";

                                    }

                                    String seat_data = "";
                                    for (int i = 1; i <= 20; i++) {
                                        if (i % 4 == 1) {
                                            if (seats[i].equals("0"))
                                                if (i < 10)
                                                    seat_data = seat_data + "    W [   " + i + " ] ";
                                                else
                                                    seat_data = seat_data + "    W [ " + i + " ] ";
                                            else
                                                seat_data = seat_data + "    W [   X ] ";
                                        } else if (i % 4 == 2) {
                                            if (seats[i].equals("0"))
                                                if (i < 10)
                                                    seat_data = seat_data + "[   " + i + " ]    ";
                                                else
                                                    seat_data = seat_data + "[ " + i + " ]    ";
                                            else
                                                seat_data = seat_data + "[   X ]    ";
                                        } else if (i % 4 == 3) {
                                            if (seats[i].equals("0"))
                                                if (i < 10)
                                                    seat_data = seat_data + " [   " + i + " ] ";
                                                else
                                                    seat_data = seat_data + "[ " + i + " ] ";
                                            else
                                                seat_data = seat_data + "[   X ] ";
                                        } else {
                                            if (seats[i].equals("0"))
                                                if (i < 10)
                                                    seat_data = seat_data + "[   " + i + " ] W\n";
                                                else
                                                    seat_data = seat_data + "[ " + i + " ] W\n";
                                            else
                                                seat_data = seat_data + "[   X ] W\n";
                                        }
                                    }
                                    seat_data = "\n\n\n" + seat_data + "\n" + "    Enter The Number Of Seat\n    You Want to book";
                                    seat_display.setText(seat_data);
                                }
                            });

                }

            }
        });
    }
}
