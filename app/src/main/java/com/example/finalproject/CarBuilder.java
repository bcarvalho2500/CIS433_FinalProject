package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.HashMap;

public class CarBuilder extends AppCompatActivity {

    private HashMap<String, String> carDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_builder);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        carDetails = (HashMap<String, String>)intent.getSerializableExtra("CarDetails");
        System.out.println(carDetails.get("Image"));
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
}