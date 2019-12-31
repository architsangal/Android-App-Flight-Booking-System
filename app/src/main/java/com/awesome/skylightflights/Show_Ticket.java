package com.awesome.skylightflights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Show_Ticket extends AppCompatActivity {

    private TextView ticket;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference flightRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__ticket);

        ticket = findViewById(R.id.ticket);
        Intent intent_prev1 = getIntent();
        final Passenger passenger1 = (Passenger) intent_prev1.getSerializableExtra("passenger");
        flightRef = db.collection(passenger1.getDate_day()+"."+passenger1.getDate_month()+"."+passenger1.getDate_year()).document(passenger1.getFlight());
        flightRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Intent intent_prev = getIntent();
                        final Passenger passenger = (Passenger) intent_prev.getSerializableExtra("passenger");
                        String status = "Conformed";
                        if(intent_prev.getStringExtra("status").equals("Cancelled") || documentSnapshot.getString("status").equals("Cancelled"))
                            status = "Cancelled";
                        String data = "\nStatus : " + status + "\nName : ";
                        int length = passenger.getName().length();
                        if(length<14)
                        {
                            data += passenger.getName();
                            for(int i=length;i<13;i++)
                                data +=" ";
                        }
                        else
                        {
                            data += passenger.getName().substring(0,13);
                        }
                        data += "\nAge    : " + passenger.getAge() + " years";
                        data += "\nGender  : " + passenger.getGender();

                        data += "\nFlight     : " + passenger.getFlight();
                        data += "\nFrom     : " + passenger.getFrom_place();
                        data += "\nTo          : " + passenger.getTo_place();
                        data += "\nDate      : " + passenger.getDate_day()
                                +"." + passenger.getDate_month()
                                +"." + passenger.getDate_year();
                        data += "\nDep. time     : " + passenger.getDep_time();
                        data += "\nLand. time   : " + passenger.getLan_time();

                        data += "\nFlight Class : " + passenger.getFlight_class();
                        data += "\nPrice : Rs " + passenger.getPrice();
                        data += "\nSeat  : " + passenger.getSeat();
                        data += "\nDuration     : " + passenger.getDuration() + " min";
                        data += "\nBooking Id : " + passenger.getBooking_Id();

                        ticket.setText(data);

                    }
                });
    }
}
