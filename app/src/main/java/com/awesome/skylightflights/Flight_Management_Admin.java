package com.awesome.skylightflights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Flight_Management_Admin extends AppCompatActivity implements View.OnClickListener{

    private Button add;
    private Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight__management__admin);

        add = findViewById(R.id.add_flight);
        delete = findViewById(R.id.delete_ticket);

        add.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add_flight)
        {
            Intent intent = new Intent(Flight_Management_Admin.this,FlightDetails.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.delete_ticket)
        {
            Intent intent = new Intent(Flight_Management_Admin.this,Delete_Flight.class);
            startActivity(intent);
        }

    }
}
