package com.awesome.skylightflights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class review_details extends AppCompatActivity {


    private TextView review;
    private Button payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_details);

        Intent intent_prev = getIntent();
        final Passenger passenger = (Passenger) intent_prev.getSerializableExtra("passenger");

        review = findViewById(R.id.review_details);
        payment = findViewById(R.id.payment);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(review_details.this,payment_portal.class);
                intent.putExtra("passenger",passenger);
                startActivity(intent);
            }
        });
        String data = "Name : ";
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
        review.setText(data);

        data += "\nFlight Class : " + passenger.getFlight_class();
        data += "\nPrice : Rs " + passenger.getPrice();
        data += "\nSeat  : " + passenger.getSeat();
        data += "\nDuration     : " + passenger.getDuration() + " min";
        passenger.setBooking_Id(genBookingId()+"");
        data += "\nBooking Id : " + passenger.getBooking_Id();
        review.setText(data);
    }
    public static long genBookingId()
    {
        int n = 10000000;
        int m = 99999999;
        return ((long)((Math.random()*(n-m)))+m);
    }
}