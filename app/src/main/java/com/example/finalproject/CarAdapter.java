package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Car> mCarData;
    private final LayoutInflater mInflater;

    public CarAdapter(Context context, ArrayList<Car> carData){
        this.mContext = context;
        this.mCarData = carData;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.car_view,parent,false);
        return new MyViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Car currentData = mCarData.get(position);
        holder.bindTo(currentData);
    }

    @Override
    public int getItemCount() {
        return mCarData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView mCarMake;
        private TextView mCarPrice;
        private TextView mCarModel;
        private ImageView mCarImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mCarMake = itemView.findViewById(R.id.carMake);
            mCarPrice = itemView.findViewById(R.id.carPrice);
            mCarModel = itemView.findViewById(R.id.carModel);
            mCarImage = itemView.findViewById(R.id.carImage);

            // Insert button listeners here
            itemView.findViewById(R.id.buildCarBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HashMap<String, String> currentCar = new HashMap<>();
                    currentCar.put("Make", mCarMake.getText().toString());
                    currentCar.put("Model", mCarModel.getText().toString());
                    currentCar.put("Price", mCarPrice.getText().toString());
                    currentCar.put("Image", String.valueOf(mCarImage.getDrawable().getConstantState()));
                    Intent startBuilding = new Intent(view.getContext(), CarBuilder.class);
                    startBuilding.putExtra("CarDetails", currentCar);
                    view.getContext().startActivity(startBuilding);
                }
            });
        }

        void bindTo(Car currentCar){
            Glide.with(mContext).load(currentCar.getImageResource()).into(mCarImage);
            mCarMake.setText(currentCar.getMake());
            mCarPrice.setText(currentCar.getPrice());
            mCarModel.setText(currentCar.getModel());
        }

    }
}