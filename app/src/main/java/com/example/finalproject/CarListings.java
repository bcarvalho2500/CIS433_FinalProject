package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

public class CarListings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_listings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tabEntry1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tabEntry2));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPager2 viewPager = findViewById(R.id.pager);
        PagerAdapter mAdapter = new PagerAdapter(this, tabLayout.getTabCount(), getApplicationContext());
        viewPager.setAdapter(mAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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