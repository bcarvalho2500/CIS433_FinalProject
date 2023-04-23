package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.finalproject.Database.CustomCar;
import com.example.finalproject.Database.CustomCarViewModel;

import java.util.List;

public class SavedCars extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_cars);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerview);
        CustomCarViewModel mCustomCarViewModel = new CustomCarViewModel(getApplication());
        SavedCarsAdapter mAdapter = new SavedCarsAdapter(this, mCustomCarViewModel);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));




        mCustomCarViewModel.getAllCustomCars().observe(this, new Observer<List<CustomCar>>() {
            @Override
            public void onChanged(@Nullable final List<CustomCar> customCars) {
                // Update the cached copy of the words in the adapter.

                mAdapter.setCustomCars(customCars);

            }
        });

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