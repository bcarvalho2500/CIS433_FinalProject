package com.example.finalproject.Fragments;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.Car;
import com.example.finalproject.CarAdapter;
import com.example.finalproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarFragment extends Fragment {

    private ArrayList<Car> mCarData;
    CarAdapter mAdapter;

    public CarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car, container, false);

        RecyclerView mRecyclerView = view.findViewById(R.id.carRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);

        mCarData = new ArrayList<>();

        mAdapter = new CarAdapter(view.getContext(), mCarData);
        mRecyclerView.setAdapter(mAdapter);

        initalizeData();

        return view;
    }

    private void initalizeData(){
        String[] carMakeList = getResources().getStringArray(R.array.car_make);
        String[] carPriceList = getResources().getStringArray(R.array.car_basePrice);
        String[] carModelList = getResources().getStringArray(R.array.car_model);
        TypedArray carImageResources = getResources().obtainTypedArray(R.array.car_imageUrl);

        mCarData.clear();

        for (int i = 0; i < carMakeList.length; i++){
            mCarData.add(new Car(carMakeList[i], carPriceList[i], carModelList[i], carImageResources.getResourceId(i, 0)));
        }

        carImageResources.recycle();
        mAdapter.notifyDataSetChanged();
    }

}