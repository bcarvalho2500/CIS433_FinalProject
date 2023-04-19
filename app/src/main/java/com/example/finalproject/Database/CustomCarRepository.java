package com.example.finalproject.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CustomCarRepository {
    private CustomCarDao mCustomCarDao;
    private LiveData<List<CustomCar>> mAllCustomCars;

    CustomCarRepository (Application application){
        SavedCarsDatabase db = SavedCarsDatabase.getDatabase(application);
        mCustomCarDao = db.customCarDao();
        mAllCustomCars = mCustomCarDao.getAllCustomCars();
    }

    LiveData<List<CustomCar>> getAllCustomCars(){
        return mAllCustomCars;
    }

    public void deleteAll(){
        new deleteAllAsyncTask(mCustomCarDao).execute();
    }

    public void delete(String savedCarID){
        new deleteAsyncTask(mCustomCarDao).execute(savedCarID);
    }

    public void insert(CustomCar customCar){
        new insertAsyncTask(mCustomCarDao).execute(customCar);
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private CustomCarDao mAsyncTaskDao;

        deleteAllAsyncTask(CustomCarDao dao){ mAsyncTaskDao = dao; }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<String, Void, Void> {
        private CustomCarDao mAsyncTaskDao;

        deleteAsyncTask(CustomCarDao dao){ mAsyncTaskDao = dao; }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.deleteOrder(params[0]);
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<CustomCar, Void, Void> {
        private CustomCarDao mAsyncTaskDao;

        insertAsyncTask(CustomCarDao dao){ mAsyncTaskDao = dao; }

        @Override
        protected Void doInBackground(final CustomCar... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
