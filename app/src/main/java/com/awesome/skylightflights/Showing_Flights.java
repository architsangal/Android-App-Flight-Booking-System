package com.awesome.skylightflights;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class Showing_Flights extends AppCompatActivity
{

    private TextView text;
    private CalendarView calendar;
    private String Date = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing__flights);

        calendar = findViewById(R.id.calendar);
        text = findViewById(R.id.select_date);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override

            // In this Listener have one method
            // and in this method we will
            // get the value of DAYS, MONTH, YEARS
            public void onSelectedDayChange(@NonNull CalendarView view,int year,int month,int dayOfMonth)
            {

                // Store the value of date with
                // format in String type Variable
                // Add 1 in month because month
                // index is start with 0
                Date
                        = dayOfMonth + "."
                        + (month + 1) + "/" + year;

                String selected_date = Date;

                int selDay =Integer.parseInt(selected_date.substring(0,selected_date.indexOf('.')));
                int selMonth =Integer.parseInt(selected_date.substring(selected_date.indexOf('.')+1,selected_date.indexOf('/')));
                int selYear =Integer.parseInt(selected_date.substring(selected_date.indexOf('/')+1));

                if(isCorrectDate(selDay,selMonth,selYear))
                {
                    Passenger passenger = new Passenger();
                    passenger.setDate_day(selDay);
                    passenger.setDate_month(selMonth);
                    passenger.setDate_year(selYear);
                    Intent intent = new Intent(Showing_Flights.this,FromTo.class);
                    intent.putExtra("passenger",passenger);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Showing_Flights.this, "Select a VALID date", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public boolean isCorrectDate(int day,int month,int year)
    {
        String currDate = (String) DateFormat.format("dd-MM-yyyy",new java.util.Date());
        int currDay =Integer.parseInt(currDate.substring(0,2));
        int currMonth =Integer.parseInt(currDate.substring(3,5));
        int currYear =Integer.parseInt(currDate.substring(6));

        if(currYear < year)
            return true;
        else if(currYear > year)
            return false;
        else
        {
            if(currMonth < month)
                return true;
            else if(currMonth > month)
                return false;
            else
            {
                if(currDay <= day)
                    return true;
                else
                    return false;
            }
        }

    }

}