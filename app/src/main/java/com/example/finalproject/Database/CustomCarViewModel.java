package com.example.finalproject.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CustomCarViewModel extends AndroidViewModel {
    private CustomCarRepository mRepository;
    private LiveData<List<CustomCar>> mAllCustomCars;

    public CustomCarViewModel(@NonNull Application application) {
        super(application);
        mRepository = new CustomCarRepository(application);
        mAllCustomCars = mRepository.getAllCustomCars();
    }

    public LiveData<List<CustomCar>> getAllCustomCars() { return mAllCustomCars; }

    public void deleteAll(){
        mRepository.deleteAll();
    }

    public void insertOrder(CustomCar customCar){
        mRepository.insert(customCar);
    }

    public void deleteOrder(String savedCarID){
        mRepository.delete(savedCarID);
    }
}
