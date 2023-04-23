package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.health.SystemHealthManager;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.material.snackbar.Snackbar;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_home:
                Intent goHome = new Intent(this, MainActivity.class);
                startActivity(goHome);
                return true;
            case R.id.action_cars:
                Intent openCarList = new Intent(this, CarListings.class);
                startActivity(openCarList);
                return true;
            case R.id.action_savedCars:
                Intent openSavedCars = new Intent(this, SavedCars.class);
                startActivity(openSavedCars);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void findNearbyDealerships(View view) {
        Uri dealershipUri = Uri.parse("geo:0,0?q=Car Dealerships");

        Intent openMap = new Intent(Intent.ACTION_VIEW, dealershipUri);
        try{
            startActivity(openMap);
        }catch (ActivityNotFoundException e){
            Log.d("Implicit Intents", "Can't handle this intent");
        }
    }

    public void openCarListings(View view) {
        Intent openCarList =  new Intent(this, CarListings.class);
        startActivity(openCarList);
    }

    public void scheduleAppt(View view) {
        DatePicker schedule = findViewById(R.id.datePicker);
        int day = schedule.getDayOfMonth();
        int month = schedule.getMonth();
        int year = schedule.getYear();

        TimePicker time = findViewById(R.id.timePicker);
        int hour = time.getHour();
        int minute = time.getMinute();

        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.set(year, month, day, hour, minute);
        Calendar endDateCalendar = Calendar.getInstance();
        endDateCalendar.set(year, month, day, hour, minute + 30);


        try {
            Intent addEvent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.Events.TITLE, "Test Drive")
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, dateCalendar.getTimeInMillis())
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endDateCalendar.getTimeInMillis())
                    .putExtra(CalendarContract.Events.ALL_DAY, false);
            if (addEvent.resolveActivity(getPackageManager()) != null){
                startActivity(addEvent);
            }

        }catch (ActivityNotFoundException e){
            Log.d("Intent", "Activity not found");
        }
    }
}