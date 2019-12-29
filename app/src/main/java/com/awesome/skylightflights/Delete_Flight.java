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

public class Delete_Flight extends AppCompatActivity {

    private EditText d_d,d_m,d_y,flight;
    private Button delete;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference flightRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__flight);

        d_d = findViewById(R.id.d_day);
        d_m = findViewById(R.id.name_delete);
        d_y = findViewById(R.id.d_year);
        flight = findViewById(R.id.booking_id);

        delete = findViewById(R.id.delete_ticket);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day,month,year,flight_int;
                try
                {
                    day = Integer.parseInt(d_d.getText().toString().trim());
                    month = Integer.parseInt(d_m.getText().toString().trim());
                    year = Integer.parseInt(d_y.getText().toString().trim());
                    Integer.parseInt(flight.getText().toString().trim());
                    flightRef = db.collection(day+"."+month+"."+year).document(flight.getText().toString().trim());
                    flightRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists())
                                {
                                    int price = Integer.parseInt(document.getString("price"));
                                    flightRef.delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>()
                                        {
                                            @Override
                                            public void onSuccess(Void aVoid)
                                            {
                                                Toast.makeText(Delete_Flight.this,"Flight Successfully Deleted.",Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(Delete_Flight.this,Flight_Management_Admin.class);
                                                startActivity(intent);
                                                //todo mail to be sent if booking is deleted....
                                            }
                                        });
                                }
                                else
                                {
                                    Toast.makeText(Delete_Flight.this,"No Such Flight Exist",Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Log.d("Failed Deleting Flight", "Failed with: ", task.getException());
                            }
                        }
                    });

                }
                catch(Exception e)
                {
                    Toast.makeText(Delete_Flight.this,"Enter Data Correctly",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
