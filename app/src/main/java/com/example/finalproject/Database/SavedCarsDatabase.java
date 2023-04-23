package com.example.finalproject.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {CustomCar.class}, version = 1, exportSchema = false)
public abstract class SavedCarsDatabase extends RoomDatabase {
    public abstract CustomCarDao customCarDao();

    private static SavedCarsDatabase INSTANCE;

    static SavedCarsDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (SavedCarsDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    SavedCarsDatabase.class, "savedCars_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}
