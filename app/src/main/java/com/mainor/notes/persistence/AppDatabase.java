package com.mainor.notes.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mainor.notes.entities.Note;

@Database(entities = {Note.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract NoteDao noteDao();
    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, AppDatabase.class.getName())
                    //if you want create db only in memory, not in file
                    //Room.inMemoryDatabaseBuilder
                    //(context.getApplicationContext(), DataRoomDbase.class)
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
