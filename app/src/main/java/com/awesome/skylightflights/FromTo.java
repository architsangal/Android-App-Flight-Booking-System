package com.awesome.skylightflights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FromTo extends AppCompatActivity implements View.OnClickListener
{

    private Button go;
    private EditText from;
    private EditText to;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_to);

        go = findViewById(R.id.go);
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);

        go.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        Intent intent_prev = getIntent();
        Passenger passenger = (Passenger) intent_prev.getSerializableExtra("passenger");
        if (passenger != null)
        {
            passenger.setTo_place(to.getText().toString().trim());
            passenger.setFrom_place(from.getText().toString().trim());
        }

        Intent intent = new Intent(FromTo.this,Selection_Of_Flight.class);
        intent.putExtra("passenger",passenger);
        startActivity(intent);
    }

}