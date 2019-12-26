package com.awesome.skylightflights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class review_details extends AppCompatActivity {


    private TextView review;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_details);

        Intent intent_prev = getIntent();
        final Passenger passenger = (Passenger) intent_prev.getSerializableExtra("passenger");

        review = findViewById(R.id.review_details);

        String data = "    Name:";
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
        data += passenger.getSeat();
        review.setText(data);
    }
}
