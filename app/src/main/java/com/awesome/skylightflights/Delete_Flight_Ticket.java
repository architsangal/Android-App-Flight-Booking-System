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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__flight__ticket);

        name = findViewById(R.id.name_delete);
        booking_id = findViewById(R.id.booking_id);

        delete_ticket = findViewById(R.id.delete_ticket);

        delete_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
                try
                {
                    id = Integer.parseInt(booking_id.getText().toString().trim());
                    bookingRef = db.collection(booking_id.getText().toString().trim()).document(name.getText().toString().trim());
                    bookingRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists())
                                {
                                    Map<String,Object> map = new HashMap<>();
                                    map.put("status","Cancelled");
                                    bookingRef.update(map)
                                            .addOnSuccessListener(new OnSuccessListener<Void>()
                                            {
                                                @Override
                                                public void onSuccess(Void aVoid)
                                                {
                                                    Toast.makeText(Delete_Flight_Ticket.this,"Ticket Successfully Cancelled.",Toast.LENGTH_LONG).show();
                                                    Intent intent = new Intent(Delete_Flight_Ticket.this,Menu.class);
                                                    startActivity(intent);
                                                }
                                            });
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
