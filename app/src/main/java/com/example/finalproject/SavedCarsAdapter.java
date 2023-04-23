package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Database.CustomCar;
import com.example.finalproject.Database.CustomCarViewModel;

import java.util.List;

public class SavedCarsAdapter extends RecyclerView.Adapter<SavedCarsAdapter.SavedCarViewHolder> {

    private List<CustomCar> mCustomCarList;
    private final LayoutInflater mInflater;
    private CustomCarViewModel mCustomCarViewModel;


    class SavedCarViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public final TextView carIdTextView;
        public final TextView carMakeTextView;
        public final TextView carModelTextView;
        public final TextView carPriceTextView;
        public final TextView carColorTextView;
        public final TextView carWheelsTextView;
        public final TextView carTrimTextView;
        public final Button deleteBuildBtn;
        final SavedCarsAdapter mAdapter;


        public SavedCarViewHolder(View itemView, SavedCarsAdapter adapter) {
            super(itemView);
            carIdTextView = itemView.findViewById(R.id.oid);
            carMakeTextView = itemView.findViewById(R.id.carMake);
            carModelTextView = itemView.findViewById(R.id.carModel);
            carPriceTextView = itemView.findViewById(R.id.carPrice);
            carColorTextView = itemView.findViewById(R.id.carColor);
            carWheelsTextView = itemView.findViewById(R.id.carWheels);
            carTrimTextView = itemView.findViewById(R.id.carTrim);
            deleteBuildBtn = itemView.findViewById(R.id.deleteCarBtn);
            this.mAdapter = adapter;
            deleteBuildBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();

            // Use that to access the affected item in mWordList.
            CustomCar element = mCustomCarList.get(mPosition);
            // Change the word in the mWordList.
            mCustomCarViewModel.deleteCar(String.valueOf(element.getUid()));
            mCustomCarList.remove(element);
            setCustomCars(mCustomCarList);
        }
    }

    public void setCustomCars(List<CustomCar> customCarList) {
        mCustomCarList = customCarList;
        notifyDataSetChanged();
    }

    public SavedCarsAdapter(Context context, CustomCarViewModel customCarViewModel) {
        mInflater = LayoutInflater.from(context);
        this.mCustomCarViewModel = customCarViewModel;
    }

    @Override
    public SavedCarsAdapter.SavedCarViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.custom_car_item, parent, false);
        return new SavedCarViewHolder(mItemView, this);
    }


    @Override
    public void onBindViewHolder(SavedCarsAdapter.SavedCarViewHolder holder,
                                 int position) {
        // Retrieve the data for that position.
        CustomCar mCurrent = mCustomCarList.get(position);
        // Add the data to the view holder.
        holder.carIdTextView.setText("Build ID: " + String.valueOf(mCurrent.getUid()));
        holder.carMakeTextView.setText("Make: " + mCurrent.getMake());
        holder.carModelTextView.setText("Model: " + mCurrent.getModel());
        holder.carColorTextView.setText("Color: " + mCurrent.getColor());
        holder.carPriceTextView.setText("Price: " + mCurrent.getCost());
        holder.carTrimTextView.setText("Trim: " + mCurrent.getTrim());
        holder.carWheelsTextView.setText("Wheels: " + mCurrent.getWheels());

    }


    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if (mCustomCarList == null) {
            return 0;
        }
        return mCustomCarList.size();
    }
}

