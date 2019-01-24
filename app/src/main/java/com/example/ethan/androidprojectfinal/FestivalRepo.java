package com.example.ethan.androidprojectfinal;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class FestivalRepo {

    private FestivalDao mFestivalDao;
    private LiveData<List<Festival>> mAllFestivals;


    FestivalRepo(Application application) {
        FestivalDatabase db = FestivalDatabase.getDatabase(application);
        mFestivalDao = db.festivalDao();
        mAllFestivals = mFestivalDao.getAllFestivals();
    }

    LiveData<List<Festival>> getmAllFestivals() {
        return mAllFestivals;
    }

    public void insert (Festival festival) {
        new insertAsyncTask(mFestivalDao).execute(festival);
    }

    private static class insertAsyncTask extends AsyncTask<Festival, Void, Void> {

        private FestivalDao mAsyncTaskDao;

        insertAsyncTask(FestivalDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Festival... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
