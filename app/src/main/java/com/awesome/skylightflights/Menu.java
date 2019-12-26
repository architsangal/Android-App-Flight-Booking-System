package com.awesome.skylightflights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity implements View.OnClickListener
{

    private Button create_a_new_ticket;
    private Button delete_an_existing_ticket;
    private Button get_an_existing_ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        create_a_new_ticket = findViewById(R.id.create_a_new_ticket);
        delete_an_existing_ticket = findViewById(R.id.delete_an_existing_ticket);
        get_an_existing_ticket = findViewById(R.id.get_an_existing_ticket);

        create_a_new_ticket.setOnClickListener(this);
        delete_an_existing_ticket.setOnClickListener(this);
        get_an_existing_ticket.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.create_a_new_ticket:
                Intent intent = new Intent(Menu.this,Showing_Flights.class);
                startActivity(intent);
                break;
            case R.id.delete_an_existing_ticket:
                intent = new Intent(Menu.this,Delete_Flight_Ticket.class);
                startActivity(intent);
                break;
            case R.id.get_an_existing_ticket:
                intent = new Intent(Menu.this,Get_Flight_Ticket.class);
                startActivity(intent);
                break;
        }
    }

}
