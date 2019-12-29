package com.awesome.skylightflights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button b0;
    private Button backspace;
    private Button enter;

    private TextView password_number;
    private int[] password = new int[50];
    private int current_password_key=0;
    private String enter_password="75790152222225109757";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button_1);
        b2 = findViewById(R.id.button_2);
        b3 = findViewById(R.id.button_3);
        b4 = findViewById(R.id.button_4);
        b5 = findViewById(R.id.button_5);
        b6 = findViewById(R.id.button_6);
        b7 = findViewById(R.id.button_7);
        b8 = findViewById(R.id.button_8);
        b9 = findViewById(R.id.button_9);
        b0 = findViewById(R.id.button_0);
        backspace = findViewById(R.id.backspace);
        enter = findViewById(R.id.enter_button);
        password_number = findViewById(R.id.password_number);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b0.setOnClickListener(this);
        backspace.setOnClickListener(this);
        enter.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        try {
            switch (v.getId()) {
                case R.id.button_1:
                    password[current_password_key] = 1;
                    current_password_key++;
                    break;
                case R.id.button_2:
                    password[current_password_key] = 2;
                    current_password_key++;
                    break;
                case R.id.button_3:
                    password[current_password_key] = 3;
                    current_password_key++;
                    break;
                case R.id.button_4:
                    password[current_password_key] = 4;
                    current_password_key++;
                    break;
                case R.id.button_5:
                    password[current_password_key] = 5;
                    current_password_key++;
                    break;
                case R.id.button_6:
                    password[current_password_key] = 6;
                    current_password_key++;
                    break;
                case R.id.button_7:
                    password[current_password_key] = 7;
                    current_password_key++;
                    break;
                case R.id.button_8:
                    password[current_password_key] = 8;
                    current_password_key++;
                    break;
                case R.id.button_9:
                    password[current_password_key] = 9;
                    current_password_key++;
                    break;
                case R.id.button_0:
                    password[current_password_key] = 0;
                    current_password_key++;
                    break;
                case R.id.backspace:
                    if (current_password_key == 0)
                        break;
                    password[current_password_key - 1] = 0;
                    current_password_key--;
                    break;
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            current_password_key=0;
            for(int i=0;i<password.length;i++)
                password[i]=0;
            password_number.setText("");
        }
        String entered = "";
        String password_entered = "";
        int j=0;
        for(int i:password)
        {
            if(j==current_password_key)
                break;
            password_entered = password_entered + i;
            entered = entered + "*";
            j++;
        }
        password_number.setText(entered);
        if(v.getId() == R.id.enter_button)
        {

            entered="";
            current_password_key=0;
            for(int i=0;i<password.length;i++)
                password[i]=0;
            password_number.setText(entered);
            if(password_entered.equals(enter_password))
            {
                Intent intent = new Intent(MainActivity.this,Flight_Management_Admin.class);
                startActivity(intent);

            }
            else
            {
                Intent intent = new Intent(MainActivity.this,Menu.class);
                startActivity(intent);
            }
        }
    }
}