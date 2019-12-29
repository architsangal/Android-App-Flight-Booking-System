package com.awesome.skylightflights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Passenger_Info extends AppCompatActivity
{

    private EditText name;
    private EditText age;
    private EditText gender;
    private Button next;
    private Spinner class_type;
    private String class_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger__info);

        Intent intent_prev = getIntent();
        final Passenger passenger = (Passenger) intent_prev.getSerializableExtra("passenger");

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        next = findViewById(R.id.next_next);
        class_type = findViewById(R.id.class_type);
        List<String> classOfFly= new ArrayList<>();
        classOfFly.add(0,"--Choose Class--");
        classOfFly.add("Economy Class");
        classOfFly.add("Business Class");
        classOfFly.add("First Class");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,classOfFly);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        class_type.setAdapter(dataAdapter);

        class_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!class_type.getSelectedItem().toString().equals("--Choose Class--"))
                {
                    class_selected = class_type.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                passenger.setName(name.getText().toString().trim().toUpperCase());
                passenger.setAge(Integer.parseInt(age.getText().toString().trim()));
                try
                {
                    if(gender.getText().toString().trim().toUpperCase().equals("M") || gender.getText().toString().trim().toUpperCase().equals("O") || gender.getText().toString().trim().toUpperCase().equals("F"))
                        passenger.setGender(gender.getText().toString().trim().toUpperCase());
                    else
                        Integer.parseInt("A");// to raise exception
                    if(class_selected.equals("First Class") || class_selected.equals("Business Class") || class_selected.equals("Economy Class"))
                        passenger.setFlight_class(class_selected);
                    else
                        Integer.parseInt("A");// to raise exception

                    Intent intent = new Intent(Passenger_Info.this,seat_selection.class);
                    intent.putExtra("passenger", passenger);
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    Toast.makeText(Passenger_Info.this,"Enter Data Correctly",Toast.LENGTH_LONG).show();
                }


            }
        });



    }
}
