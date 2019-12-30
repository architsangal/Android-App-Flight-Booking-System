package com.awesome.skylightflights;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Arrays;


public class Selection_Of_Flight extends AppCompatActivity {

    //database
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String Date_sel;
    private CollectionReference date;
    private TextView available_flights;
    private EditText selected_flight;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection__of__flight);

        selected_flight = findViewById(R.id.selected_flight);
        available_flights = findViewById(R.id.available_flights);
        next = findViewById(R.id.next);

        Intent intent_prev = getIntent();
        final Passenger passenger = (Passenger) intent_prev.getSerializableExtra("passenger");

        Date_sel = passenger.getDate_day()
                +"." + passenger.getDate_month()
                +"." + passenger.getDate_year();

        date = db.collection(Date_sel);

        final String from = passenger.getFrom_place();
        final String to = passenger.getTo_place();
        final String[] aval_flights = new String[50];
        final String[] price = new String[50];
        final String[] dep = new String[50];
        final String[] land = new String[50];
        final String[] duration = new String[50];

        date.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots)
                    {
                        int c=0;
                        String data = "S.No.    Flight Name    Price(in Rs)    Dep. Time   Land. Time\n";
                        for(QueryDocumentSnapshot snapshots : queryDocumentSnapshots)
                        {
                            if(snapshots.getString("from").trim().equals(from.trim()) && snapshots.getString("to").trim().equals(to.trim()) && snapshots.getString("status").trim().equals("Aval"))
                            {
                                for(int i=1;i<=20;i++)
                                {
                                    if(snapshots.getString(i+"").equals("0"))
                                    {
                                        aval_flights[c] = snapshots.getId();
                                        price[c] = snapshots.getString("price");
                                        dep[c] = snapshots.getString("dep_time");
                                        land[c] = snapshots.getString("land_time");
                                        duration[c] = snapshots.getString("duration");
                                        Log.d("sof", Arrays.toString(aval_flights));
                                        if(c<9)
                                            data = data + "\n" + (c+1) + ".          " + aval_flights[c]+ "               " + snapshots.getString("price").trim() +"                "+ snapshots.getString("dep_time").trim() + "            "+ snapshots.getString("land_time");
                                        else
                                            data = data + "\n" + (c+1) + ".         " + aval_flights[c]+ "               " + snapshots.getString("price").trim() +"                "+ snapshots.getString("dep_time").trim() + "            "+ snapshots.getString("land_time");
                                        c++;
                                        break;
                                    }
                                }
                            }
                        }
                        if(c==0)
                            available_flights.setText("NO FLIGHTS AVAILABLE");
                        else
                            available_flights.setText(data);

                        final int temp_c=c;

                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int choice = Integer.parseInt(selected_flight.getText().toString().trim());
                                if(choice>0 && choice <=temp_c)
                                {
                                    passenger.setFlight(aval_flights[choice - 1]);
                                    passenger.setPrice(price[choice - 1]);
                                    passenger.setDep_time(dep[choice - 1]);
                                    passenger.setLan_time(land[choice - 1]);
                                    passenger.setDuration(duration[choice - 1]);
                                    Intent intent = new Intent(Selection_Of_Flight.this, Passenger_Info.class);
                                    intent.putExtra("passenger", passenger);
                                    startActivity(intent);
                                }
                                else
                                    Toast.makeText(Selection_Of_Flight.this,"Enter Choice Correctly",Toast.LENGTH_LONG).show();
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
}