package com.awesome.skylightflights;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FlightDetails extends AppCompatActivity implements View.OnClickListener{

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference flightRef;

    private EditText from;
    private EditText to;
    private EditText dep_time;
    private EditText land_time;
    private EditText duration;
    private EditText nameOfFlight;
    private EditText day;
    private EditText month;
    private EditText year;
    private EditText price;
    private Button ADD;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);

        from = findViewById(R.id.add_from);
        to = findViewById(R.id.add_to);
        dep_time = findViewById(R.id.add_dep_time);
        land_time = findViewById(R.id.add_land_time);
        duration = findViewById(R.id.add_duration);
        nameOfFlight = findViewById(R.id.add_flight_name);
        day = findViewById(R.id.add_day);
        month = findViewById(R.id.add_month);
        year = findViewById(R.id.add_year);
        price = findViewById(R.id.add_price);
        ADD = findViewById(R.id.add_flight);

        ADD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        final flight_info info = new flight_info();
        info.setFrom(from.getText().toString().trim());
        info.setTo(to.getText().toString().trim());
        info.setDay(day.getText().toString().trim());
        info.setMonth(month.getText().toString().trim());
        info.setYear(year.getText().toString().trim());
        info.setDep_time(dep_time.getText().toString().trim());
        info.setLan_time(land_time.getText().toString().trim());
        info.setDuration(duration.getText().toString().trim());
        info.setFlight_name(nameOfFlight.getText().toString().trim());
        info.setPrice(price.getText().toString().trim());

        try
        {
            Integer.parseInt(info.getDay());
            Integer.parseInt(info.getMonth());
            Integer.parseInt(info.getYear());
            Integer.parseInt(info.getDep_time());
            Integer.parseInt(info.getDuration());
            Integer.parseInt(info.getLan_time());
            Integer.parseInt(info.getFlight_name());
            Integer.parseInt(info.getPrice());
            String Date = Integer.parseInt(info.getDay())
                            +"."+ Integer.parseInt(info.getMonth())
                            +"."+ Integer.parseInt(info.getYear());
            flightRef = db.collection(Date).document(info.getFlight_name());

            Map<String,Object> map = new HashMap<>();
            map.put("name",info.getFlight_name());
            map.put("from",info.getFrom());
            map.put("to",info.getTo());
            map.put("day",info.getDay());
            map.put("month",info.getMonth());
            map.put("year",info.getYear());
            map.put("dep_time",info.getDep_time());
            map.put("land_time",info.getLan_time());
            map.put("duration",info.getDuration());
            map.put("price",info.getPrice());
            map.put("status","Aval");
            for(int i=1;i<=20;i++)
            {
                map.put((i + ""), "0" );
            }
            flightRef.set(map)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(FlightDetails.this,"Flight Added Successfully",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(FlightDetails.this,Flight_Management_Admin.class);
                    startActivity(intent);

                }
            })
            .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(FlightDetails.this,"Failed To Add Flight" + e.toString(),Toast.LENGTH_LONG).show();
                            }
                        });
        }
        catch (Exception e)
        {
            Toast.makeText(FlightDetails.this,"Enter The Details Correctly",Toast.LENGTH_LONG).show();
        }

/*        Flights.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                            Map<String,Object> map = documentSnapshot.getData();
                            map.put((""+map.size()),info.getFlight_name());
                            Flights.set(map);
                        }
                        else
                        {
                            Map<String,Object> map = new HashMap<>();
                            map.put("0",info.getFlight_name());
                            Flights.set(map);
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
