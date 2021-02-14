package com.classy.common.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.classy.common.model.Session;

@Database(entities = {Session.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SessionDao sessionDao();
}