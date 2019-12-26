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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Arrays;


public class Selection_Of_Flight extends AppCompatActivity {

    //database
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String Date_sel;
    private DocumentReference Flights;
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
        Passenger passenger = (Passenger) intent_prev.getSerializableExtra("passenger");

        Date_sel = passenger.getDate_day()
                +"." + passenger.getDate_month()
                +"." + passenger.getDate_year();

        date = db.collection(Date_sel);

        final String from = passenger.getFrom_place();
        final String to = passenger.getTo_place();
        final String[] aval_flights = new String[10];
        date.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots)
                    {
                        int c=0;
                        String data = "S.No.    Flight Name    Price(in Rs)    Dep. Time   Land. Time\n";
                        for(QueryDocumentSnapshot snapshots : queryDocumentSnapshots)
                        {
                            if(snapshots.getString("from").trim().equals(from.trim()) && snapshots.getString("to").trim().equals(to.trim()))
                            {
                                aval_flights[c] = snapshots.getId();
                                Log.d("sof", Arrays.toString(aval_flights));
                                if(c<9)
                                    data = data + "\n" + (c+1) + ".          " + aval_flights[c]+ "               " + snapshots.getString("price").trim() +"                "+ snapshots.getString("dep_time").trim() + "            "+ snapshots.getString("land_time");
                                else
                                    data = data + "\n" + (c+1) + ".         " + aval_flights[c]+ "               " + snapshots.getString("price").trim() +"                "+ snapshots.getString("dep_time").trim() + "            "+ snapshots.getString("land_time");
                                c++;
                            }
                        }
                        if(c==0)
                            available_flights.setText("NO FLIGHTS AVAILABLE");
                        else
                            available_flights.setText(data);
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int choice = Integer.parseInt(selected_flight.getText().toString().trim())-1;
                                Intent intent = new Intent(Selection_Of_Flight.this,Passenger_Info.class);
                                intent.putExtra("passenger",aval_flights[choice]);
                                startActivity(intent);
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });


/*                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                            final String[] flights = new String[10];
                            final String [] aval_flights = new String[10];

                            Map<String,Object> map = documentSnapshot.getData();

                            for(int i=0;i<map.size();i++)
                            {

                                flights[i] = documentSnapshot.getString((""+i));
                                final String curr_flight = flights[i];

                                DocumentReference flightRef = db.collection("Flights")
                                        .document(Date_sel).collection(curr_flight).document(curr_flight);

                                Log.d("sof","" + flightRef.get().isCanceled()+flightRef.get().isComplete()+flightRef.get().isSuccessful());

                                flightRef.get()
                                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
                                        {
                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                Log.d("sof", "executed");
                                                if(!documentSnapshot.exists())
                                                {
                                                    String curr_flight_from = documentSnapshot.getString("from");
                                                    String curr_flight_to = documentSnapshot.getString("to");
                                                    Log.d("sof",curr_flight_from);
                                                    Log.d("sof",curr_flight_to);
                                                    if(from.equals(curr_flight_from) && to.equals(curr_flight_to))
                                                    {
                                                        int f=0;
                                                        for(int j=1; j<=20;j++)
                                                        {
                                                            if(documentSnapshot.getString((j+"")).equals("0"))
                                                                f=1;
                                                        }
                                                        if(f == 1)
                                                        {
                                                            int c=0;
                                                            while(!aval_flights[c].isEmpty())
                                                            {
                                                                c++;
                                                            }
                                                            aval_flights[c] = curr_flight;
                                                        }
                                                    }
                                                }
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d("sof","executed");
                                            }
                                        });
                            }
                            if(aval_flights[0].equals(""))
                            {
                                Intent intent = new Intent(Selection_Of_Flight.this,FromTo.class);
                                Toast.makeText(Selection_Of_Flight.this,"No Flights Available",Toast.LENGTH_LONG).show();
                                startActivity(intent);
                            }
                            else
                            {
                                String data = "S.No.  Flight Name  Price    Dep. Time  Landing Time";
                                int i=0;
                                while(!aval_flights[i].isEmpty())
                                {
                                    final DocumentReference flightRef = db.collection("Flight").document(Date_sel)
                                            .collection(aval_flights[i]).document(aval_flights[i]);

                                    final String[] curr_flight_info = new String[3];

                                    flightRef.get()
                                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                @Override
                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                    curr_flight_info[0] = documentSnapshot.getString("price");
                                                    curr_flight_info[1] = documentSnapshot.getString("dep_time");
                                                    curr_flight_info[2] = documentSnapshot.getString("land_time");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {

                                                }
                                            });

                                    if(i<9)
                                        data = "\n" + (i+1) + ".     " + aval_flights[i]+ "        Rs" + curr_flight_info[0] +"   "+ curr_flight_info[1] + "       "+curr_flight_info[2];
                                    else
                                        data = "\n" + (i+1) + ".    " + aval_flights[i]+ "        Rs" + curr_flight_info[0] +"   "+ curr_flight_info[1] + "       "+curr_flight_info[2];

                                    i++;
                                }
                                available_flights.setText(data);
                                next.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int choice = Integer.parseInt(selected_flight.getText().toString().trim())-1;
                                        Intent intent = new Intent(Selection_Of_Flight.this,Passenger_Info.class);
                                        intent.putExtra("passenger",aval_flights[choice]);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }
                        else
                        {
                            Intent intent = new Intent(Selection_Of_Flight.this,FromTo.class);
                            Toast.makeText(Selection_Of_Flight.this,"No Flights Available",Toast.LENGTH_LONG).show();
                            startActivity(intent);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
*/
    }
}