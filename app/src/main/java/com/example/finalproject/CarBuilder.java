package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.Database.CustomCar;
import com.example.finalproject.Database.CustomCarViewModel;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.HashMap;

public class CarBuilder extends AppCompatActivity {

    private HashMap<String, String> carDetails;
    private ImageView carImageView;
    private TextView carMakeView;
    private TextView carModelView;
    private TextView carPriceView;

    private RadioGroup colorOptions;
    private RadioGroup wheelOptions;
    private RadioGroup trimOptions;
    private int price;
    private CustomCarViewModel mCustomCarViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_builder);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCustomCarViewModel = new ViewModelProvider(this).get(CustomCarViewModel.class);

        Intent intent = getIntent();
        carDetails = (HashMap<String, String>)intent.getSerializableExtra("CarDetails");
        carDetails.put("Color", "");
        carDetails.put("Wheels", "");
        carDetails.put("Trim", "");

        carImageView = findViewById(R.id.buildImage);
        carImageView.setImageResource(Integer.parseInt(carDetails.get("Image")));

        carMakeView = findViewById(R.id.carMake);
        carMakeView.setText(carDetails.get("Make"));

        carModelView = findViewById(R.id.carModel);
        carModelView.setText(carDetails.get("Model"));

        carPriceView = findViewById(R.id.buildPrice);
        carPriceView.setText(carDetails.get("Price"));
        String tempPrice = carDetails.get("Price");
        tempPrice = tempPrice.replace("$", "");
        tempPrice = tempPrice.replace(",", "");
        price = Integer.parseInt(tempPrice);

        colorOptions = findViewById(R.id.colorOptions);
        wheelOptions = findViewById(R.id.wheelOptions);
        trimOptions = findViewById(R.id.trimOptions);

        colorOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (R.id.colorOptionRed == i){
                    carDetails.replace("Color", "Red");
                }else if (R.id.colorOptionSilver == i) {
                    carDetails.replace("Color", "Silver");
                }else if (R.id.colorOptionBlack == i){
                    carDetails.replace("Color", "Black");
                }
                else if (R.id.colorOptionBlue == i){
                    carDetails.replace("Color", "Blue");
                }
            }
        });

        wheelOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (R.id.wheelOption18in == i){
                    if (!(carDetails.get("Wheels").equals(""))){
                        price -= 150;
                        updatePrice();
                    }
                    carDetails.replace("Wheels", "18 in hubcaps");
                }else if (R.id.wheelOption19in == i){
                    price += 150;
                    updatePrice();
                    carDetails.replace("Wheels", "19 in alloys");

                }
            }
        });

        trimOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (R.id.trimOptionBase == i){
                    if (carDetails.get("Trim").equals("Luxury")){
                        price -= 1250;
                        updatePrice();
                    }else if (carDetails.get("Trim").equals("Sport")){
                        price -= 2125;
                        updatePrice();
                    }
                    carDetails.replace("Trim", "Base");
                }else if (R.id.trimOptionLuxury == i) {
                    if (carDetails.get("Trim").equals("Sport")){
                        price -= 875;
                        updatePrice();
                    }else{
                        price += 1250;
                        updatePrice();
                    }
                    carDetails.replace("Trim", "Luxury");
                }else if (R.id.trimOptionSport == i){
                    if (carDetails.get("Trim").equals("Luxury")){
                        price += 875;
                        updatePrice();
                    }else{
                        price += 2125;
                        updatePrice();
                    }
                    carDetails.replace("Trim", "Sport");
                }
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

    public void saveCarBuild(View view) {
        CustomCar newCar = new CustomCar();
        newCar.setMake(carDetails.get("Make"));
        newCar.setModel(carDetails.get("Model"));
        newCar.setColor(carDetails.get("Color"));
        newCar.setCost(carDetails.get("Price"));
        newCar.setTrim(carDetails.get("Trim"));
        newCar.setWheels(carDetails.get("Wheels"));

        mCustomCarViewModel.insertCar(newCar);

        Toast.makeText(this, "Your build has been saved sucessfully!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, SavedCars.class);
        startActivity(intent);
    }

    public void updatePrice(){

        DecimalFormat df = new DecimalFormat("#,###");
        String formattedNumberWithComma = df.format(price);
        String tempPrice = "$" + formattedNumberWithComma;
        carPriceView.setText(tempPrice);
        carDetails.replace("Price", tempPrice);
    }
}