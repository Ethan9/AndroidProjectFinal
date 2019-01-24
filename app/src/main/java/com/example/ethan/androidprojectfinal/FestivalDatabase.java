package com.example.ethan.androidprojectfinal;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Festival.class}, version = 3, exportSchema = false)
public abstract class FestivalDatabase extends RoomDatabase {
    public abstract FestivalDao festivalDao();

    private static FestivalDatabase INSTANCE;

    static FestivalDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FestivalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FestivalDatabase.class, "festival_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static FestivalDatabase.Callback sRoomDatabaseCallback =
            new FestivalDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
//                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
//
//    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
//
//        private final FestivalDao mDao;
//
//        PopulateDbAsync(FestivalDatabase db) {
//            mDao = db.festivalDao();
//        }
//
//        @Override
//        protected Void doInBackground(final Void... params) {
//            mDao.deleteAll();
//            Festival festival = new Festival("Glastonbury", 51.147351, -2.718377);
//            mDao.insert(festival);
//            festival = new Festival("Parklife", 53.535708, -2.254709);
//            mDao.insert(festival);
//            festival = new Festival("Reading", 51.447558, -1.009084);
//            mDao.insert(festival);
//            festival = new Festival("Creamfields", 53.346648, -2.626197);
//            mDao.insert(festival);
//            festival = new Festival("Boomtown", 51.461243, -2.585546);
//            mDao.insert(festival);
//            return null;
//        }
//    }
}
